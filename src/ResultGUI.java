import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Pascal Thuma, Francesco Ryu, Nils Rothenbühler
 * @since 2021-07-03
 * @version 1.0
 */

public class ResultGUI extends JFrame {

    private JPanel winnerPanel;
    private JLabel winner;
    private JPanel pointsPanel;
    private JLabel pointsPlayer1;
    private JLabel pointsPlayer2;
    private JPanel totalPointsPanel;
    private JLabel totalPointsLabel;
    private JPanel totalPointsPlayerPanel;
    private JLabel totalPointsPlayer1;
    private JLabel totalPointsPlayer2;
    private JButton restartGame;
    private JButton leave;
    private int [] points= new int[2];
    private int p1;
    private int p2;

    /**
     * ResultGUI constructor
     * @param p1
     * @param p2
     */
    ResultGUI(int p1, int p2){
        super("Memory Game - Result");
        this.p1 = p1;
        this.p2 = p2;
        points = loadPoints();
        winner = new JLabel("", SwingConstants.CENTER);
        if(p1 > p2){
            winner.setText("Sieger: Spieler 1");
            points[0]++;
        }
        else if(p2 > p1){
            winner.setText("Sieger: Spieler 2");
            points[1]++;
        }
        else{
            winner.setText("Sieger: Unentschieden");
            points[0]++;
            points[1]++;
        }
        init(p1,p2);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        writeToFile();
    }

    /**
     * init method
     * @param p1 points of Player 1
     * @param p2 points of Player 2
     */
    public void init(int p1, int p2){
        winnerPanel = new JPanel(new GridLayout(2,1,5,5));
        pointsPanel = new JPanel(new GridLayout(1,2,5,5));
        totalPointsPanel = new JPanel(new GridLayout(2,1,5,5));
        totalPointsPlayerPanel = new JPanel(new GridLayout(1,2,5,5));
        pointsPlayer1 = new JLabel("Spieler 1: " + (p1), SwingConstants.CENTER);
        pointsPlayer2 = new JLabel("Spieler 2: " + (p2), SwingConstants.CENTER);
        totalPointsLabel = new JLabel("Gesamte Punktzahl", SwingConstants.CENTER);
        totalPointsPlayer1 = new JLabel("Spieler1: " + points[0], SwingConstants.CENTER);
        totalPointsPlayer2 = new JLabel("Spieler2: " + points[1], SwingConstants.CENTER);

        totalPointsLabel.setFont(new Font("Georgia", Font.PLAIN,30));
        winner.setFont(new Font("Georgia", Font.PLAIN,30));

        restartGame = new JButton("Neues Spiel");
        leave = new JButton("Beenden");

        pointsPanel.add(pointsPlayer1);
        pointsPanel.add(pointsPlayer2);

        winnerPanel.add(winner);
        winnerPanel.add(pointsPanel);

        totalPointsPlayerPanel.add(totalPointsPlayer1);
        totalPointsPlayerPanel.add(totalPointsPlayer2);

        totalPointsPanel.add(totalPointsLabel);
        totalPointsPanel.add(totalPointsPlayerPanel);

        getContentPane().setLayout(new GridLayout(2,2,20,20));

        getContentPane().add(winnerPanel);
        getContentPane().add(totalPointsPanel);
        getContentPane().add(restartGame);
        getContentPane().add(leave);

        savePoints(points);

        /**
         * restarts the game
         */
        restartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePoints(points);
                setVisible(false);
                MenuGUI menuGUI = new MenuGUI();
                menuGUI.setSize(520,250);
            }
        });

        /**
         * leaves the game
         */
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    /**
     * loads the points out of a text file
     * @return temp (saved points)
     */
    public int[] loadPoints() {
        int[] temp = points;
        try {
            FileInputStream in = new FileInputStream("savePunkte.txt");
            ObjectInputStream s = new ObjectInputStream(in);
            temp = (int[]) s.readObject();
        } catch (Exception e) {
        }
        return temp;
    }

    /**
     * saves the points in a text file
     * @param save (points)
     */
    public void savePoints(int[] save) {
        try {
            FileOutputStream f = new FileOutputStream("savePunkte.txt");
            ObjectOutput s = new ObjectOutputStream(f);
            s.writeObject(save);
        } catch (Exception e) {
            System.out.println("Error saving" + e);
        }
    }

    /**
     * get the Winner
     * @return winner
     */
    public JLabel getWinner() {
        return winner;
    }

    /**
     * get the points
     * @return points
     */
    public int[] getPoints() {
        return points;
    }

    /**
     * Writes data of game to a file (saved in C:/MemoryGame_Auswertungen/filename.txt)
     */
    public void writeToFile(){
        String nameTime = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
        File file = new File("Auswertungen/auswertung_"+nameTime+".txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("Die Memoryspielauswertung vom " + timeStamp);
            writer.newLine();
            writer.write(winner.getText());
            writer.newLine();
            writer.write("Punkte " + pointsPlayer1.getText());
            writer.newLine();
            writer.write("Punkte " + pointsPlayer2.getText());
            writer.newLine();
            writer.write("Karten: " + ((p1+p2)*2));
            writer.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
