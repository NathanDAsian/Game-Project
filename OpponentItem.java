/* 
 * Item.java - subclass OpponenntItem
 * Computer Science CPT
 * Buckshot roulette
 * Eric, Cheryl, Marcus
*/


// Subclass OpponentItem creates object handCuffs - Eric
  public class OpponentItem extends Item {
    // Constructor takes name
    public OpponentItem(String name) {
      super(name);
    }

    //Creates an item called handcuffs - Eric
    public static OpponentItem createHandcuffs() {
      return new OpponentItem("HandCuffs");
    }
  }