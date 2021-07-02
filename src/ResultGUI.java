import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
    private int [] punkte = new int[2];

    ResultGUI(int p1, int p2){
        super("Memory Game - Result");
        punkte = loadPunkte();
        init(p1,p2);
        if(p1 > p2){
            winner.setText("Sieger: Spieler 1");
            punkte[0]++;
        }
        else if(p2 > p1){
            winner.setText("Sieger: Spieler 2");
            punkte[1]++;
        }
        else{
            winner.setText("Sieger: Unentschieden");
            punkte[0]++;
            punkte[1]++;
        }
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }


    public void init(int p1, int p2){
        winnerPanel = new JPanel(new GridLayout(2,1,5,5));
        pointsPanel = new JPanel(new GridLayout(1,2,5,5));
        totalPointsPanel = new JPanel(new GridLayout(2,1,5,5));
        totalPointsPlayerPanel = new JPanel(new GridLayout(1,2,5,5));
        winner = new JLabel("");
        pointsPlayer1 = new JLabel("Spieler 1: " + (p1));
        pointsPlayer2 = new JLabel("Spieler 2: " + (p2));
        totalPointsLabel = new JLabel("Gesamte Punktzahl");
        totalPointsPlayer1 = new JLabel("Spieler1: " + punkte[0]);
        totalPointsPlayer2 = new JLabel("Spieler2: " + punkte[1]);

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

        getContentPane().setLayout(new GridLayout(2,2));

        getContentPane().add(winnerPanel);
        getContentPane().add(totalPointsPanel);
        getContentPane().add(restartGame);
        getContentPane().add(leave);

        restartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePunkte(punkte);
                setVisible(false);
                MenuGUI menuGUI = new MenuGUI();
                menuGUI.setSize(520,250);
            }
        });
        savePunkte(punkte);
    }


    public int[] loadPunkte() {
        int[] temp = punkte;
        try {
            FileInputStream in = new FileInputStream("savePunkte.txt");
            ObjectInputStream s = new ObjectInputStream(in);
            temp = (int[]) s.readObject();
        } catch (Exception e) {
        }
        return temp;
    }

    public void savePunkte(int[] save) {
        try {
            FileOutputStream f = new FileOutputStream("savePunkte.txt");
            ObjectOutput s = new ObjectOutputStream(f);
            s.writeObject(save);
        } catch (Exception e) {
            System.out.println("Error saving" + e);
        }
    }

    public JLabel getWinner() {
        return winner;
    }

    public int[] getPunkte() {
        return punkte;
    }

    public void setPunkte(int[] punkte) {
        this.punkte = punkte;
    }
}
