import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  User 
 *  is wandering at the airport and player main focus is to get to the 
 *  aeroplane through different procesdures required by the game
 *  That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

public class Game 
{
    private Parser parser;
    private static Player player;
    private NPC porter;
    // Count the number of current number of moves
    private static int numberOfMoves;
    // Fix a limit to the number of moves
    public static boolean decision;
    private static int limitOfMoves;
    // Fix a number of rooms for choosing the teleport room
    private static final int NB_ROOM_TELEPORT = 8;
    // Build a list which contains all the current rooms of the game#
    
    

        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        numberOfMoves = 0;
        player = new Player();
        
        createRooms();
        parser = new Parser();

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, gallery, boarding, aeroplane , waiting_room, resturant, ticket_office, money_exchange;
      
        // create the rooms
        outside = new Room("outside the main entrance of the Airport");
        theatre = new Room("in a Airport theatre");
        pub = new Room("in the Airport pub");
        gallery = new Room("in a Airport Gallery");
        aeroplane = new Room("in the Aeroplane");
        boarding= new Room("At the Boarding!");
        waiting_room= new Room("in a Airport Waiting Room!");
        resturant= new Room("in a Airport resturant!");
        ticket_office= new Room("in a ticket office !"); 
        money_exchange= new Room("in a Money Exchange office !");
        
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", gallery);
        outside.setExit("dennis", pub);
        outside.setExit("up" ,ticket_office);

        
        outside.setDoor("Exchange", money_exchange,false);
        money_exchange.setExit("down", outside);
        
        theatre.setExit("west", outside);
        ticket_office.setExit("east", boarding);
        ticket_office.setExit("down", outside);
        boarding.setExit("up", outside);

        
        pub.setExit("undennis", outside);

        gallery.setExit("north", outside);
        gallery.setExit("east", outside);

        waiting_room.setDoor("RunWay", aeroplane,true);

        boarding.setDoor("Waiting", waiting_room,true);
        // initialise room items
        outside.setItem("flask", "pretty spangly. It looks like you've got ye flask");
        outside.setItem("apple", "a delicious apple");
        theatre.setItem("sandwich", "a delicious sandwich");
        theatre.setItem("burger", "a spicy burger");
        pub.setItem("rice", "a rice");
        pub.setItem("jelly", "just some jelly");

        gallery.setItem("jelly", "just some jelly");
        gallery.setItem("apple", "a delicious apple");


        aeroplane.setItem("jelly", "just some jelly");
        aeroplane.setItem("burger", "a spicy burger");
        
        
        boarding.setItem("ticket", "Checked ticket");
        boarding.setItem("luggage", "your luggage is checked");
       
        waiting_room.setItem("apple", "a delicious apple");
        waiting_room.setItem("jelly", "just some jelly");

        resturant.setItem("fish", " spicy fish");
        resturant.setItem("burger", "spicy burger");
       
        ticket_office.setItem("ticket", "take a ticket");
        ticket_office.setItem("luggage", "your luggage is checked");

        
        money_exchange.setItem("currence", "exchange currency");
        money_exchange.setItem("gold", "buy some gold");

        player.addInventory("bagel", "half a blueberry bagel");

        outside.addNPC("Dwarf", "A helpful Dwarf", "key", "a sweet key");

