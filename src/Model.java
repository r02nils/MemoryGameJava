import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.Vector;

public class Model implements ListModel<Karte> {

    private Vector<Karte> kartenListe = new Vector<>();
    private int s1punkte;
    private int s2punkte;

    public void shuffle(){

    }

    public int getPunkteSpieler1(){
        return s1punkte;
    }

    public int getPunkteSpieler2(){
        return s2punkte;
    }

    public void addPunkteSpieler1(int punkte){
        this.s1punkte += punkte;
    }

    public void addPunkteSpieler2(int punkte){
        this.s2punkte += punkte;
    }

    public void checkCards(){

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Karte getElementAt(int index) {
        return null;
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
