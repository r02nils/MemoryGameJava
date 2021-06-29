import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {

    private JPanel[] cards = new JPanel[20];
    private JPanel field;

    GUI() throws IOException {
        this.setTitle("Memory Game");

        init();
        pack();
        setVisible(true);
    }

    public void init() throws IOException {
        field = new JPanel(new GridLayout(4, 5, 10, 10));
        BufferedImage image = ImageIO.read(new File("m.png"));
        JLabel img = new JLabel(new ImageIcon(image));
        img.setSize(50,50);
        for (int i = 0; i < 20; i++) {
            cards[i] = new JPanel();
            cards[i].setBackground(Color.gray);
            cards[i].add(img);
            field.add(cards[i]);
        }

        getContentPane().add(field);
    }
}
