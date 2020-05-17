
/**
 * Door.java. Stores information and provides locking
 * and unlocking of doors, lockable exits in Zuul.
 */
public class Door extends Exit
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class Door
     */
    public Door(String direction, Room neighbor, boolean islocked)
    {
        // initialise instance variables
        this.setDirection(direction);
        this.setNeighbor(neighbor);
        this.setislocked(islocked);
    }
     
}
