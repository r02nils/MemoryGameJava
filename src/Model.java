import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.Collections;
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
    private ArrayList<Integer> imageIndexs;
    private ArrayList<Integer> sizeIndexs1;
    private ArrayList<Integer> sizeIndexs2;

    private int imgIndex = 0;
    private int imgIndexCheck;
    private int size;

    Vector<Integer> num = new Vector<>();
    Vector<Integer> img = new Vector<>();

    public Model(){
        for (int i = 2; i <= 50; i++) {
            num.add(i);
            img.add(i);
        }

        Collections.shuffle(num);
        Collections.shuffle(img);

        num.add(0,1);
    }

    public Vector<Card> shuffle1(int index){
        int check = 1;
        Vector<Card> cards = new Vector<>();
        cards.add(new Card(num.get(0), img.get(1)));
        cards.add(new Card(num.get(0), img.get(1)));
        for (int i = 2; i < index; i++) {
            if(check == 1){
                cards.add(new Card(num.get(i), img.get(i)));
                check = 2;
            }
            else{
                cards.add(new Card(num.get(i-1), img.get(i-1)));
                check = 1;
            }
        }

        Collections.shuffle(cards);
        return cards;
    }


    /*public Vector<Card> shuffle2(int index){
        int counter = index;

        int sizeCounter = 0;
        imageIndexs = new ArrayList<>();
        sizeIndexs1 = new ArrayList<>();
        sizeIndexs2 = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            imageIndexs.add(i);
        }
        Collections.shuffle(imageIndexs);

        for (int i = 0; i < index / 2; i++) {
            sizeIndexs1.add(imageIndexs.get(i));
            sizeIndexs1.add(imageIndexs.get(i));
        }
        for (int i = 0; i < index / 2; i++) {

        }

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
            cards.add(new Card(num.get(x -1), setImageIndexOne(sizeIndexs1.get(i))));
            num.remove(x-1);
            counter--;
        }
        int z = random.nextInt(index);
        int w = cards.get(z).getNumber();

        for (int i = 0; i < cards.size(); i++) {
               if(cards.get(i).getNumber() == w) {
               //cards.get(i).setGif();
               }
        }
        Collections.shuffle(cards);
        return cards;
    }*/

    public int getPunkteSpieler1() {
        return player1Points;
    }

    public int getPunkteSpieler2() {
        return player2Points;
    }

    public void addPunkteSpieler1(int points) {
        this.player1Points += points;
    }

    public void addPunkteSpieler2(int points) {
        this.player2Points += points;
    }

    public void checkCards() {

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

    public int setImageIndexOne(int y) {
        imgIndex++;
        return imageIndexs.get(y);
    }
}
