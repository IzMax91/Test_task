# Test_task
java version "23.0.1" 2024-10-15
Java(TM) SE Runtime Environment (build 23.0.1+11-39)
Java HotSpot(TM) 64-Bit Server VM (build 23.0.1+11-39, mixed mode, sharing)

#Launch instructions
- Open a command prompt (cmd) on your operating system. 
- Go to the directory where the file is located util.java using the cd command. 
- Compile the program using the command: java util.java.
- Run the compiled class with the necessary command line arguments: java util [-asfop] <input_files>
- Where:
  - -a mode for adding to existing files.
  - -s brief statistics only contain the number of items recorded in the outgoing files.
  - -f the complete statistics for numbers additionally contains the minimum and maximum values, the sum and the average.
    The complete statistics for the rows, in addition to their number, also contains the size of the shortest row and the longest.
  - -o set the path for the results.
  - -p sets the prefix of the output file names.
- After launching the program, it will process the specified input files and output statistics to the console.
