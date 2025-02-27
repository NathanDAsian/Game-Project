/* 
 * Files.java
 * Computer Science CPT
 * Buckshot Roulette
 * Marcus
*/


import java.util.*;
import java.io.*;

// Class that holds all methods chagning/writing files
/* Method Rundown
* getName() --> gets String to be inputted to writeName() 
* writeName(Str) --> adds parameter into saved data file 
* clearFile() --> deletes saved data file's contents
* showFile() --> reads and outputs file's contents to console
*/
public class Files {
  
  private File saveFile = new File("source.txt");
  private String playerName;
  private Scanner input = new Scanner(System.in);

  // The prompts the user for a name which will be sent in another method 
  public void getName() {
    System.out.println("Congratulations! You have Beaten the AI");
    boolean validAnswer = false;
    while (!validAnswer) { // Gimmick to ask the user if they will confirm the name
      System.out.print("Enter your name for the leaderboard: ");
      playerName = input.nextLine();
      System.out.print("Confirm your name (type y/n): ");
      String answer = input.nextLine();
      if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
        validAnswer = true;
      }
    }
    writeName(playerName);
  }

  // Writes the name in the file score.txt
  private void writeName(String name) {
    try {
      PrintWriter writeFile = new PrintWriter(new BufferedWriter(new FileWriter(saveFile, true)));
      writeFile.println(name);
      writeFile.close();
    } catch(Exception e) {
      System.out.println("Problem when adding name");
    
    }
  }

  // Just In Case, we clear the source.txt file if leaderboard gets too cluttered
  public void clearFile() {
    try {
      new FileWriter(saveFile, false).close();
    } catch (Exception e) {
      System.out.println("Problem when deleting file");
    }
  }

  // Reads the file "showing" the leaderboard to the user in alphabetical order
  public void showFile() {
    try {
        BufferedReader readFile = new BufferedReader(new FileReader(saveFile));
        ArrayList<String> names = new ArrayList<String>();
        String text;
        while ((text = readFile.readLine()) != null) {  // Collect all names into the list
            names.add(text);
        }
        readFile.close();
      
        bubbleSort(names);   // Sort the list alphabetically
        System.out.println("All The Players Who Beat The AI (Alphabetical Order):");    // Display the sorted names
        for (String name : names) {
            System.out.println(name);
        }
    } catch (Exception e) {
        System.out.println("Problem when reading file");
    }
  }
  
  // Bubble Sort implementation for sorting names alphabetically
  private void bubbleSort(ArrayList<String> names) {
    int n = names.size();
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (names.get(j).compareToIgnoreCase(names.get(j + 1)) > 0) {
          // Swap names[j] and names[j+1]
          String temp = names.get(j);
          names.set(j, names.get(j + 1));
          names.set(j + 1, temp);
        }
      }
    }
  }
}