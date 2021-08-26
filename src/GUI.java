
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;
import java.util.Vector;

/**
 * @author Pascal Thuma, Francesco Ryu, Nils Rothenbühler
 * @since 2021-07-03
 * @version 1.0
 */
public class GUI extends JFrame {

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
    private int timeInt;
    private Timer t;
    private JLabel timeLabel;
    private JPanel middle;
    private int size;

    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/music/africa-toto.wav").getCanonicalFile());
    Clip clip = AudioSystem.getClip();

    /**
     *
     * @param y
     * @param x
     * @param mode //mode 0 = normal mode | mode 1 = test mode
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    GUI(int y, int x, int size, int mode) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Timer(15); //start timer (15 seconds)
        this.x = x;
        this.y = y;
        this.size = size;
        this.mode = mode;
        number = (y * x);
        if ((x * y) % 2 != 0) {
            number -= 1; //reduse number of cards if cards are odd
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

    /**
     * init Method
     * @throws IOException exception
     * @throws LineUnavailableException exception
     */
    public void init() throws IOException, LineUnavailableException {
        clip.open(audioInputStream);
        clip.start();
        player1 = new JLabel("Spieler 1");
        player2 = new JLabel("Spieler 2");
        pointsPlayer1 = new JLabel("Spieler 1: 0", SwingConstants.LEFT);
        pointsPlayer2 = new JLabel("Spieler 2: 0", SwingConstants.RIGHT);
        currentPlayer = new JLabel("Aktueller Spieler: 1",SwingConstants.CENTER);
        timeLabel = new JLabel("",SwingConstants.CENTER);

        pointsPlayer1.setFont(new Font("Georgia", Font.PLAIN,20));
        pointsPlayer2.setFont(new Font("Georgia", Font.PLAIN,20));
        currentPlayer.setFont(new Font("Georgia", Font.PLAIN,20));
        timeLabel.setFont(new Font("Georgia", Font.PLAIN,15));

        model = new Model();

        if(mode == 1){
            cards = model.shuffle2(number); //cards are not mixed (test mode)
        }
        else{
            cards = model.shuffle1(number); //cards are mixed (normal mode)
        }
        c1 = -1;
        c2 = -1;
        activePlayer = 1;
        state = 1;
        wait = 0;

        infoPanel = new JPanel(new GridLayout(1, 3));

        middle = new JPanel(new GridLayout(2,1));
        middle.add(currentPlayer);
        middle.add(timeLabel);

        infoPanel.add(pointsPlayer1);
        infoPanel.add(middle);
        infoPanel.add(pointsPlayer2);
        infoPanel.setSize(100, 50);


        field = new JPanel(new GridLayout(x, y, 5, 5));

        /**
         * cards are created
         */
        for (int i = 0; i < (number); i++) {

            if (x == y) {
                cards.get(i).getPanel().setSize(size ,size);
            } else {
                cards.get(i).getPanel().setSize(size, size);
            }
            field.add(cards.get(i).getPanel());
        }

        getContentPane().add(infoPanel, BorderLayout.NORTH);
        getContentPane().add(field, BorderLayout.CENTER);

        /**
         * Back of the card is created
         */
        for (int i = 0; i < number; i++) {
            cards.get(i).getPanel().setIcon(new ImageIcon(new ImageIcon(getClass().getResource("img/Memory.png")).getImage().getScaledInstance(cards.get(i).getPanel().getWidth(), cards.get(i).getPanel().getHeight(), Image.SCALE_SMOOTH)));
        }

        for (int i = 0; i < cards.size(); i++) {
            int index = i;
            cards.get(i).getPanel().addActionListener(e -> clickEvent(index));
        }

    }

    /**
     * turns or unturns the card
     * @param index index of card
     * @param val value
     */
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

    /**
     * change the current player
     * @param index index (number)
     */
    public void changePlayer(int index) {
        currentPlayer.setText("Aktueller Spieler: " + index);
        if(index == 1){
            pointsPlayer2.setOpaque(false);
            pointsPlayer1.setBackground(Color.green);
            pointsPlayer1.setOpaque(true);
            this.repaint();
        }
        else{
            pointsPlayer1.setOpaque(false);
            pointsPlayer2.setBackground(Color.green);
            pointsPlayer2.setOpaque(true);
            this.repaint();
        }
    }

    /**
     * set points to pointsLabel
     * @param index index of Player
     * @param points points
     */
    public void points(int index, int points) {
        if (index == 1) {
            pointsPlayer1.setText("Spieler 1: " + points);
        } else {
            pointsPlayer2.setText("Spieler 2: " + points);
        }
    }

    /**
     * checks if game is finished (if all cards are turned)
     */
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
            resultGUI.setSize(700, 500);
            resultGUI.setVisible(true);
            setVisible(false);
        }
    }

    /**
     * set Selected Option from MenuGUI
     * @param selectedOption selectedOption
     */
    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }

    /**
     * get Model
     * @return model
     */
    public Model getModel() {
        return model;
    }

    /**
     * returns the active Player
     * @return activePlayer
     */
    public int getActivePlayer() {
        return activePlayer;
    }

    /**
     * get Points of Player 1
     * @return p1Points
     */
    public int getP1Points() {
        return p1Points;
    }

    /**
     * get Points of Player 2
     * @return p2Points
     */
    public int getP2Points() {
        return p2Points;
    }

    /**
     * get Player 1 Label
     * @return player1
     */
    public JLabel getPlayer1() {
        return player1;
    }

    /**
     * get Player 2 Label
     * @return player2
     */
    public JLabel getPlayer2() {
        return player2;
    }

    /**
     * get cards Vector
     * @return cards
     */
    public Vector<Card> getCards() {
        return cards;
    }

    /**
     * get Instance of resultGUI
     * @return resultGUI
     */
    public ResultGUI getResultGUI() {
        return resultGUI;
    }

    /**
     * Timer
     * @param time time
     */
    public void Timer(int time) {
        this.timeInt = time;
        t = new Timer(950, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdown();
            }
        });
        t.start();
    }

    /**
     * countdown of Timer
     */
    public void countdown(){
        if (timeInt > 4) {
            timeLabel.setText(String.valueOf(timeInt));
            timeLabel.setOpaque(false);
            timeInt--;
            this.repaint();
        }
        else if(timeInt > -1){
            timeLabel.setText(String.valueOf(timeInt));
            timeLabel.setBackground(Color.red);
            timeLabel.setOpaque(true);
            timeInt--;
            this.repaint();
        }
        else {
            t.stop();
            if(activePlayer == 1){
                changePlayer(2);
                activePlayer = 2;
            }
            else{
                changePlayer(1);
                activePlayer = 1;
            }
        }
    }

    /**
     * When a player reveals a card, this method is executed
     * @param index index of card
     */
    public void clickEvent(int index){
        t.stop();
        Timer(15);
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

}

