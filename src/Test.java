
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import static org.junit.Assert.*;

/**
 * @author Pascal Thuma, Francesco Ryu, Nils Rothenbühler
 * @since 2021-07-03
 * @version 1.0
 */

public class Test{

    /**
     * checks if correct active player is set after a player discover a pair of cards
     * @throws UnsupportedAudioFileException exception
     * @throws LineUnavailableException exception
     * @throws IOException exception
     */
    @org.junit.Test
    public void t01() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,150,1);
        gui.setSelectedOption(1);
        gui.clickEvent(0);
        gui.clickEvent(1);
        int x = gui.getActivePlayer();

        assertEquals(x,1);
    }

    /**
     * checks whether the player with the most points wins
     * @throws UnsupportedAudioFileException exception
     * @throws LineUnavailableException exception
     * @throws IOException exception
     */
    @org.junit.Test
    public void t02() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,150,1);
        gui.setSelectedOption(1);
        for (int i = 0; i < 16; i++) {
            gui.clickEvent(i);
        }
        ResultGUI resultGUI = new ResultGUI(gui.getP1Points(),gui.getP2Points());
        String x = resultGUI.getWinner().getText();

        assertEquals("Sieger: Spieler 1", x);
    }

    /**
     * checks if totalPoints are carried over when the game is restarted
     * @throws UnsupportedAudioFileException exception
     * @throws LineUnavailableException exception
     * @throws IOException exception
     */
    @org.junit.Test
    public void t04() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        clearTheFile();
        ResultGUI resultGUI = new ResultGUI(4,6);
        resultGUI.savePoints(resultGUI.getPoints());
        resultGUI = new ResultGUI(1,7);

        assertEquals(2, resultGUI.getPoints()[1]);
    }

    /**
     * checks if two matched cards have the same color
     * @throws UnsupportedAudioFileException exception
     * @throws LineUnavailableException exception
     * @throws IOException exception
     */
    @org.junit.Test
    public void t06() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,150,1);
        gui.setSelectedOption(2);
        Vector<Card> cards = gui.getModel().shuffle0(16);
        Color card1 = cards.get(0).getColor();
        Color card2 = cards.get(1).getColor();

        assertEquals(card1,card2);
    }

    /**
     * checks if name is correct
     * @throws UnsupportedAudioFileException exception
     * @throws LineUnavailableException exception
     * @throws IOException exception
     */
    @org.junit.Test
    public void t07() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,150,1);
        gui.setSelectedOption(2);
        String name1 = gui.getPlayer1().getText();
        String name2 = gui.getPlayer2().getText();

        assertEquals(name1,"Spieler 1");
        assertEquals(name2,"Spieler 2");
    }

    /**
     * checks if the points are counted correctly
     * @throws UnsupportedAudioFileException exception
     * @throws LineUnavailableException exception
     * @throws IOException exception
     */
    @org.junit.Test
    public void t08() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,150,1);
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

    /**
     * checks if the cards are getting unturned after they don't match
     * @throws UnsupportedAudioFileException exception
     * @throws LineUnavailableException exception
     * @throws IOException exception
     */
    @org.junit.Test
    public void t11() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,150,1);
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

    /**
     * checks if Joker card gives 3 points
     * @throws UnsupportedAudioFileException exception
     * @throws LineUnavailableException exception
     * @throws IOException exception
     */
    @org.junit.Test
    public void t12() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,150,1);
        gui.setSelectedOption(2);
        gui.getModel().shuffle0(16);

        gui.clickEvent(0);
        gui.clickEvent(1);

        int x = gui.getP1Points();

        assertEquals(3,x);
    }

    /**
     * checks if active Player get Swapped if a player dont find two matched cards
     * @throws UnsupportedAudioFileException exception
     * @throws LineUnavailableException exception
     * @throws IOException exception
     */
    @org.junit.Test
    public void t13() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,150,1);
        gui.setSelectedOption(2);
        gui.getModel().shuffle0(16);

        gui.clickEvent(0);
        gui.clickEvent(4);

        int x = gui.getActivePlayer();

        assertEquals(2,x);
    }

    /**
     * checks if active Player is set to 1 if game starts
     * @throws UnsupportedAudioFileException exception
     * @throws LineUnavailableException exception
     * @throws IOException exception
     */
    @org.junit.Test
    public void t14() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(4,4,150,1);
        gui.setSelectedOption(2);
        gui.getModel().shuffle0(16);

        int x = gui.getActivePlayer();

        assertEquals(1,x);
    }

    @org.junit.Test
    public void a1() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(3,2,150,1);
        gui.setSize(450,320);
        gui.setSelectedOption(1);

        gui.clickEvent(0); // 1.Karte
        gui.clickEvent(3); // 4.Karte
        gui.clickEvent(1); // Zum zudecken der Karten

        int x = gui.getActivePlayer();
        boolean card1 = gui.getCards().get(0).getTurned();
        boolean card2 = gui.getCards().get(3).getTurned();

        assertEquals(2,x);
        assertEquals(false,card1);
        assertEquals(false,card2);
    }

    @org.junit.Test
    public void a2() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(3,2,150,1);
        gui.setSize(450,320);
        gui.setSelectedOption(1);

        gui.clickEvent(0); // 1.Karte
        gui.clickEvent(1); // 2.Karte
        gui.clickEvent(2); // Zum zudecken der Karten

        int x = gui.getActivePlayer();
        boolean card1 = gui.getCards().get(0).getTurned();
        boolean card2 = gui.getCards().get(1).getTurned();

        assertEquals(1,x);
        assertEquals(true,card1);
        assertEquals(true,card2);
    }

    @org.junit.Test
    public void a3() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GUI gui = new GUI(3,2,150,1);
        gui.setSize(450,320);
        gui.setSelectedOption(1);
        gui.getModel().shuffle2(6);

        gui.clickEvent(0); // 1.Karte
        gui.clickEvent(3); // 4.Karte

        gui.clickEvent(0); // 1.Karte
        gui.clickEvent(1); // 2.Karte

        gui.clickEvent(2); // 3.Karte
        gui.clickEvent(3); // 4.Karte

        int x = gui.getP1Points();

        assertEquals(2, x);
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
