/* 
 * Game.java
 * Computer Science CPT
 * Buckshot roulette
 * Eric, Marcus
*/

public class Player {
  private String name;
  private int health = 5;
  private boolean handcuffed;
  private boolean turn = true;
  
  // Create a constructor with base health 5 - Eric
  public Player (String name) {
    this.name = name;
    this.health = health;
    this.handcuffed = false;
  }

  // get health of object, Eric
  public int getHealth() {
    return health;
  }

  // object.health = setHealth(int damage);
  // Sets new health of object, Eric
  public void removeHealth(int damage) {
    this.health = this.health - damage;
  }

  // Add health to the person, Eric
  public int addHealth(int hp) {
    this.health = this.health + hp;
      return this.health;
  }

  // Set handcuffed true/false Marcus, Cheryl, Nathan
  public void setHandcuffed() {
    if (handcuffed) {
      handcuffed = false;
      System.out.println("Handcuffs on " + name + " broke");
    } else if (!handcuffed) {
      handcuffed = true;
      System.out.println(name + "... cuffed");
    }
  }

  // Check if current player is cuffed, Marcus
  public boolean checkHandcuffed() {
    return handcuffed;
  }

  // Gets the name of the player, Eric
  public String getName() {
    return name;
  }

  // Checks the turn order, Eric
  public boolean checkTurn() {
    return turn;
  }

  // Sets the turn order to false, Eric, Marcus
  public void setTurnToFalse() {
    turn = false;
  }

  // Sets the turn order to true, Eric, Marcus
  public void setTurnToTrue() {
    turn = true;
  }
}
