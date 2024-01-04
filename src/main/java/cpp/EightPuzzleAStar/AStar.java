package cpp.EightPuzzleAStar;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Vector;

public class AStar 
{
    private PriorityQueue<Node> unexpandedNodes;
    private HashMap<String, Node> expandedNodes;
    private boolean useHueristicOne = false;

    public AStar(boolean useH1)
    {
        unexpandedNodes = new PriorityQueue<>(Comparator.comparingInt(node -> node.getTotalCost()));
        expandedNodes =  new HashMap<>();
        useHueristicOne = useH1;
    }

    public Stack<int[]> findMovesFrom(int row, int col)
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

    public Vector<Node> generateNeighbors(Node curNode)
    {  
        Vector<Node> neighbors = new Vector<>();  
        String puzzle = curNode.getPuzzleState();
        char puzzleChars[] = puzzle.toCharArray();

        int index =  puzzle.indexOf('0');
        int[] cords = EightPuzzle.ToCoordinates(index);

        Stack<int[]> moves = findMovesFrom(cords[0], cords[1]);
        while (!moves.empty()) 
        {
            int[] activeMove = moves.pop();
            int newIndex = EightPuzzle.ToIndex(activeMove[0], activeMove[1]);
            char swapval = puzzle.charAt(newIndex);

            puzzleChars[index] = swapval;
            puzzleChars[newIndex] = '0';

            String newPuzzle = new String(puzzleChars);

            if(expandedNodes.containsKey(newPuzzle))
                neighbors.add(expandedNodes.get(newPuzzle));
            else
                neighbors.add(new Node(newPuzzle));

            puzzleChars[index] = '0';
            puzzleChars[newIndex] = swapval;
        }

        return neighbors;
    }

    public Integer heuristic(Node node)
    {
        int h = 0;
        if(useHueristicOne)
            h += EightPuzzle.CountMisplaced(node.getPuzzleState());
        else
            h += EightPuzzle.CountTotalDistance(node.getPuzzleState());
        return h;
    }

    public int printPath(Node node)
    {
        int size = 0;
        Stack<Node> path = new Stack<>();

        Node activNode = node;

        while (activNode.getParentNode() != null) 
        {
            path.push(activNode);
            activNode = activNode.getParentNode();    
        }
        System.out.println("Start State: ");
        EightPuzzle.printStringAsMatrix(activNode.getPuzzleState());
        System.out.println();
        int steps = 1;
        size = path.size();
        while (!path.empty())
        {
            Node currentNode = path.pop();
            EightPuzzle.printStringAsMatrix(currentNode.getPuzzleState());
            System.out.print("HCost: ");
            System.out.println(currentNode.getHeuristic());

            System.out.print("Step: ");
            System.out.println(steps++);      
            System.out.println();      
        }
        return size;
    }

    public int performAStarSearch(Node startNode) 
    {
        startNode.setCost(0);
        startNode.setHeuristic(heuristic(startNode));
        unexpandedNodes.add(startNode);

        while(!unexpandedNodes.isEmpty()) 
        { 
            
            Node currentNode = unexpandedNodes.poll();

            if (currentNode.isGoalState()) 
            {
                return printPath(currentNode);
            }

            expandedNodes.put(currentNode.getPuzzleState(), currentNode);

            Vector<Node> neighbors = generateNeighbors(currentNode);
            for (Node neighbor : neighbors) 
            {
                int tentativeCost = currentNode.getCost() + 1;

                if(tentativeCost < neighbor.getCost()) //DEAFULTs HAVE MAX VAL || Already Visited Should Have Their Cost
                {
                    neighbor.setParentNode(currentNode);
                    neighbor.setHeuristic(heuristic(neighbor));
                    neighbor.setCost(tentativeCost);
                    if (!unexpandedNodes.contains(neighbor))
                        unexpandedNodes.add(neighbor);
                } 

            }
        }
        return -1;
    }

    public int getUnexpandedNodesSize()
    {
        return unexpandedNodes.size();
    }

    public int getExpandedNodesSize()
    {
        return expandedNodes.size();
    }

}
