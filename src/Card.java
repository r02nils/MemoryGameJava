import javax.swing.*;
import java.awt.*;

/**
 * @author Pascal Thuma, Francesco Ryu, Nils Rothenbühler
 * @since 2021-07-03
 * @version 1.0
 */
public class Card {
    private int number;
    private boolean isTurned;
    private Color color;
    private String imgAnime = "";
    private JButton panel;
    private int imgIndex;

    /**
     * Card constructor
     * @param number number of Card
     * @param imgIndex image Index
     */
    public Card(int number, int imgIndex) {
        this.imgIndex = imgIndex;
        this.isTurned = false;
        this.number = number;
        this.color = new Color(0, 0, 0);
        setImages();
        setColors();
        panel = new JButton();
    }

    /**
     * Images are assigned to the cards
     * Joker card is present in every game
     */
    public void setImages() {
        switch (number) {
            case 1: imgAnime = "img/i"+1+".png";break; //Joker card is present in every game
            case 2: imgAnime = "img/i"+imgIndex+".png";break;
            case 3: imgAnime = "img/i"+imgIndex+".png";break;
            case 4: imgAnime = "img/i"+imgIndex+".png";break;
            case 5: imgAnime = "img/i"+imgIndex+".png";break;
            case 6: imgAnime = "img/i"+imgIndex+".png";break;
            case 7: imgAnime = "img/i"+imgIndex+".png";break;
            case 8: imgAnime = "img/i"+imgIndex+".png";break;
            case 9: imgAnime = "img/i"+imgIndex+".png";break;
            case 10: imgAnime = "img/i"+imgIndex+".png";break;
            case 11: imgAnime = "img/i"+imgIndex+".png";break;
            case 12: imgAnime = "img/i"+imgIndex+".png";break;
            case 13: imgAnime = "img/i"+imgIndex+".png";break;
            case 14: imgAnime = "img/i"+imgIndex+".png";break;
            case 15: imgAnime = "img/i"+imgIndex+".png";break;
            case 16: imgAnime = "img/i"+imgIndex+".png";break;
            case 17: imgAnime = "img/i"+imgIndex+".png";break;
            case 18: imgAnime = "img/i"+imgIndex+".png";break;
            case 19: imgAnime = "img/i"+imgIndex+".png";break;
            case 20: imgAnime = "img/i"+imgIndex+".png";break;
            case 21: imgAnime = "img/i"+imgIndex+".png";break;
            case 22: imgAnime = "img/i"+imgIndex+".png";break;
            case 23: imgAnime = "img/i"+imgIndex+".png";break;
            case 24: imgAnime = "img/i"+imgIndex+".png";break;
            case 25: imgAnime = "img/i"+imgIndex+".png";break;
            case 26: imgAnime = "img/i"+imgIndex+".png";break;
            case 27: imgAnime = "img/i"+imgIndex+".png";break;
            case 28: imgAnime = "img/i"+imgIndex+".png";break;
            case 29: imgAnime = "img/i"+imgIndex+".png";break;
            case 30: imgAnime = "img/i"+imgIndex+".png";break;
            case 31: imgAnime = "img/i"+imgIndex+".png";break;
            case 32: imgAnime = "img/i"+imgIndex+".png";break;
            case 33: imgAnime = "img/i"+imgIndex+".png";break;
            case 34: imgAnime = "img/i"+imgIndex+".png";break;
            case 35: imgAnime = "img/i"+imgIndex+".png";break;
            case 36: imgAnime = "img/i"+imgIndex+".png";break;
            case 37: imgAnime = "img/i"+imgIndex+".png";break;
            case 38: imgAnime = "img/i"+imgIndex+".png";break;
            case 39: imgAnime = "img/i"+imgIndex+".png";break;
            case 40: imgAnime = "img/i"+imgIndex+".png";break;
            case 41: imgAnime = "img/i"+imgIndex+".png";break;
            case 42: imgAnime = "img/i"+imgIndex+".png";break;
            case 43: imgAnime = "img/i"+imgIndex+".png";break;
            case 44: imgAnime = "img/i"+imgIndex+".png";break;
            case 45: imgAnime = "img/i"+imgIndex+".png";break;
            case 46: imgAnime = "img/i"+imgIndex+".png";break;
            case 47: imgAnime = "img/i"+imgIndex+".png";break;
            case 48: imgAnime = "img/i"+imgIndex+".png";break;
            case 49: imgAnime = "img/i"+imgIndex+".png";break;
            case 50: imgAnime = "img/i"+imgIndex+".png";break;

        }
    }

