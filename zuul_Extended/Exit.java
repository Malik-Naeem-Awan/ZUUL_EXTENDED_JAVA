
/**
 * Exit.java. Stores information on exits, non-lockable
 * travel avenues in Zuul.
 */
public class Exit
{
    private String direction;
    private Room neighbor;
    private  boolean islocked;
    /**
     * Constructor for objects of class Exit
     */
     
     public Exit()
     {
     }
     
    public Exit(String direction, Room neighbor)
    {
        // initialise instance variables
        this.direction = direction;
        this.neighbor = neighbor;

    }
    
    public String getDirection()
    {
        // put your code here
        return direction;
    }
    
    public Room getNeighbor()
    {
        return neighbor;
    }
    
    public void setDirection(String direction)
    {
        this.direction = direction;
    }
    
    public void setNeighbor(Room neighbor)
    {
        this.neighbor = neighbor;
    }
    
     public void setislocked(boolean islocked)
    {
        this.islocked = islocked;
    }
    
    public boolean islocked()
    {
        return islocked;
    }
    
    
    
}
