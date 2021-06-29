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

    public Vector<Card> shuffle(){
        int counter = 20;
        Vector<Card> cards = new Vector<>();
        int[] numbers = new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10};
        Vector<Integer> num = new Vector<>();
        for (int i = 0; i < 20; i++) {
            num.add(numbers[i]);
        }

        for (int i = 0; i < 20; i++) {
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
