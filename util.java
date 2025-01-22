import java.io.*;

public class util {
    private static final String INTEGER_FILE = "integers.txt";
    private static final String FLOAT_FILE = "floats.txt";
    private static final String STRING_FILE = "strings.txt";

    private static boolean flag_a = false;
    private static boolean floats_flag_a = false;
    private static boolean integers_flag_a = false;
    private static boolean strings_flag_a = false;

    private static boolean flag_s = false;
    private static boolean flag_f = false;

    private static int num_elem_floats = 0;
    private static double mini_num_float = 0;
    private static double max_num_float = 0;
    private static double amount_float = 0;

    private static int num_elem_int = 0;
    private static long mini_num_int = 0;
    private static long max_num_int = 0;
    private static long amount_int = 0;

    private static int num_elem_strings = 0;
    private static int mini_long_string = 0;
    private static int max_long_string = 0;

    private static boolean flag_o = false;
    private static String path_results = "";

    private static String prefix_name = "";

    public static void main(String args[]) {
        int args_len = args.length;

        String incoming_files = "";

        for (Integer i = 0; i < args_len; i++) {
            if (args[i].matches("-[asfop]")) {
                switch (args[i].charAt(1)) {
                    case 'a':
                        floats_flag_a = true;
                        integers_flag_a = true;
                        strings_flag_a = true;
                        break;
                    case 's':
                        flag_s = true;
                        break;
                    case 'f':
                        flag_f = true;
                        break;
                    case 'o':
                        flag_o = true;
                        path_results = i + 1 != args_len ? args[++i] + "\\" : "";
                        break;
                    case 'p':
                        prefix_name = i + 1 != args_len ? args[++i] : "";
                        break;
                }

            } else if (args[i].matches(".*\\.txt$")) {
                incoming_files = incoming_files + args[i] + "\n";
            }

            else {
                System.out.println("Wrong path or flag: " + args[i]);
            }
        }

        String[] array_inputs = incoming_files.split("\n");

        File directory = new File(path_results);

        if (!flag_o || (directory.exists() && directory.isDirectory())) {
            for (String inp_file : array_inputs) {
                readingFile(inp_file);
            }

            if (flag_s || flag_f) {
                print_statistics();
            }
        }

        else {
            System.out.println("The directory does not exist or it is not a directory: " + path_results);
        }
    }

    private static void readingFile(String inp_file) {
        File file = new File(inp_file);
        if (!file.exists()) {
            System.out.println("File not found: " + inp_file);
        }

        else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String recording_file = path_results + prefix_name + determineDataType(line);

                    writingFile(line, recording_file);
                }

            } catch (IOException ex) {
                System.out.println("Error reading the file: " + ex.getMessage());
            }
        }
    }

    private static String determineDataType(String data) {
        String result = STRING_FILE;

        try {
            Long.parseLong(data);
            result = INTEGER_FILE;
            num_elem_int++;

        } catch (NumberFormatException ex_int) {

            try {
                Double.parseDouble(data);
                result = FLOAT_FILE;
                num_elem_floats++;

            } catch (NumberFormatException ex_float) {
                num_elem_strings++;
            }
        }

        if (flag_f) {
            statistics(result, data);
        }

        settingFlagA(result);
        return result;
    }

    private static void writingFile(String data, String recording_file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(recording_file, flag_a))) {
            bw.write(data + "\n");

        } catch (IOException ex) {
            System.out.println("Error writing the file: " + ex.getMessage());
        }

        flag_a = false;
    }

    private static void settingFlagA(String data_type) {
        if (data_type == INTEGER_FILE) {
            if (integers_flag_a) {
                flag_a = true;
            } else {
                integers_flag_a = true;
            }

        } else if (data_type == FLOAT_FILE) {
            if (floats_flag_a) {
                flag_a = true;
            } else {
                floats_flag_a = true;
            }

        } else {
            if (strings_flag_a) {
                flag_a = true;
            } else {
                strings_flag_a = true;
            }
        }
    }

    private static void statistics(String data_type, String data) {
        if (data_type == INTEGER_FILE) {
            long num = Long.parseLong(data);

            amount_int += num;

            if (num_elem_int == 1) {
                mini_num_int = num;
                max_num_int = num;
            }

            else if (num < mini_num_int) {
                mini_num_int = num;
            }

            else if (num > max_num_int) {
                max_num_int = num;
            }

        } else if (data_type == FLOAT_FILE) {
            double num = Double.parseDouble(data);

            amount_float += num;

            if (num_elem_floats == 1) {
                mini_num_float = num;
                max_num_float = num;
            }

            else if (num < mini_num_float) {
                mini_num_float = num;
            }

            else if (num > max_num_float) {
                max_num_float = num;
            }

        } else {
            if (num_elem_strings == 1) {
                mini_long_string = data.length();
                max_long_string = data.length();
            }

            else if (data.length() < mini_long_string) {
                mini_long_string = data.length();
            }

            else if (data.length() > max_long_string) {
                max_long_string = data.length();
            }
        }
    }

    private static void print_statistics() {
        System.out.println("\nFile statistics:");

        System.out.println("Floats_element - " + num_elem_floats);
        if (flag_f && num_elem_floats > 0) {
            System.out.println("\tMinimum value of the element - " + mini_num_float);
            System.out.println("\tMaximum value of the element - " + max_num_float);
            System.out.println("\tSum of the elements - " + amount_float);
            System.out.println("\tAverage value - " + amount_float / num_elem_floats + "\n");
        }

        System.out.println("Integers_element - " + num_elem_int);
        if (flag_f && num_elem_int > 0) {
            System.out.println("\tMinimum value of the element - " + mini_num_int);
            System.out.println("\tMaximum value of the element - " + max_num_int);
            System.out.println("\tSum of the elements - " + amount_int);
            System.out.println("\tAverage value - " + amount_int / num_elem_int + "\n");
        }

        System.out.println("Strings_element - " + num_elem_strings);
        if (flag_f && num_elem_strings > 0) {
            System.out.println("\tMinimum long of the element - " + mini_long_string);
            System.out.println("\tMaximum long of the element - " + max_long_string);
        }

    }
}
