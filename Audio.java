/* 
 * Game.java
 * Computer Science CPT
 * Buckshot roulette
 * Nathan
 * JUICEMIND DOES NOT ALLOW SOUNDS 
 * If you would like sound please run this on VS code using the other file submit
*/

import java.io.File;
import javax.sound.sampled.*;

public class Audio {

    // Plays a sound when run
    public static void playSound(String soundFileName) {
        try {
            File audioFile = new File(soundFileName); // Replace with absolute path if needed
            if (!audioFile.exists()) {
                throw new Exception("File not found: " + audioFile.getAbsolutePath());
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            System.out.println("Playing: " + soundFileName);
        } catch (Exception ex) {
            System.err.println("Error playing sound: " + ex.getMessage());
        }
    }
}
