import javax.sound.sampled.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Pascal Thuma, Francesco Ryu, Nils Rothenbühler
 * @since 2021-07-03
 * @version 1.0
 */

public class MemoryGame {

    /**
     * main Method
     * @param args args
     * @throws IOException exception
     * @throws UnsupportedAudioFileException exception
     * @throws LineUnavailableException exception
     */
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        clearTheFile();

        MenuGUI menuGUI = new MenuGUI();
        menuGUI.setSize(520,250);

    }

    /**
     * clears the file "savePunkte.txt"
     * @throws IOException exception
     */
    public static void clearTheFile() throws IOException {
        FileWriter fwOb = new FileWriter("savePunkte.txt", false);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }
}
