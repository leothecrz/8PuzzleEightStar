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
        ParentNode = null;
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

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
            return true;
        
        if (obj == null || getClass() != obj.getClass())
            return false;

        Node otherNode = (Node) obj;
        return PuzzleState.equals(otherNode.PuzzleState);
    }
    
}
