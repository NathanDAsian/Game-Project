/* 
 * Game.java
 * Computer Science CPT
 * Buckshot roulette
 * Eric, Marcus
 */

import java.util.Random;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

 // Class that controls opponent's (computer's) actions
public class Computer extends Player {
  private boolean knowLive = false;
  private boolean shoot;

  // Constructor for name
  public Computer(String name) {
    super(name);
  }

  // Computer's text is colored red, Nathan, Eric
  private String spokenText(String prompt) {
    System.out.print("\033[38;5;1m  > \033[38;5;1m" + (prompt != null ? prompt : ""));
    Scanner scanner = new Scanner(System.in);
    String userInput = scanner.nextLine();
    System.out.print("\033[0m");
    return userInput;
} 


  // Pauses thread so that player can read Nathan
  private void pauseUntilEnter() {
    System.out.println("Press Enter to continue...");
    try {
      System.in.read();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Handles logic for opponent's turn, Eric, Cheryl, Marcus
  public void opponentTurn(ArrayList<Item> items, Player player, Computer opponent, Gun gameGun) {
    Random random = new Random();
    boolean useItem = !items.isEmpty() && random.nextBoolean(); // Checks if computer can use item
    boolean run = gameGun.isEmpty();

    if (!run) {
      spokenText("My turn... hahaha...");
      pauseUntilEnter();
      Game.clearScreen();
      if (opponent.checkHandcuffed()) {
         spokenText("Im cuffed, I guess not");
        player.setTurnToTrue();
      }

      if (!player.checkTurn()) {
          if (useItem && items.size() > 0) {  // Ensure items are not empty
            int itemIndex = random.nextInt(items.size()); // Safely generate a valid index
            Item selectedItem = items.get(itemIndex);  // Declaring selectedItem and itemIndex
            spokenText("I (Dealer) use " + selectedItem.getName());
            pauseUntilEnter();

           switch (selectedItem.getName()) {
            case "HandCuffs":
                if (!player.checkHandcuffed()) {
                player.setHandcuffed();
                items.remove(itemIndex);
                spokenText("(Dealer) You're cuffed now... its getting fun"); // Ayyoo
                pauseUntilEnter();
             }
             break;
  
          case "Saw":
              if (!gameGun.checkSaw()) {
                gameGun.setSawToTrue();
                System.out.println("*Dealer saws off the gun to deal double damage*");
                spokenText("(Dealer) You can suffer now");
                pauseUntilEnter();
                items.remove(itemIndex);
              }
              break;

          case "Cigarettes":
              addHealth(1);
              spokenText("I feel refreshed... like I can take one more shot");
              items.remove(itemIndex);
              pauseUntilEnter();
              break;

          case "MagGlass":
            gameGun.useMagGlass();
            System.out.println("*Dealer uses magnifying glass*");
            spokenText("(Dealer) hahaha...");
            items.remove(itemIndex);
            pauseUntilEnter();
            break;

            default:
            System.out.println("Unknown item. Nothing happens.");
            break;
        }
      }
          // Always shoot if magnifying glass uses live round
          if (gameGun.isEmpty()) {
            return;
          } else {
            if(gameGun.checkFirstBullet()) {
              System.out.println("[Dealer] points the gun at you");
              System.out.println("The gun fires...");
              gameGun.shoot(player);
              player.setTurnToTrue();
              return;
            } else {
              System.out.println("[Dealer] takes the shot at himself.");
              System.out.println("[Dealer] gets another turn");
              gameGun.shoot(opponent);
            }
              
          } 
      }
    if (opponent.checkHandcuffed()) opponent.setHandcuffed();
    }
  }
}