    /**
     * Colors are assigned to the cards
     */
    public void setColors(){
        switch (number){
            case 1: imgAnime = "img/i"+1+".png";break; //Joker card is present in every game
            case 2: color = new Color(255,204,0); break;
            case 3: color = new Color(100,255,0); break;
            case 4: color = new Color(0,255,149); break;
            case 5: color = new Color(0,217,255); break;
            case 6: color = new Color(0,60,255); break;
            case 7: color = new Color(144,0,255); break;
            case 8: color = new Color(255,0,195); break;
            case 9: color = new Color(255,94,0); break;
            case 10: color = new Color(191,255,0); break;
            case 11: color = new Color(107,0,0); break;
            case 12: color = new Color(107,54,0); break;
            case 13: color = new Color(107,98,0); break;
            case 14: color = new Color(57,107,0); break;
            case 15: color = new Color(0,107,30); break;
            case 16: color = new Color(0,107,100); break;
            case 17: color = new Color(0,66,107); break;
            case 18: color = new Color(0,0,107); break;
            case 19: color = new Color(55,0,107); break;
            case 20: color = new Color(107,0,107); break;
            case 21: color = new Color(255,107,107); break;
            case 22: color = new Color(255,164,107); break;
            case 23: color = new Color(255,203,107); break;
            case 24: color = new Color(255,255,107); break;
            case 25: color = new Color(198,255,107); break;
            case 26: color = new Color(144,255,107); break;
            case 27: color = new Color(107,255,186); break;
            case 28: color = new Color(107,255,253); break;
            case 29: color = new Color(107,194,255); break;
            case 30: color = new Color(132,107,255); break;
            case 31: color = new Color(174,107,255); break;
            case 32: color = new Color(255,107,255); break;
            case 33: color = new Color(51,51,0); break;
            case 34: color = new Color(153,153,102); break;
            case 35: color = new Color(153,102,51); break;
            case 36: color = new Color(255,204,255); break;
            case 37: color = new Color(204,204,255); break;
            case 38: color = new Color(204,255,255); break;
            case 39: color = new Color(204,255,204); break;
            case 40: color = new Color(255,255,204); break;
            case 41: color = new Color(102,0,204); break;
            case 42: color = new Color(107,153,153); break;
            case 43: color = new Color(102,102,153); break;
            case 44: color = new Color(153,51,102); break;
            case 45: color = new Color(105,105,105); break;
            case 46: color = new Color(23,23,23); break;
            case 47: color = new Color(235,235,235); break;
            case 48: color = new Color(153,115,115); break;
            case 49: color = new Color(54,84,45); break;
            case 50: color = new Color(255,255,0); break;

        }
    }

    /**
     * returns the card
     * @return panel (card)
     */
    public JButton getPanel() {
        return this.panel;
    }

    /**
     * set isTurned value to true/false
     * @param val value
     */
    public void setTurned(boolean val) {
        this.isTurned = val;
    }

    /**
     * get boolean if card is turned or not
     * @return isTurned
     */
    public boolean getTurned() {
        return this.isTurned;
    }

    /**
     * gets the unique number (every card pair has the same number)
     * @return number
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * returns color of the card
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * returns image of the card
     * @return imgAnime
     */
    public String getImgAnime() {
        return imgAnime;
    }
}
