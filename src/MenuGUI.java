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

public class MenuGUI extends JFrame {

    private JPanel menuPanel;
    private JComboBox tiles;
    private JLabel tilesLabel;
    private JPanel tilesBox;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private ButtonGroup radioButtons;
    private JPanel radioPanel;
    private JLabel imagesLabel;
    private JPanel imagesBox;
    private JButton start;
    private JButton leave;
    private JLabel label;
    private JPanel selectionPanel;

    private int selectedOption;

    MenuGUI() {
        super("Memory Game Menu");
        setResizable(false);
        init();
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    private void init() {
        String [] tilesArr = {"4x4", "5x4","5x5","10x10"};

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

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI gui;
                try {
                    if(tiles.getSelectedIndex() == 0){
                        gui = new GUI(4,4);
                        gui.setSize(600,620);
                    }
                    else if(tiles.getSelectedIndex() == 1){
                        gui = new GUI(5,4);
                        gui.setSize(600,620);
                    }
                    else if(tiles.getSelectedIndex() == 2){
                        gui = new GUI(5,5);
                        gui.setSize(600,620);
                    }
                    else{
                        gui = new GUI(10,10);
                        gui.setSize(600,620);
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
