/* 
 * Item.java
 * Computer Science CPT
 * Buckshot roulette
 * Eric, Cheryl, Marcus
*/


public abstract class Item {
  private String name;

  // Constructor - Eric
  public Item (String name) {
    this.name = name;
  }
  
  // Item name getter - Eric
  public String getName() {
    return name;
  }

  // Returns the item name when called - Eric
  @Override
  public String toString() {
        return "Item: " + name;
    }

  // Subclass Gunitem creates objects Hacksaw, Magnifying Glass, Beer - Eric
  public static class GunItem extends Item {
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
}






