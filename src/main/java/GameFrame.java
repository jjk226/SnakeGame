import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class GameFrame extends JFrame {

    private static GamePanel panel;
    private JButton button;

    public GameFrame() {
        this.setSize(1000, 1000);
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new GamePanel();
        this.add(panel);
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }


}
