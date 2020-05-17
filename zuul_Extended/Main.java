 

import java.util.Scanner;

/**
 * Main class dedicated to run the game
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Game G;
        boolean play_again = true;
        // Allows the player to play several times
        while (play_again)
        {
            System.out.println("---------------- Welcome to Extended Zuul Game entertainment ------------");
            System.out.println("- New Game : type '1' ");
            System.out.println("- (Default) Quit this awesome platform : type '0' ");
            Scanner answer = new Scanner(System.in);
            try 
            {
                // Create a new game
                if(answer.nextInt() == 1)
                {
                    G = new Game();
                    G.play();
                }
                // Quit the platform
                else 
                {
                    System.out.println("It's your choice.");
                    play_again = false;
                }
            }
            // Check if the entered command is different from the available choices.
            catch(Exception e)
            {
                System.out.println(e);
                System.out.println("Unkown command, sorry.");
            }
        }
        System.out.println("Thank you for playing. Goodbye. Keep it Up! You are a Gem!");
    }

}

