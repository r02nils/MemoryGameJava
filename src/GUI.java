import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
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
    private int activePlayer;
    private boolean turnFinished;
    private int state;
    private int wait;
    private int selectedOption;
    private int x;
    private int y;
    private ArrayList imageIndexs;
    private int imgIndex;
    private int number;

    GUI(int x, int y) throws IOException {
        this.x = x;
        this.y = y;
        number = (y * x);
        if ((x * y) % 2 != 0) {
            number -= 1;
        }
        this.setTitle("Memory Game");
        init();
        pack();
        setResizable(false);
        setVisible(true);
    }

    public void init() throws IOException {
        player1 = new JLabel("Spieler 1");
        player2 = new JLabel("Spieler 2");
        pointsPlayer1 = new JLabel("0");
        pointsPlayer2 = new JLabel("0");
        currentPlayer = new JLabel("Aktueller Spieler: Spieler 1");


        model = new Model();
        cards = model.shuffle1(x * y);
        //cards = model.shuffle1(number);
        c1 = -1;
        c2 = -1;
        p1Points = 0;
        p2Points = 0;
        activePlayer = 1;
        state = 1;
        wait = 0;

        infoPanel = new JPanel(new GridLayout(1, 3));

        infoPanel.add(pointsPlayer1);
        infoPanel.add(currentPlayer);
        infoPanel.add(pointsPlayer2);
        infoPanel.setSize(100, 20);


        field = new JPanel(new GridLayout(x, y, 5, 5));
        for (int i = 0; i < (number); i++) {
            if (x == y) {
                cards.get(i).getPanel().setSize((150 / x) * 4, (150 / y) * 4);
            } else {
                cards.get(i).getPanel().setSize(150, 150);
            }
            field.add(cards.get(i).getPanel());
        }

        getContentPane().add(infoPanel, BorderLayout.NORTH);
        getContentPane().add(field, BorderLayout.CENTER);

        for (int i = 0; i < number; i++) {
            cards.get(i).getPanel().setIcon(new ImageIcon(new ImageIcon(getClass().getResource("img/Memory.png")).getImage().getScaledInstance(cards.get(0).getPanel().getWidth(), cards.get(0).getPanel().getHeight(), Image.SCALE_SMOOTH)));
        }

        for (int i = 0; i < cards.size(); i++) {
            int index = i;
            int points = 0;
            cards.get(i).getPanel().addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (cards.get(index).getTurned() == false) {
                        if (wait == 1) {
                            setTurned(c1, false);
                            setTurned(c2, false);
                            wait = 0;
                        }
                        setTurned(index, true);
                        showText(index);
                        if (state == 2) {
                            state = 1;
                            c2 = index;
                            if (cards.get(c1).getImgAnime().contains((cards.get(c2).getImgAnime()))) {
                                if (activePlayer == 1) {
                                    if(cards.get(index).getNumber() == 1){
                                        p1Points += 3;
                                    }
                                    else {
                                        p1Points++;
                                    }
                                    points(activePlayer, p1Points);
                                    activePlayer = 1;
                                    changePlayer(activePlayer);
                                } else {
                                    if(cards.get(index).getNumber() == 1){
                                        p2Points += 3;
                                    }
                                    else {
                                        p2Points++;
                                    }
                                    points(activePlayer, p2Points);
                                    activePlayer = 2;
                                    changePlayer(2);
                                }
                                checkFinished();
                            } else {
                                wait = 1;
                                if (activePlayer == 1) {
                                    activePlayer = 2;
                                    changePlayer(activePlayer);
                                } else {
                                    activePlayer = 1;
                                    changePlayer(activePlayer);
                                }
                            }
                        } else {
                            state = 2;
                            c1 = index;
                        }
                    }


                }

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
        if (val == true) {
            if (selectedOption == 1) {
                    cards.get(index).getPanel().setIcon(new ImageIcon(new ImageIcon(getClass().getResource(cards.get(index).getImgAnime())).getImage().getScaledInstance(cards.get(index).getPanel().getWidth(), cards.get(index).getPanel().getHeight(), Image.SCALE_SMOOTH)));
            } else {
                if (cards.get(index).getNumber() == 1) {
                    cards.get(index).getPanel().setIcon(new ImageIcon(new ImageIcon(getClass().getResource(cards.get(index).getImgAnime())).getImage().getScaledInstance(cards.get(index).getPanel().getWidth(), cards.get(index).getPanel().getHeight(), Image.SCALE_SMOOTH)));
                } else {
                    cards.get(index).getPanel().setBackground(cards.get(index).getColor());
                    cards.get(index).getPanel().setIcon(null);
                }
            }

            showText(index);
            cards.get(index).setTurned(true);
        }
        if (val == false) {
            cards.get(index).getPanel().setIcon(new ImageIcon(new ImageIcon(getClass().getResource("img/Memory.png")).getImage().getScaledInstance(cards.get(index).getPanel().getWidth(), cards.get(index).getPanel().getHeight(), Image.SCALE_SMOOTH)));
            unshowText(index);
            cards.get(index).setTurned(false);
        }
    }

    public void showText(int index) {
        //cards.get(index).setLabel(String.valueOf(cards.get(index).getNumber()));
    }

    public void unshowText(int index) {
        //cards.get(index).setLabel(" ");
    }

    public void changePlayer(int index) {
        currentPlayer.setText("Aktueller Spieler: " + index);
    }

    public void points(int index, int points) {
        if (index == 1) {
            pointsPlayer1.setText("Spieler 1: " + points);
        } else {
            pointsPlayer2.setText("Spieler 2: " + points);
        }
    }

    public void checkFinished() {
        int check = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getTurned() == true) {
                check++;
            }
        }
        System.out.println(check);
        System.out.println(cards.size());
        if (check == cards.size()) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            setVisible(false);
            ResultGUI resultGUI = new ResultGUI(p1Points, p2Points);
            resultGUI.setSize(700,500);
            resultGUI.setVisible(true);
        }
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNumber() {
        return number;
    }

}

