import org.junit.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.junit.Assert.*;

public class Test{

    @org.junit.Test
    public void test() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        GUI gui = new GUI(5,5);
        int x = gui.getNumber();
        assertEquals(x,24);
    }
}
