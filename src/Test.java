import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import static org.junit.Assert.*;

public class Test{

    @org.junit.Test
    public void t01() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,1);
        gui.setSelectedOption(1);
        gui.clickEvent(0);
        gui.clickEvent(1);
        int x = gui.getActivePlayer();

        assertEquals(x,1);
    }

    @org.junit.Test
    public void t02() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,1);
        gui.setSelectedOption(1);
        for (int i = 0; i < 16; i++) {
            gui.clickEvent(i);
        }
        ResultGUI resultGUI = new ResultGUI(gui.getP1Points(),gui.getP2Points());
        String x = resultGUI.getWinner().getText();

        assertEquals("Sieger: Spieler 1", x);
    }

    @org.junit.Test
    public void t04() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        clearTheFile();
        ResultGUI resultGUI = new ResultGUI(4,6);
        resultGUI.savePunkte(resultGUI.getPunkte());
        resultGUI = new ResultGUI(1,7);

        assertEquals(2, resultGUI.getPunkte()[1]);
    }

    @org.junit.Test
    public void t06() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,1);
        gui.setSelectedOption(2);
        Vector<Card> cards = gui.getModel().shuffle0(16);
        Color card1 = cards.get(0).getColor();
        Color card2 = cards.get(1).getColor();

        assertEquals(card1,card2);
    }

    @org.junit.Test
    public void t07() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,1);
        gui.setSelectedOption(2);
        String name1 = gui.getPlayer1().getText();
        String name2 = gui.getPlayer2().getText();

        assertEquals(name1,"Spieler 1");
        assertEquals(name2,"Spieler 2");
    }

    @org.junit.Test
    public void t08() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,1);
        gui.setSelectedOption(2);
        gui.getModel().shuffle0(16);

        gui.clickEvent(0);
        gui.clickEvent(1);

        gui.clickEvent(2);
        gui.clickEvent(3);

        gui.clickEvent(4);
        gui.clickEvent(5);

        int x = gui.getP1Points();

        assertEquals(5,x);
    }

    @org.junit.Test
    public void t11() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,1);
        gui.setSelectedOption(2);
        gui.getModel().shuffle0(16);

        gui.clickEvent(0);
        gui.clickEvent(4);

        gui.clickEvent(1);

        boolean check1 = gui.getCards().get(0).getTurned();
        boolean check2 = gui.getCards().get(4).getTurned();

        assertEquals(false,check1);
        assertEquals(false,check2);
    }

    @org.junit.Test
    public void t12() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,1);
        gui.setSelectedOption(2);
        gui.getModel().shuffle0(16);

        gui.clickEvent(0);
        gui.clickEvent(1);

        int x = gui.getP1Points();

        assertEquals(3,x);
    }

    @org.junit.Test
    public void t13() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,1);
        gui.setSelectedOption(2);
        gui.getModel().shuffle0(16);

        gui.clickEvent(0);
        gui.clickEvent(4);

        int x = gui.getActivePlayer();

        assertEquals(2,x);
    }

    @org.junit.Test
    public void t14() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,1);
        gui.setSelectedOption(2);
        gui.getModel().shuffle0(16);

        int x = gui.getActivePlayer();

        assertEquals(1,x);
    }

    public static void clearTheFile() throws IOException {
        FileWriter fwOb = new FileWriter("savePunkte.txt", false);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }
}
