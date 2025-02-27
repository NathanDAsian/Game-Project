
/* 
 * Game.java
 * Computer Science CPT
 * Buckshot roulette
 * Eric, Cheryl, Marcus
*/

import javax.swing.*;
import javax.sound.sampled.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
  public Game() {
    gameLogic(); 
  }

  // Method that generates random items from the item class, Eric
  public static ArrayList<Item> generateRandomItems(int count, ArrayList<Item> initialItems) {
    Random random = new Random();
    for (int i = 0; i < count; i++) {
      int choice = random.nextInt(5);
      switch (choice) {
        case 0: initialItems.add(SelfItem.createCigarettes());
          break;
          
        case 1: case 2: case 3: 
          int gunItemChoice = random.nextInt(3);
          switch(gunItemChoice) {
            case 0:
              initialItems.add(GunItem.createSaw()); 
              break;
            case 1:
              initialItems.add(GunItem.createMagnifyingGlass()); 
              break;
          }
          break;
        case 4: 
          initialItems.add(OpponentItem.createHandcuffs());
          break;
      }
    }
    return initialItems;
  }

  // Pauses so that user can read, Eric
  public static void pauseUntilEnter() {
    System.out.println("Press Enter to continue...");
    try {
      System.in.read();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Simulates a player to choose an item in their item box. Used items are removed. 
  // Non-stackable items (cuffs, saw) are limited from being used twice, Eric, Cheryl
  public static void chooseItem (ArrayList<Item> items, Player player, Player opponent, Gun gameGun) {
    int validChoice = items.size();
    Scanner sc = new Scanner(System.in);
    boolean cont = true;
    while (cont) { // Until gun is shot
      
      for (int i = 0; i < items.size(); i++) { // Print item list
        System.out.println((i+1) + " " + items.get(i));
      }
      
      System.out.println("Which item do I use? <0 to shoot now, 1, 2, 3, etc>");
      
      if (!sc.hasNextInt()) { // Error trap any non-number 
          System.out.println("Invalid input. Please enter a number.");
          sc.next();
          continue;
      }
      
      int choice = sc.nextInt();
      if (choice == 0) {
        cont = false;
        System.out.println("Shoot self <1>, or shoot enemy? <2>");
        switch (sc.nextInt()) {
          case 1: 
            if (gameGun.checkFirstBullet()) {
              gameGun.shoot(player); 
              player.setTurnToFalse();
            } else if (!gameGun.checkFirstBullet()) {
              gameGun.shoot(player);
            }
            break;
          case 2: gameGun.shoot(opponent); player.setTurnToFalse(); break;
            
          default: 
            System.out.println("Oops, missed the trigger. No one was shot");
            pauseUntilEnter();
            clearScreen();
            
        }
      } else if (choice > 0 && choice <= items.size()) {
        Item selectedItem = items.get(choice - 1);  // Adjust for zero-based indexing
        System.out.println("You selected: " + selectedItem);
        System.out.println("");
        switch (selectedItem.getName()) {
            
          case "HandCuffs": // Handcuffs Item
            if (opponent.checkHandcuffed()) {
              System.out.println("Opponent is already cuffed");
            } else {
              opponent.setHandcuffed();
              items.remove(selectedItem);
            } 
            break;
            
          case "Saw": // Saw Item
            if (gameGun.checkSaw()) {
              System.out.println("Gun is already sawed off");
            } else {
              gameGun.setSawToTrue();
              items.remove(selectedItem);
            }
            break;
          case "Cigarettes": // Cigarettes Item
             player.addHealth(1);
             items.remove(selectedItem);
             System.out.println("I feel better, maybe I might be able to take another shot");
             System.out.println("Maybe " + player.getHealth() + " More...");
             break;
            
          case "MagGlass": // Magnifying Glass Item
            gameGun.useMagGlass();
            items.remove(selectedItem);
            break; 
            
          default: System.out.println("Bug encountered"); break;
        }
        
      } else {
        System.out.println("Invalid choice");
      }
    }
  }

  // Simulates a player turn, pass the player and their items Eric & Marcus
  public static void playerTurn (ArrayList<Item> items, Player player, Player opponent, Gun gameGun) {
     if (player.checkHandcuffed()) {
       System.out.println("Handcuffed, can't do anything");
       player.setTurnToFalse();
       player.setHandcuffed();
       return;
     }
     chooseItem(items, player, opponent, gameGun);
  }

  // Game loop of game, shooting, reloading, item, win function, Eric
  public static void gameLoop (ArrayList<Item> playerItems, ArrayList<Item> opponentItems, Player player, Computer opponent, Gun gameGun) {
    while (player.checkTurn() && !gameGun.isEmpty()) { // Run if gun is not empty
      playerTurn(playerItems, player, opponent, gameGun); 
        if (opponent.getHealth() <= 0) {
          clearScreen();
          System.out.println("Player wins");
          break;
         } 
        if (player.getHealth() <= 0) {
          clearScreen();
          System.out.println("Opponent wins");  
          break;
        }
    }
    
      if (!gameGun.isEmpty() && opponent.getHealth() > 0 ) opponent.opponentTurn(opponentItems, player, opponent, gameGun); // Run if gun is not empty
      pauseUntilEnter();
  }

  // Handles the iterations of the text based Game, Eric,
  public static void gameLogic() {
    displayRules();
    clearScreen();
    Gun gameGun = new Gun();
    Files file = new Files();
    Player player = new Player("player");
    Computer opponent = new Computer("opponent");
    ArrayList <Item> playerItems = new ArrayList<>();
    ArrayList <Item> opponentItems = new ArrayList<>();
    System.out.println("Loading gun...");
    gameGun.determineChamber(); 
    gameGun.showChamber();
    System.out.println("I see...");
    System.out.println("");
    pauseUntilEnter();
    
    opponentItems = generateRandomItems(1, opponentItems);
    playerItems = generateRandomItems(3, playerItems);

    while (opponent.getHealth() > 0 && player.getHealth() > 0) {
      clearScreen();
      if (gameGun.isEmpty()) {
        System.out.println("The gun is empty. Reloading...");
        opponentItems = generateRandomItems(1, opponentItems);
        playerItems = generateRandomItems(2, playerItems);
        gameGun.determineChamber();
        gameGun.showChamber();
        System.out.println("I see...");
        pauseUntilEnter();
      }
      gameLoop(playerItems, opponentItems, player, opponent, gameGun);
    }
    if (opponent.getHealth() <= 0) { // When player wins, they will place their name into a leaderboard
      file.getName();
    }
    file.showFile();
  }  

  // Clears the terminal screen, Marcus
  public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
  }  

  // Shows the rules page, prints a Gun image, Cheryl
  public static void displayRules() {
    Scanner sc = new Scanner(System.in);
    System.out.println("""
, ______________________________________       
|_________________,----------._ [____]  ""-,__  __....-----=====
               (_(||||||||||||)___________/   ""                |
                  `----------' -------[ ))"-,                   |
                                       ""    `,  _,--....___    |
                                               `/           "-"
_________________________________________________________________
                       BUCKSHOT ROULETTE 
                  Eric, Cheryl, Marcus, Nathan
_________________________________________________________________
                       
                       """);
    System.out.println("Would you like to see the rules? <0 for yes, 1 for no>");
    int choice = sc.nextInt();
    while (choice == 0) {
      clearScreen();
      System.out.println("""
                        GAME LOOP: 
                           1. Your turn first every time the gun is loaded, lucky you.
                           2. Remember the type of bullets in the gun! 
                           3. You are given items every time the gun is racked
                           4. You are allowed to use any of your items before you shoot
                           5. If you shoot self, and its a blank, you go again.  
                           6. If you shoot enemy, enemy goes (unless handcuffed)

                         ITEMS:   
                           1. Cigarettes: Get 1+ lives. 
                           2. Magnifying glass: Tells you what bullet is next
                           3. Handcuffs: If you shoot the enemy, you are given 1 more round
                           4. Saw: The next bullet will do double damage. Buff runs out when gun is shot(regardless of blank or lives)

                          MISC:
                           1. Beating the AI will give place your name in the special files list
                           2. AI will use items at random
                           3. # of lives are uncapped
                          5. Be careful! Shooting yourself if the round is live will give the turn to the next player
                        """);
      pauseUntilEnter();
      choice = -1;
    }
  }
}


