package cpp.EightPuzzleAStar;

public class EightPuzzle 
{

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

    public static int ToIndex(int x, int y) 
    {
        return y * 3 + x;
    }

    public static int[] ToCoordinates(int index) 
    {
        int y = index / 3;
        int x = index % 3;
        return new int[]{x, y};
    }

    public static int calculateDistance(int x,int y, int x1, int y1) 
    {
        return (int) Math.sqrt(Math.pow((x1 - x)*10, 2) + Math.pow(y1-y*10, 2));
    }
    
    public static int calculateMDistance(int x,int y, int x1, int y1) 
    {
        return (int) (Math.abs((x-x1)) + Math.abs((y-y1)));
    }

  }
