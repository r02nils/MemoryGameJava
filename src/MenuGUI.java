
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author Pascal Thuma, Francesco Ryu, Nils Rothenbühler
 * @since 2021-07-03
 * @version 1.0
 */

public class MenuGUI extends JFrame {

    private JPanel menuPanel;
    private JComboBox tiles;
    private JLabel tilesLabel;
    private JPanel tilesBox;
    private JRadioButton option1;
    private JRadioButton option2;
    private ButtonGroup radioButtons;
    private JPanel radioPanel;
    private JLabel imagesLabel;
    private JPanel imagesBox;
    private JButton start;
    private JButton leave;
    private JLabel label;
    private JPanel selectionPanel;

    /**
     * MenuGUI constructor
     */
    MenuGUI() {
        super("Memory Game Menu");
        setResizable(false);
        init();
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * init Method
     */
    private void init() {
        String [] tilesArr = {"4x4", "5x4","5x5","6x5","6x6","7x6","7x7","8x7","8x8","9x8","9x9","10x9","10x10", "3x2 (Test)"};
        label = new JLabel("Memory Game Menu", SwingConstants.CENTER);
        menuPanel = new JPanel();
        tiles = new JComboBox(tilesArr);
        option1 = new JRadioButton("Anime");
        option2 = new JRadioButton("Farben");
        start = new JButton("Spiel starten");
        leave = new JButton("Beenden");
        tilesLabel = new JLabel("Anzahl Felder", SwingConstants.CENTER);
        imagesLabel = new JLabel("Bilder Auswahl", SwingConstants.CENTER);
        radioPanel = new JPanel(new GridLayout(1,3));
        selectionPanel = new JPanel();
        radioButtons = new ButtonGroup();
        tilesBox = new JPanel(new BorderLayout());
        imagesBox = new JPanel(new BorderLayout());

        label.setFont(new Font("Georgia", Font.PLAIN,30));
        tilesLabel.setFont(new Font("Georgia", Font.PLAIN,15));
        imagesLabel.setFont(new Font("Georgia", Font.PLAIN,15));

        radioButtons.add(option1);
        radioButtons.add(option2);

        option2.setSelected(true);

        radioPanel.add(option1);
        radioPanel.add(option2);

        Border border = label.getBorder();
        Border margin = new EmptyBorder(0,0,20,0);
        label.setBorder(new CompoundBorder(border, margin));

        menuPanel.setLayout(new BorderLayout());
        selectionPanel.setLayout(new GridLayout(2, 2, 30, 30));

        tilesBox.add(tilesLabel, BorderLayout.NORTH);
        tilesBox.add(tiles, BorderLayout.CENTER);

        imagesBox.add(imagesLabel, BorderLayout.NORTH);
        imagesBox.add(radioPanel, BorderLayout.CENTER);

        selectionPanel.add(tilesBox);
        selectionPanel.add(imagesBox);
        selectionPanel.add(leave);
        selectionPanel.add(start);

        menuPanel.add(label, BorderLayout.NORTH);
        menuPanel.add(selectionPanel, BorderLayout.CENTER);

        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        menuPanel.setBorder(padding);

        getContentPane().add(menuPanel);

        /**
         * code gets executed if start gets clicked
         */
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI gui;
                try {
                    switch (tiles.getSelectedIndex()){
                        case 0: gui = new GUI(4,4,150,0); gui.setSize(600,620); break;
                        case 1: gui = new GUI(5,4,150,0); gui.setSize(750,620); break;
                        case 2: gui = new GUI(5,5,100,0); gui.setSize(500,520); break;
                        case 3: gui = new GUI(6,5,100,0); gui.setSize(600,520); break;
                        case 4: gui = new GUI(6,6,100,0); gui.setSize(600,620); break;
                        case 5: gui = new GUI(7,6,100,0); gui.setSize(700,620); break;
                        case 6: gui = new GUI(7,7,75,0); gui.setSize(525,545); break;
                        case 7: gui = new GUI(8,7,75,0); gui.setSize(600,545); break;
                        case 8: gui = new GUI(8,8,75,0); gui.setSize(600,620); break;
                        case 9: gui = new GUI(9,8,75,0); gui.setSize(675,625); break;
                        case 10: gui = new GUI(9,9,75,0); gui.setSize(675,695); break;
                        case 11: gui = new GUI(10,9,70,0); gui.setSize(700,650); break;
                        case 12: gui = new GUI(10,10,60,0); gui.setSize(600,620); break;
                        case 13: gui = new GUI(3,2,150,1); gui.setSize(450,320); break;
                        default: gui = new GUI(4,4,150,0); gui.setSize(600,620); break;
                    }

                    if(option1.isSelected()){
                        gui.setSelectedOption(1);
                    }
                    else if(option2.isSelected()){
                        gui.setSelectedOption(2);
                    }
                    else{
                        gui.setSelectedOption(0);
                    }
                    setVisible(false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
            }
        });
    }
}