        player.setCurrentRoom(outside);  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Goodbye.");
    }

        /**
     * Choosing the level of the game :
     * - Easy is for beginners 
     * - Medium brings a little bit more challenge
     * - Hard is the "no-mistake way"
     * 
     */
    private void chooseLevel()
    {
        // Choosing a level (asking to the user through the terminal)
        Scanner reader = new Scanner(System.in);
        System.out.println("Please choose a level : Easy for peasant(0) - Medium for warriors (1) - Hard for gods (2)");
        // Find the chosen level and alter the number of moves accorfing to the chosen one
        try {
            switch (reader.nextInt()) {
            case 0:
                limitOfMoves = 20;
                System.out.println("You've chosen the easy way to win ! - Number of moves : " + limitOfMoves);
                break;
            case 1:
                limitOfMoves = 16;
                System.out.println("You've chosen the medium level :)- Number of moves : " + limitOfMoves);
                break;
            case 2:
                limitOfMoves = 14;
                System.out.println("It's gonna be hard this way :@  - Number of moves : " + limitOfMoves);
                break;
            default:
                limitOfMoves = 20;
                System.out.println("Unkown command - Default level : Easy - Number of moves : " + limitOfMoves);
                break;
            }
        } catch(Exception e){
            limitOfMoves = 20;
            System.out.println("Unkown command - Default level : Easy - Number of moves : " + limitOfMoves);
        }
    }

    /**
     * Counting the current move of the player
     * @return false if the player has executed too many moves, true otherwise
     */
    public static boolean countMove(){
        // Count a move
        numberOfMoves++;

        // Give some informations concerning the number of moves
        if (numberOfMoves < limitOfMoves) {
            System.out.println("Current number of moves : " + numberOfMoves);
            System.out.println("Moves left : " + (limitOfMoves - numberOfMoves));
            return false;
            // Ending the game if the number of moves is reached
        } else {
            System.out.println("You have reached the maximum number of moves");
            System.out.println("By the way, GAME OVER ! ");
            System.out.println();
            System.out.println();
            return true;
        }
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("You are wandering at the airport" ) ;
        System.out.println("Your main focus is to get to the aeroplane"); 
        System.out.println("following different procesdures required by Game"); 
        
        chooseLevel();
        
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println(player.getInventoryString());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            wantToQuit = goRoom(command);
        else if (commandWord.equals("drop"))
            dropItem(command);
        else if (commandWord.equals("take"))
            takeItem(command);
        else if (commandWord.equals("eat"))
            eatItem(command);
        else if (commandWord.equals("examine"))
            examineItem(command);
        else if (commandWord.equals("inventory"))
            printInventory(command);           
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the Airport.");
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println(player.getInventoryString());
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. If there's a door, try
     * to go through it. If it's locked, print an error message.
     */
    private boolean goRoom(Command command) 
       {
        if(!command.hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return false;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        // This is the potential next room, via exit
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        // This is the potential next room, via door
        Room nextDoor = player.getCurrentRoom().getDoor(direction);
        
        if (nextDoor != null)
        {
            if(player.checkKey()){
                
                
                player.setCurrentRoom(nextDoor);
                if(player.getCurrentRoom().getShortDescription()=="in the Aeroplane"){
                    System.out.println("Congratulations!");
                    System.out.println("You have won the game, well done Malik;)");
                    System.out.println();
                    System.out.println();  
                    System.out.println("Play Again & Enjoy Our Game! Thanks!)");
                    return true;
                    
                }
             
                System.out.println(player.getCurrentRoom().getShortDescription());
                System.out.println(player.getCurrentRoom().getLongDescription());
                System.out.println(player.getInventoryString());
                boolean decision = Game.countMove();     
                return decision;
            }
            else{
                System.out.println("You don't have ticket! get a ticket first");
            boolean decision = Game.countMove();     
            return decision;
            }        
        }

        if (nextRoom == null)
            System.out.println("There is no exit!");
        else
            {
                player.setCurrentRoom(nextRoom);
                System.out.println(player.getCurrentRoom().getLongDescription());
                System.out.println(player.getInventoryString());
                boolean decision = Game.countMove();     
                return decision;
            }
        boolean decision = Game.countMove(); 
        return decision;  
    }

    
    /** 
     * "Drop" was entered. Drops the specified item if it's in 
     * the player's inventory, otherwise throws an error.
     */
    private void dropItem(Command command)
    {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }
        
        String droppedItem = command.getSecondWord();
        
        // Drop it
        
        Item temp = player.dropInventory(droppedItem);
        if (temp != null)
        {
            
            // Add it to the room's items
            player.getCurrentRoom().setItem(temp.getName(), temp.getDescription());
            
            // Refresh inventory
            System.out.println(player.getCurrentRoom().getLongDescription());
            System.out.println(player.getInventoryString());
        }      
        
    }
    
     /** 
     * "inventory" was entered. Takes the specified items in inventory, otherwise tells no inventory
     */
    private void printInventory(Command command)
    {
        if(player.getInventoryString()!=null){
            System.out.println(player.getInventoryString());
        }
        else{
            System.out.println("No Inventory for player");          
        }
         
        
    }
    
    
    /** 
     * "Take" was entered. Takes the specified item if it's in 
     * the room, otherwise throws an error.
     */
    private void takeItem(Command command)
    {
       if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Take what?");
            return;
        }
        
        String desiredItem = command.getSecondWord();
              
        // Remove it from the room's items
        Item temp = player.getCurrentRoom().delItem(desiredItem);
        if (temp != null)
        {     
            // Add it to player's inventory
            player.addInventory(temp.getName(), temp.getDescription());
            
            // Refresh inventory
            System.out.println(player.getCurrentRoom().getLongDescription());
            System.out.println(player.getInventoryString());
        }
    }
    
    /** 
     * "Trade" was entered. Trades the specified item if it's in 
     * the player's inventory, otherwise throws an error.
     */
    private void eatItem(Command command)
    {
       if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Trade what?");
            return;
        }
        
        String eatingItem = command.getSecondWord();
        
        // get NPCs in room
        
        NPC npc = player.getCurrentRoom().getNPC();
                   
        // remove NPC item
        
        Item tempItem = npc.dropInventory();
        
        // add NPC item to player inventory
        
        player.addInventory(tempItem);
        
        // remove player item
        
        Item tempPlayerItem = player.dropInventory(eatingItem);
        
        if (tempPlayerItem == null)
        {
            npc.addInventory(tempItem);
            return;
        }
        
        // add player item to NPC inventory
        
        npc.addInventory(tempPlayerItem);
        
        // print out inventory
        
        System.out.println(player.getInventoryString());
    }
    
    
    /** 
     * "Examine" was entered. This prints out the name and description
     * of the specified item, so that everyone can see how clever I've
     * been with the descriptions.
     */
    private void examineItem(Command command)
    {
       if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Examine what?");
            return;
        }
            
        System.out.println(player.getExamineString(command.getSecondWord()));
        return;
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
    
      /**
     * @return the player
     */
    public static Player getPlayer() {
        return player;
    }

}
