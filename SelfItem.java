/* 
 * Item.java - subclass SelfItem
 * Computer Science CPT
 * Buckshot roulette
 * Eric, Cheryl, Marcus
*/


// Subclass SelfItem creates object Cigarettes - Eric
  public class SelfItem extends Item {
    // Constructor, takes name
    public SelfItem(String name) {
      super(name);
    }

    //Creates a self item called cigarettes - Eric
    public static SelfItem createCigarettes() {
      return new SelfItem("Cigarettes");
    }
  }