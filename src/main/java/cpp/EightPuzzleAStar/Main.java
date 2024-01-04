package cpp.EightPuzzleAStar;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main 
{
    /***
     * Given the filepath read a 3x3 matrix from it.
     * @param filePath
     * @return
     */
    public static String readMatrixFromFile(String filePath) 
    {
        StringBuilder resultString = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
            for (int i = 0; i < 3; i++) 
            {
                String[] numbers = br.readLine().split("\\s+");
                for (String number : numbers) 
                    resultString.append(number);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        if(!EightPuzzle.isStringValidPuzzle(resultString.toString()))
        {
            System.err.println("File was not formatted correctly.");
            return null;
        }

        return resultString.toString();
    }

    /***
     * get 1,2 or 3 from the user.
     * @return
     */
    public static int getUserInput() 
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Select Input Method:");
        System.out.println(" 1 - Random Puzzle.");
        System.out.println(" 2 - From File");
        System.out.println(" 3 - From Console");
        System.out.print(" >> ");

        int attempts = 3;
        int choice = 0;
        boolean isValidInput = false;

        while (!isValidInput && (attempts-- != 0)) 
        {
            try 
            {
                String input = reader.readLine();
                choice = Integer.parseInt(input);
                if(choice > 0 && choice < 4)
                    isValidInput = true;
            } 
            catch (NumberFormatException | IOException e) 
            {
                if(attempts > 0)
                {
                    System.out.println("Invalid input. Please try again");
                    System.out.print(" >> ");
                }
                else
                    System.out.println("Good Bye");
            }
        }

        return choice;
    }
    
    /***
     * retrive an existing file name from the user.
     * @return
     */
    public static String getFileName() 
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter file name: ");

        String fileName = null;
        int attempts = 3;
        boolean isValidInput = false;

        while (!isValidInput && (attempts-- != 0)) 
        {
            try
            { 
                File file = new File(reader.readLine().trim());
                if(file.exists())
                {
                    fileName = file.getPath();
                    isValidInput = true;
                }
                else
                {
                    System.out.println("File does not exist");
                    System.out.print("Enter file name: ");
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    /***
     * determine which hueristic function to use based on user input.
     * CLOSES SYSTEM.IN
     * @return
     */
    public static boolean useH1orH2() 
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = "";
        System.out.println("Use H1? Else will use H2." + " (yes/no)");
        try
        {
            userInput = reader.readLine().toLowerCase();

            while (!userInput.equals("yes") && !userInput.equals("no")) 
            {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                userInput = reader.readLine().toLowerCase();
            }
        }
        catch (IOException IOE)
        {
            IOE.printStackTrace();
        }
        
        try 
        {
            reader.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        return userInput.equals("yes");
    }

    /***
     * create a random puzzle string
     * @return
     */
    public static String generateRandomString() 
    {
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i <= 8; i++) 
            digits.add(i);

        Collections.shuffle(digits);

        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < 9; i++)
            randomString.append(digits.get(i));
        
        return randomString.toString();
    }

    /***
     * get puzzle from console
     */
    public static String getPuzzleFromConsole()
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter 8-Puzzle: ");

        String puzzle = null;
        int attempts = 3;
        boolean isValidInput = false;

        while (!isValidInput && (attempts-- != 0)) 
        {
            try 
            {
                String input = reader.readLine();
                if(EightPuzzle.isStringValidPuzzle(input))
                {
                    puzzle = input;
                    isValidInput = true;
                }
                else
                {
                    System.out.println("Not A Valid Puzzle");
                    if(attempts > 0)
                        System.out.print("Enter 8-Puzzle: ");
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }

        return puzzle;
    }

    /***
     * Main Function. Entry Point
     * @param args
     */
    public static void main(String[] args) 
    {
        if(args.length > 0)
        {
            String active = args[0];
            if(active.equals("test"))
            {
                Test100();
                return;
            }
        }
        String puzzleString = null;
        switch (getUserInput()) 
        {
            case 1:
                while (puzzleString == null || EightPuzzle.CountInversions(puzzleString)%2 != 0) 
                    puzzleString = generateRandomString();
                break;
            case 2:
                String file = getFileName();
                if(file == null)
                    return;
                puzzleString = readMatrixFromFile(file);
                break;

            case 3:
                puzzleString = getPuzzleFromConsole();
                break;

            default:
                System.err.println("Failed To GET USER INPUT. GOODBYE");
                return;
        }
        
        if(puzzleString == null)
            return;
        
        AStar solver = new AStar(useH1orH2());
        int inv = EightPuzzle.CountInversions(puzzleString);
        if (inv % 2 == 0)
        {
            long start = System.currentTimeMillis();
            solver.performAStarSearch(new Node(puzzleString));
            long elapse = System.currentTimeMillis() - start;
            
            System.out.print("Elapsed Time: ");
            System.out.print(String.valueOf(elapse));
            System.out.println();
        }
        else
            System.out.println("Unsolveable");

    }

    /***
     * Test randomly generate puzzles and gather data.
     */
    public static void Test100()
    {
        int size = 100;
        int results[] = new int[size];
        int unexpandedNodes[] = new int[size];
        int expandedNodes[] = new int[size];
        
        long times[] = new long[size]; 
        boolean useH1 = useH1orH2();
        for (int i = 0; i < size; i++) 
        {
            String puzzleString = null;
            while (puzzleString == null || EightPuzzle.CountInversions(puzzleString)%2 != 0) 
                puzzleString = generateRandomString();
            
            AStar solver = new AStar(useH1);
            long start = System.currentTimeMillis();
            results[i] = solver.performAStarSearch(new Node(puzzleString));
            times[i] = System.currentTimeMillis() - start;
            expandedNodes[i] = solver.getExpandedNodesSize();
            unexpandedNodes[i] = solver.getUnexpandedNodesSize();
        
        }

        System.out.println(Arrays.toString(results));
        calculateAndPrintAverage("STEP COST", results);
        System.out.println();

        System.out.println(Arrays.toString(times));
        calculateAndPrintAverage(times);
        System.out.println();

        System.out.println(Arrays.toString(expandedNodes));
        calculateAndPrintAverage("ExpandedNodes Count",expandedNodes);
        System.out.println();

        System.out.println(Arrays.toString(unexpandedNodes));
        calculateAndPrintAverage("UnexpandedNodes Count",unexpandedNodes);
        System.out.println();
    }
    
    /***
     * print average of int []
     * @param array
     */
    public static void calculateAndPrintAverage(String id, int[] array) 
    {
        if (array.length == 0) 
            return;

        int sum = 0;
        for (int num : array) 
            sum += num;
        
        double average = (double) sum / array.length;
        System.out.println("The average " + id + " is: " + average);
    }

    /***
     * print average of long[] 
     * @param array
     */
    public static void calculateAndPrintAverage(long[] array) 
    {
        if (array.length == 0) 
            return;
        
        long sum = 0;
        for (long num : array) 
            sum += num;
        
        double average = (double) sum / array.length;
        System.out.println("The average ELAPSED MSECONDS is: " + average);
    }

}
 

