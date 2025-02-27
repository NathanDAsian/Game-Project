/* 
 * Item.java - subclass GunItem
 * Computer Science CPT
 * Buckshot roulette
 * Eric, Cheryl, Marcus
*/

// Subclass Gunitem creates objects Hacksaw, Magnifying Glass, Beer - Eric
  public class GunItem extends Item {
    //Constructor, takes name
    public GunItem(String name) {
      super(name);
    }
  
    // Creates an item called Saw - Eric
    public static GunItem createSaw() {
      return new GunItem("Saw");
    }

  
    // Creates an item called mag. glass - Eric
    public static GunItem createMagnifyingGlass() {
      return new GunItem("MagGlass");
    }
  }