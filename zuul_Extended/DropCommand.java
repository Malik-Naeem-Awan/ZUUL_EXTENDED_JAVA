import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java. util. Iterator;
import java.util.*; 

/**
 * Write a description of class DropCommand here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DropCommand extends Command
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class DropCommand
     */
    public DropCommand() {}
    
    @Override
    public boolean execute(Player player)
    {   
        HashMap<String, Item> inventory= player.getItems();
        String in;
        if(player.getCurrentRoom().getItem() != null) {
            System.out.println("Room Already has a item so cannot drop!");
        }
        else if (!hasSecondWord()) {
            System.out.println("Drop what?");
        } 
        else if (!getSecondWord().equals("key")){
            System.out.println("There is nothing like that to take !");
        }
        else
        {
            System.out.println("Droped the key ! ");
            inventory.clear();
        }
        return false;
    }
}
