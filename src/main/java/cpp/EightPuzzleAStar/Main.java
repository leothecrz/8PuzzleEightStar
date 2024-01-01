package cpp.EightPuzzleAStar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main 
{
    /***
     * 
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

        return resultString.toString();
    }

    /***
     * 
     * @param args
     */
    public static void main(String[] args) 
    {


        AStar solver = new AStar();

        String puzzleString = "021345678";
        int inv = EightPuzzle.CountInversions(puzzleString);
        if (inv % 2 == 0)
            solver.performAStarSearch(new Node(puzzleString));
        else
            System.out.println("Unsolveable");

        System.out.println(inv);

    }

}
 

