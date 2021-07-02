import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class GUI extends JFrame {

    private JPanel[] cardpanels = new JPanel[20];
    private Model model;
    private Vector<Card> cards;
    private JPanel field;
    private JPanel infoPanel;
    private JLabel player1;
    private JLabel player2;
    private JLabel pointsPlayer1;
    private JLabel pointsPlayer2;
    private JLabel currentPlayer;
    private int c1, c2;
    private int p1Points, p2Points;
    private int activePlayer;
    private int state;
    private int wait;
    private int selectedOption;
    private int x;
    private int y;
    private int number;
    private ResultGUI resultGUI;
    private int mode;

    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/music/africa-toto.wav").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();

    GUI(int y, int x, int mode) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        this.x = x;
        this.y = y;
        this.mode = mode;
        number = (y * x);
        if ((x * y) % 2 != 0) {
            number -= 1;
        }
        this.setTitle("Memory Game");
        init();
        pack();
        setResizable(false);
        setVisible(true);
        for (int i = 0; i < number; i++) {
            cards.get(i).setTurned(true);
            cards.get(i).setTurned(false);
        }
    }

    public void init() throws IOException, LineUnavailableException {
        clip.open(audioInputStream);
        clip.start();
        player1 = new JLabel("Spieler 1");
        player2 = new JLabel("Spieler 2");
        pointsPlayer1 = new JLabel("0");
        pointsPlayer2 = new JLabel("0");
        currentPlayer = new JLabel("Aktueller Spieler: Spieler 1");


        pointsPlayer1.setFont(new Font("Georgia", Font.PLAIN,20));
        pointsPlayer2.setFont(new Font("Georgia", Font.PLAIN,20));
        currentPlayer.setFont(new Font("Georgia", Font.PLAIN,20));

        model = new Model();

        if(mode == 1){
            cards = model.shuffle0(number);
        }
        else{
            cards = model.shuffle1(number);
        }
        c1 = -1;
        c2 = -1;
        activePlayer = 1;
        state = 1;
        wait = 0;

        infoPanel = new JPanel(new GridLayout(1, 3));

        infoPanel.add(pointsPlayer1);
        infoPanel.add(currentPlayer);
        infoPanel.add(pointsPlayer2);
        infoPanel.setSize(100, 50);
        infoPanel.setBackground(Color.red);


        field = new JPanel(new GridLayout(x, y, 5, 5));
        for (int i = 0; i < (number); i++) {

            if (x == y) {
                cards.get(i).getPanel().setSize((150 / x) * 4, (150 / y) * 4);
            } else {
                cards.get(i).getPanel().setSize((150 / x) * 4, (150 / y) * 4);
            }
            field.add(cards.get(i).getPanel());
        }


        getContentPane().add(infoPanel, BorderLayout.NORTH);
        getContentPane().add(field, BorderLayout.CENTER);

        for (int i = 0; i < number; i++) {
            cards.get(i).getPanel().setIcon(new ImageIcon(new ImageIcon(getClass().getResource("img/Memory.png")).getImage().getScaledInstance(cards.get(i).getPanel().getWidth(), cards.get(i).getPanel().getHeight(), Image.SCALE_SMOOTH)));
        }

        for (int i = 0; i < cards.size(); i++) {
            int index = i;
            cards.get(i).getPanel().addActionListener(e -> clickEvent(index));
        }

    }


    public void setTurned(int index, boolean val){
        if (val == true) {
            if (selectedOption == 1) {
                if(cards.get(index).getNumber() == 1){
                    cards.get(index).getPanel().setBorder(BorderFactory.createBevelBorder(1, Color.red, Color.blue));
                }
                    cards.get(index).getPanel().setIcon(new ImageIcon(new ImageIcon(getClass().getResource(cards.get(index).getImgAnime())).getImage().getScaledInstance(cards.get(index).getPanel().getWidth(), cards.get(index).getPanel().getHeight(), Image.SCALE_SMOOTH)));
            } else {
                if (cards.get(index).getNumber() == 1) {
                    cards.get(index).getPanel().setBorder(BorderFactory.createBevelBorder(1, Color.red, Color.blue));
                    cards.get(index).getPanel().setIcon(new ImageIcon(new ImageIcon(getClass().getResource(cards.get(index).getImgAnime())).getImage().getScaledInstance(cards.get(index).getPanel().getWidth(), cards.get(index).getPanel().getHeight(), Image.SCALE_SMOOTH)));
                } else {
                    cards.get(index).getPanel().setBackground(cards.get(index).getColor());
                    cards.get(index).getPanel().setIcon(null);
                }
            }
            cards.get(index).setTurned(true);
        }
        if (val == false) {
            cards.get(index).getPanel().setIcon(new ImageIcon(new ImageIcon(getClass().getResource("img/Memory.png")).getImage().getScaledInstance(cards.get(index).getPanel().getWidth(), cards.get(index).getPanel().getHeight(), Image.SCALE_SMOOTH)));
            cards.get(index).setTurned(false);
            cards.get(index).getPanel().setBorder(UIManager.getBorder("Button.border"));
        }
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
        if (check == cards.size()) {
            clip.stop();
            resultGUI = new ResultGUI(p1Points, p2Points);
            resultGUI.setSize(700,500);
            resultGUI.setVisible(true);
            setVisible(false);
        }
    }

    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }

    public Model getModel() {
        return model;
    }

    public int getActivePlayer() {
        return activePlayer;
    }

    public int getP1Points() {
        return p1Points;
    }

    public int getP2Points() {
        return p2Points;
    }

    public JLabel getPlayer1() {
        return player1;
    }

    public JLabel getPlayer2() {
        return player2;
    }

    public Vector<Card> getCards() {
        return cards;
    }

    public ResultGUI getResultGUI() {
        return resultGUI;
    }

    public void clickEvent(int index){
        if (cards.get(index).getTurned() == false) {
            if (wait == 1) {
                setTurned(c1, false);
                setTurned(c2, false);
                wait = 0;
            }
            setTurned(index, true);
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
                        infoPanel.setBackground(Color.green);
                    } else {
                        activePlayer = 1;
                        changePlayer(activePlayer);
                        infoPanel.setBackground(Color.red);
                    }
                }
            } else {
                state = 2;
                c1 = index;
            }
        }
    }
}

