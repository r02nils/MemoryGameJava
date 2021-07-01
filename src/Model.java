import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Model implements ListModel<Card> {
    private Random random = new Random();
    private Vector<Card> kartenListe = new Vector<>();
    private String player1;
    private String player2;
    private int player1Points;
    private int player2Points;
    private int imgSelection;

    public Vector<Card> shuffle(int index){
        int counter = index;
        Vector<Card> cards = new Vector<>();
        Vector<Integer> num = new Vector<>();
        int y = 0;
            for (int i = 2; i <= index+1; i++) {
                if(i % 2 == 0){
                    y=i/2;
                }
                num.add(y);
            }

        for (int i = 0; i < index; i++) {
            int x = random.nextInt(counter)+1;
            cards.add(new Card(num.get(x-1)));
            num.remove(x-1);
            counter--;
        }
        return cards;
    }

    public int getPunkteSpieler1(){
        return player1Points;
    }

    public int getPunkteSpieler2(){
        return player2Points;
    }

    public void addPunkteSpieler1(int points){
        this.player1Points += points;
    }

    public void addPunkteSpieler2(int points){
        this.player2Points += points;
    }

    public void checkCards(){

    }

    public int getImgSelection() {
        return imgSelection;
    }

    public void setImgSelection(int imgSelection) {
        this.imgSelection = imgSelection;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Card getElementAt(int index) {
        return null;
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }

}
