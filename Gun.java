/* 
 * Game.java
 * Computer Science CPT
 * Buckshot roulette
 * Eric, Cheryl, Marcus, Nathan
*/

import java.util.Random;
import java.util.ArrayList;

// Class that controls the number and type of bullet in the chamber
public class Gun {
  private ArrayList<Boolean> chamber = new ArrayList<Boolean>();
  private boolean sawUsed = false;

  // method that determines chamber size - Eric, Cheryl
  public int determineChamberSize() {
       Random rand = new Random();
       int mag = rand.nextInt(3);
       int bulletsInChamber = -1;
       switch (mag) {
         case 0: bulletsInChamber = 2; break;
         case 1: bulletsInChamber = 3; break;
         case 2: bulletsInChamber = 4; break;
       }
      return bulletsInChamber;
  }

    // method that determines type of bullet found in the chamber, Eric Marcus
  public ArrayList<Boolean> determineBullet(int bulletsInChamber) {
      Random rand = new Random();
      ArrayList<Boolean> bullets = new ArrayList<>();
      for (int i = 0; i < bulletsInChamber; i++) {
        bullets.add(rand.nextInt(2)==0);
      }
      return bullets;
  }

  // Method that determines the chamber size and type of bullet, Eric
  public void determineChamber() { 
    chamber = determineBullet(determineChamberSize());
  }

  // Method that says how many bullets, and how many are live and blank, Marcus Cheryl
  public void showChamber() {
    int live = 0, blank = 0;
    for (Boolean shell : chamber) {
      if (shell) {
        ++live;
      } else {
        ++blank;
      }
    }
    
    System.out.println("Number of lives is " + live + ". Number of blanks is " + blank);
  }

  // Returns true if the chamber is empty, Eric, Cheryl, Nathan
  public boolean isEmpty() {
    if (chamber.size() == 0) return true;
    return false;
  }
  // User sided check using mag glass, Eric, Nathan
  public void useMagGlass() {
    boolean round = chamber.get(0);
    if (round) {
      System.out.println("Live round..."); 
    } else if (!round) {
      System.out.println("Blank round...");
    }
  }

  // Check the saw used status, Marcus, Nathan
  public boolean checkSaw() {
    return sawUsed;
  }

  // Changes the sawUsed boolean to the opposite, Eric, Marcus, Cheryl
  public void setSawToFalse() {
      sawUsed = false;
    
  }

  // Changes sawUsed to True
  public void setSawToTrue() {
    sawUsed = true;
  }

  // Handles shooting logic and turn logic, Eric, Marcus
  public void shoot(Player personGettingShot) {
    if (sawUsed && chamber.get(0)) {
      //damage = 2;
      System.out.println("Live Round...");
      System.out.println("");
      personGettingShot.removeHealth(2);
      System.out.println(personGettingShot.getName() + " has " +  personGettingShot.getHealth() + " health left");
      chamber.remove(0);
    } else if (chamber.get(0)){
      //damge = 1;
      System.out.println("Live Round...");
      System.out.println("");
      personGettingShot.removeHealth(1);
      System.out.println(personGettingShot.getName() + " has " +  personGettingShot.getHealth() + " health left");
      chamber.remove(0);
    } else if (!chamber.get(0)) {
      System.out.println("Blank round...");
      chamber.remove(0);
      sawUsed = false;
    } else {
      System.out.println("Bug occurred when determining blank/live rounds");
    }
  }

  // Removes first bullet in chamber - Eric
  public void removeFirstBullet () {
    chamber.remove(0);
  }

  //Checks the true/false of the first bullet - Nathan
  public boolean checkFirstBullet() {
    return chamber.get(0);
  }
}