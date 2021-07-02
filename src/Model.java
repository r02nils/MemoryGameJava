import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class Model implements ListModel<Card> {
    private Random random = new Random();
    private Vector<Card> kartenListe = new Vector<>();

    Vector<Integer> num = new Vector<>();
    Vector<Integer> img = new Vector<>();

    public Model(){
        for (int i = 2; i <= 50; i++) {
            num.add(i);
            img.add(i);
        }

        Collections.shuffle(num);
        Collections.shuffle(img);

        img.add(0,1);

        num.add(0,1);
    }

    public Vector<Card> shuffle1(int index){
        Vector<Card> cards = new Vector<>();
        cards.add(new Card(num.get(0), img.get(1)));
        cards.add(new Card(num.get(0), img.get(1)));
        for (int i = 1; i < (index/2); i++) {
            cards.add(new Card(num.get(i), img.get(i)));
            cards.add(new Card(num.get(i), img.get(i)));
        }

        Collections.shuffle(cards);
        return cards;
    }

    public Vector<Card> shuffle0(int index){
        Vector<Card> cards = new Vector<>();
        cards.add(new Card(num.get(0), img.get(1)));
        cards.add(new Card(num.get(0), img.get(1)));
        for (int i = 1; i < (index/2); i++) {
            cards.add(new Card(num.get(i), img.get(i)));
            cards.add(new Card(num.get(i), img.get(i)));
        }

        return cards;
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
