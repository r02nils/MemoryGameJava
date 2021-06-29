import javax.swing.*;
import java.awt.*;

public class Card{
    private int number;
    private boolean isTurned = false;
    private int value;
    private Color color;
    private String img;
    private JPanel panel;
    private JLabel label;

    public Card(int number){
        this.number = number;
        this.color = new Color(0,0,0);
        switch (number){
            case 1: color = Color.ORANGE; break;
            case 2: color = Color.BLUE; break;
            case 3: color = Color.GREEN; break;
            case 4: color = Color.CYAN; break;
            case 5: color = Color.magenta; break;
            case 6: color = Color.YELLOW; break;
            case 7: color = Color.pink; break;
            case 8: color = Color.black; break;
            case 9: color = Color.RED; break;
            case 10: color = Color.WHITE; break;
        }
        panel = new JPanel();
        label = new JLabel();
        panel.add(label);
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

    public JPanel getPanel(){return this.panel;}

    public void setTurned(boolean val){this.isTurned = val;}
    public boolean getTurned() {return this.isTurned;}

    public int getNumber() {return this.number;}

    public void setLabel(String num) {this.label.setText(num);}

    public Color getColor() {
        return color;
    }
}
