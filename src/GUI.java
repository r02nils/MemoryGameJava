import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;


public class GUI extends JFrame {

    private JPanel[] cardpanels = new JPanel[20];
    private Model model;
    private Vector<Card> cards;
    private JPanel field;
    private JPanel infoPanel;
    private JPanel player1Panel;
    private JPanel player2Panel;
    private JLabel player1;
    private JLabel player2;
    private JLabel pointsPlayer1;
    private JLabel pointsPlayer2;
    private JLabel currentPlayer;
    private int c1, c2;
    private int p1Points, p2Points;
    private int c1Index, c2Index;
    private boolean activePlayer;
    private boolean turnFinished;
    private int state;
    private int wait;

    GUI() throws IOException {
        this.setTitle("Memory Game");
        init();
        pack();
        setVisible(true);
    }

    public void init() throws IOException {
        player1 = new JLabel("Spieler 1");
        player2 = new JLabel("Spieler 2");
        pointsPlayer1 = new JLabel("0");
        pointsPlayer2 = new JLabel("0");
        currentPlayer = new JLabel("Aktueller Spieler: Spieler 1");

        model = new Model();
        cards = model.shuffle();
        c1 = -1;
        c2 = -1;
        p1Points = 0;
        p2Points = 0;
        activePlayer = true;
        state = 1;
        wait = 0;

        field = new JPanel(new GridLayout(4, 5, 5, 5));
        for (int i = 0; i < 20; i++) {
            cards.get(i).getPanel().setSize(20, 20);
            cards.get(i).getPanel().setBackground(Color.gray);
            field.add(cards.get(i).getPanel());
        }

        getContentPane().add(field);

        for (int i = 0; i < cards.size(); i++) {
            int index = i;
            int points = 0;
            cards.get(i).getPanel().addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(wait == 1){
                        setTurned(c1,false);
                        setTurned(c2,false);
                        wait = 0;
                    }
                    setTurned(index, true);
                    showText(index);
                    if(state == 2){
                        state = 1;
                        c2 = index;
                        if(cards.get(c1).getNumber() == cards.get(c2).getNumber()){
                            checkFinished();
                        }
                        else{
                            wait = 1;
                        }
                    }
                    else{
                        state = 2;
                        c1 = index;
                    }


                }
                    /*
                    setTurned(index, true);
                    showText(index);
                    turnFinished = false;
                    if (c1 == -1) {
                        c1 = cards.get(index).getNumber();
                        c1Index = index;
                    }
                    if (cards.get(c1Index).getTurned() == true && turnFinished == true) {
                        c2 = cards.get(index).getNumber();
                        c2Index = index;
                    }
                    if (c2 != -1 || c1 != -1) {
                        if (c2 == c1) {
                            if (activePlayer == true) {
                                p1Points += (cards.get(c1Index).getNumber() + cards.get(c2Index).getNumber());
                            }
                            if (activePlayer == false) {
                                p2Points += (cards.get(c1Index).getNumber() + cards.get(c2Index).getNumber());
                            }
                            turnFinished = false;
                        }

                        if(c2 != c1) {
                            cards.get(c1Index).setTurned(false);
                            unshowText(c1Index);
                            cards.get(c2Index).setTurned(false);
                            unshowText(c2Index);
                            turnFinished = false;
                        }
                        turnFinished = true;

                    }
                    if(cards.get(c1Index).getTurned() == false || cards.get(c2Index).getTurned() == false) {
                        cards.get(c1Index).getPanel().setBackground(Color.gray);
                        cards.get(c2Index).getPanel().setBackground(Color.gray);
                        c2 = -1;
                        c1 = -1;
                        c1Index = -1;
                        c2Index = -1;
                    }
                }*/

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }
    }
    public void setTurned(int index, boolean val){
        if(val == true){
            cards.get(index).getPanel().setBackground(cards.get(index).getColor());
            showText(index);
            cards.get(index).setTurned(true);
        }
        if(val == false) {
            cards.get(index).getPanel().setBackground(Color.gray);
            unshowText(index);
            cards.get(index).setTurned(false);
        }
    }

    public void showText(int index) {
        cards.get(index).setLabel(String.valueOf(cards.get(index).getNumber()));
    }
    public void unshowText(int index) {
        cards.get(index).setLabel(" ");
    }
    public void checkFinished(){
        int check = 0;
        for (int i = 0; i < cards.size(); i++) {
            if(cards.get(i).getTurned() == true) {
                check ++;
            }
        }
        System.out.println(check);
        System.out.println(cards.size());
        if(check == cards.size()){
            setVisible(false);
            MenuGUI menuGUI = new MenuGUI();
            menuGUI.setVisible(true);
        }
    }

}

