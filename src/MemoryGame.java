import javax.sound.sampled.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MemoryGame {

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        clearTheFile();
        String soundName = "africa-toto.wav";
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("africa-toto.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
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
