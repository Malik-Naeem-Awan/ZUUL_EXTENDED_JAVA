import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 * Write a description of class InventoryCommand here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class InventoryCommand extends Command
{
    // instance variables - replace the example below with your own
  
    /**
     * Constructor for objects of class InventoryCommand
     */
    public InventoryCommand(){ }
    
    @Override
    public boolean execute(Player player)
    {   
        
        if(player.getItems()!=null)
        {
          String returnString = "Inventory:";
          HashMap<String, Item> inventory= player.getItems();
          System.out.println(returnString);
        
          for ( String key : inventory.keySet() ) {
              System.out.println(key);
          } 
        }
        return false;
    }
}