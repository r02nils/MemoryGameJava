import javax.sound.sampled.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MemoryGame {

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        clearTheFile();


        MenuGUI menuGUI = new MenuGUI();
        menuGUI.setSize(520,250);

    }

    public static void clearTheFile() throws IOException {
        FileWriter fwOb = new FileWriter("savePunkte.txt", false);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }
}
