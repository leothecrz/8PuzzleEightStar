package cpp.EightPuzzleAStar;


public class Node 
{

    private String PuzzleState;
    private Node ParentNode;
    
    private Integer Cost;
    private Integer Heuristic;


    public Node(String puzzle)
    {
        PuzzleState = puzzle;
        Cost = Integer.MAX_VALUE;
        Heuristic = Integer.MAX_VALUE;
    }
    
    public boolean isGoalState()
    {
        if(PuzzleState.equals("012345678"))
            return true;
        return false;   
    }

    public void setCost(Integer cost) 
    {
        Cost = cost;
    }

    public void setHeuristic(Integer heuristic) 
    {
        Heuristic = heuristic;
    }

    public void setParentNode(Node parentNode) 
    {
        ParentNode = parentNode;
    }

    public Integer getTotalCost() 
    {
        return Cost + Heuristic;
    }    

    public Integer getCost() 
    {
        return Cost;
    }
    
    public Integer getHeuristic() 
    {
        return Heuristic;
    }

    public Node getParentNode() 
    {
        return ParentNode;
    }

    public String getPuzzleState() 
    {
        return PuzzleState;
    }
    
}
