import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java. util. Iterator;
import java.util.*; 
/**
 * UseCommand Class
 * 
 * @author Alexandre Boursier and Nolan Potier
 * @version 2011.10.24
 */
public class UseCommand extends Command
{
    /**
     * Constructor for objects of class GoCommand
     */
    public UseCommand() {}

    /** 
     * Try to take an item if present into the room
     * We have to be in the good room
     * Returns always 'false'.
     */
    @Override
    public boolean execute(Player player)
    {
        HashMap<String, Item> inventory= player.getItems();
        if (!hasSecondWord()) {
            System.out.println("use what?");
        } 
        else if (player.getItems().get(getSecondWord().toLowerCase()) == null){
            System.out.println("There is nothing to use like that !");
        }
        else if (!player.getItems().get(getSecondWord().toLowerCase()).useItem(player)) {
            System.out.println("There is nothing to use there !");              
        }
        else{
            System.out.println("used! "+ player.getItems().get(getSecondWord().toLowerCase()).useItem(player));
            inventory.clear();
        }
        return false;
    }
}
