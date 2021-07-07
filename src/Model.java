import java.util.Collections;
import java.util.Vector;

/**
 * @author Pascal Thuma, Francesco Ryu, Nils Rothenbühler
 * @since 2021-07-03
 * @version 1.0
 */

public class Model{

    Vector<Integer> num = new Vector<>();
    Vector<Integer> img = new Vector<>();

    /**
     * Model constructor
     */
    public Model(){
        for (int i = 2; i <= 50; i++) {
            num.add(i);
            img.add(i);
        }
    }

    /**
     * Shuffles the card (normal mode)
     * @param index index (number)
     * @return cards
     */
    public Vector<Card> shuffle1(int index){
        Collections.shuffle(num);
        Collections.shuffle(img);

        img.add(0,1);

        num.add(0,1);
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

    /**
     * Shuffles the card (test mode)
     * @param index index of card
     * @return cards
     */
    public Vector<Card> shuffle0(int index){
        img.add(0,1);
        num.add(0,1);
        Vector<Card> cards = new Vector<>();
        cards.add(new Card(num.get(0), img.get(1)));
        cards.add(new Card(num.get(0), img.get(1)));
        for (int i = 1; i < (index/2); i++) {
            cards.add(new Card(num.get(i), img.get(i)));
            cards.add(new Card(num.get(i), img.get(i)));
        }

        return cards;
    }

    public Vector<Card> shuffle2(int index){
        Vector<Card> cards = new Vector<>();
        for (int i = 1; i < (index/2)+1; i++) {
            cards.add(new Card(num.get(i), img.get(i)));
            cards.add(new Card(num.get(i), img.get(i)));
        }

        return cards;
    }
}
