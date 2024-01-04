# Eight Puzzle Solver Readme

## Overview
This Java program is an implementation of an Eight Puzzle solver using the A* search algorithm. The solver takes an initial puzzle state as input and attempts to find a sequence of moves that transforms the initial state into the goal state.

## Usage
To run the program, execute the `main` method in the `Main` class. The program can be run with or without command-line arguments.

If command-line arguments are provided, the program checks if the first argument is "test". If so, it runs a test method `Test100()` and exits.

If no command-line arguments are provided, the program prompts the user to choose one of the following options:
1. Generate a random solvable puzzle.
2. Read a puzzle from a file.
3. Input a puzzle manually.

After obtaining the initial puzzle state, the program checks if the puzzle is solvable. If solvable, it proceeds to solve the puzzle using the A* search algorithm with either heuristic function H1 or H2, depending on user input.

## Input Options
### 1. Generate a Random Solvable Puzzle
Generates a random puzzle state, ensuring it is solvable by checking the number of inversions.

### 2. Read a Puzzle from a File
Prompts the user for a file name and reads the puzzle state from the specified file.

### 3. Input a Puzzle Manually
Prompts the user to input the puzzle manually through the console.

## Heuristic Selection
The program allows the user to choose between two heuristic functions, H1 and H2, for the A* search algorithm.

## Compile and Build Instructions
To compile and build the project, follow these steps:

1. **Install Maven:**
   Make sure you have Maven installed on your system. You can download Maven from [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi) and follow the installation instructions.

2. **Navigate to Project Directory:**
   Open a terminal or command prompt and navigate to the directory where your `pom.xml` file is located.

3. **Compile and Build:**
   Run the following command to compile and build the project:

   ```bash
   mvn clean package
   ```

   This command will download the required dependencies, compile the source code, run tests, and package the application into a JAR file.

4. **Run the Application:**
   After a successful build, you can run the application using the following command:

   ```bash
   java -jar target/EightPuzzle-1.0-SNAPSHOT.jar
   ```

   Make sure to replace `target/EightPuzzle-1.0-SNAPSHOT.jar` with the actual path to your generated JAR file.

   If you have specified the main class and classpath correctly in the `maven-jar-plugin` configuration, the application should execute.


## Example Usage
```bash
java -jar EightPuzzle-1.0-SNAPSHOT.jar test    
# Run a test method
java -jar EightPuzzle-1.0-SNAPSHOT.jar         
# Manually choose puzzle input method
```