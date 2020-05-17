 

/**
 * BeamerCommand Class
 */
public class BeamerCommand extends Command
{
    /**
     * Constructor for objects of class GoCommand
     */
    public BeamerCommand() {}

    /** 
     * Try to use beamer device. When you charge the beamer, it memorizes the current room.
     * When you fire the beamer, it transports you immediately back to the room it was
     * charged in..
     * Return always false
     */
    @Override
    public boolean execute(Player player)
    {
        if (!hasSecondWord()) {
            // if there is no second word, we don't know what to do...
            System.out.println("Beamer what ? (charged or fired)");
        }

        String action = getSecondWord();

        if (action.equals("charged")) {
            Game.setBeamerRoom(player.getCurrentRoom());
            System.out.println("This room is charged to beamer !");
        } else if (action.equals("fired")) {
            player.setCurrentRoom(Game.getBeamerRoom());
            System.out.println(player.getCurrentRoom().getLongDescription());
        } else {
            System.out.println("Invalid command beamer !");
        }
        return false;
    }
}
