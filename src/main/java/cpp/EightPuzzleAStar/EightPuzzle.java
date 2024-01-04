package cpp.EightPuzzleAStar;

import java.util.Stack;

public class EightPuzzle 
{

    /***
     * Given a puzzle string, print the string in a 3x3 matrix.
     * @param input - puzzle
     */
    public static void printStringAsMatrix(String input) {
        if (input.length() != 9) 
            return;
        for (int i = 0; i < 9; i++) 
        {
            System.out.print(input.charAt(i) + " ");
            if ((i + 1) % 3 == 0) 
                System.out.println();
        }
    }

    /***
     * Count the number of inversions in a puzzle string.
     * @param puzzleString
     * @return
     */
    public static int CountInversions(String puzzleString)
    {
        int inversions = 0;
        char charArray[] = puzzleString.toCharArray();
        for (int i = 0; i < charArray.length; i++) 
            for (int j = i+1; j < charArray.length; j++) 
                if ( (charArray[i] != '0' && charArray[j] != '0') && charArray[i] > charArray[j])
                    inversions++;
        return inversions;
    }

    /***
     * Validate a string so that it is length 9 and made only of 0-8 chars.
     * @param input
     * @return
     */
    public static boolean isStringValidPuzzle(String input) 
    {
        if(input.length() != 9)
            return false;

        for (char c : input.toCharArray()) 
        {
            if (!Character.isDigit(c)) 
                return false;
            int digit = Character.getNumericValue(c);
            if (digit < 0 || digit > 8) 
                return false;    
        }
        return true;
    } 

    /***
     * Count the number of tiles not in there place
     * @param input
     * @return
     */
    public static int CountMisplaced(String input)
    {
        int misplaced = 0;
        
        char characters[] = input.toCharArray();
        for (int i = 0; i < characters.length; i++) 
        {
            if( ((int)(characters[i] - '0')) != i )
                misplaced++;
        }
        
        return misplaced;
    } 

    /***
     * Find the manhattan distance for each tile. Add up the distance of each tile from its goal tile.
     * @param input
     * @return
     */
    public static int CountTotalDistance(String input)
    {
        int totalDistance = 0;
        char characters[] = input.toCharArray();
        
        for (int i = 0; i < characters.length; i++) 
        {
            int charVal = (int) characters[i] - '0';
            if(charVal == 0) //Skip Pointer
                continue;

            if(charVal == i) // In Position
                continue;

            int currentCords[] = ToCoordinates(i);
            int GoalCords[] = ToCoordinates(charVal);
            
            totalDistance += calculateMDistance(GoalCords[0], GoalCords[1], currentCords[0], currentCords[1]); 
        }
        return totalDistance;
    }

    /***
     * given an x and y from an 3x3 matrix give the appropiate 9x1 index;
     * @param x
     * @param y
     * @return
     */
    public static int ToIndex(int x, int y) 
    {
        return y * 3 + x;
    }

    /***
     * given an index from a 9x1 array find the x and y for its 3x3 equivalent.
     * @param index
     * @return
     */
    public static int[] ToCoordinates(int index) 
    {
        int y = index / 3;
        int x = index % 3;
        return new int[]{x, y};
    }

    /***
     * Find the possible moves from x,y
     * @param row
     * @param col
     * @return
     */
    public static Stack<int[]> findMovesFrom(int row, int col)
    {
        Stack<int[]> moves = new Stack<>();

        if (row > 0)
            moves.add(new int[]{row - 1, col});

        if (row < 2)
            moves.add(new int[]{row + 1, col});

        if (col > 0)
            moves.add(new int[]{row, col - 1});
        
        if (col < 2)
            moves.add(new int[]{row, col + 1});
        
       return moves;
    }

    /***
     * 
     * @param x
     * @param y
     * @param x1
     * @param y1
     * @return
     */
    public static int calculateDistance(int x,int y, int x1, int y1) 
    {
        return (int) Math.sqrt(Math.pow((x1 - x)*10, 2) + Math.pow(y1-y*10, 2));
    }
    
    /***
     * 
     * @param x
     * @param y
     * @param x1
     * @param y1
     * @return
     */
    public static int calculateMDistance(int x,int y, int x1, int y1) 
    {
        return (int) (Math.abs((x-x1)) + Math.abs((y-y1)));
    }

  }
