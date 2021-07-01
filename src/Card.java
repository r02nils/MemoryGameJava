import javax.swing.*;
import java.awt.*;

public class Card{
    private int number;
    private boolean isTurned;
    private int value;
    private Color color;
    private String imgAnime = "";
    private String imgFootball = "";
    private JButton panel;
    private JLabel label;

    public Card(int number){
        this.isTurned = false;
        this.number = number;
        this.color = new Color(0,0,0);
        setImages();
        panel = new JButton();
        label = new JLabel();
        panel.add(label);
    }

    public void setImages(){
        switch (number){
            case 1: color = Color.ORANGE;
                imgAnime = "img/i1.png"; imgFootball = "img/s1.png";
                break;
            case 2: color = Color.BLUE;
                imgAnime = "img/i2.png"; imgFootball = "img/s2.png";
                break;
            case 3: color = Color.GREEN;
                imgAnime = "img/i3.png"; imgFootball = "img/s3.png";
                break;
            case 4: color = Color.CYAN;
                imgAnime = "img/i4.png"; imgFootball = "img/s4.png";
                break;
            case 5: color = Color.magenta;
                imgAnime = "img/i5.png"; imgFootball = "img/s5.png";
                break;
            case 6: color = Color.YELLOW;
                imgAnime = "img/i6.png"; imgFootball = "img/s6.png";
                break;
            case 7: color = Color.pink;
                imgAnime = "img/i7.png"; imgFootball = "img/s7.png";
                break;
            case 8: color = Color.black;
                imgAnime = "img/i8.png"; imgFootball = "img/s8.png";
                break;
            case 9: color = Color.RED;
                imgAnime = "img/i9.png"; imgFootball = "img/s9.png";
                break;
            case 10: color = Color.WHITE;
                imgAnime = "img/i10.png"; imgFootball = "img/s10.png";
                break;
        }
    }

    public void setColor() {

    }

    public void setImg() {

    }

    public boolean isMatched(){
        return true;
    }

    public void setJokerCard(){
        this.value = 2;
    }

    public int getCardValue(){return this.value;}

    public JButton getPanel(){return this.panel;}

    public void setTurned(boolean val){this.isTurned = val;}
    public boolean getTurned() {return this.isTurned;}

    public int getNumber() {return this.number;}

    public void setLabel(String num) {this.label.setText(num);}

    public Color getColor() {
        return color;
    }

    public String getImgAnime() {
        return imgAnime;
    }

    public void setImgAnime(String imgAnime) {
        this.imgAnime = imgAnime;
    }

    public String getImgFootball() {
        return imgFootball;
    }

    public void setImgFootball(String imgFootball) {
        this.imgFootball = imgFootball;
    }
}
