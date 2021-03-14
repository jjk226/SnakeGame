import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class GameFrame extends JFrame implements ActionListener {

    private static GamePanel panel;
    private JButton button;

    public GameFrame() {
        this.setSize(1000, 1000);
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        button = new JButton();
        button.setText("Reset");
        button.setSize(50, 25);
        button.setLocation(0, 50);
        button.addActionListener(this);
        button.setVisible(true);

        this.panel = new GamePanel();

        this.add(button);
        this.add(panel);

        this.setVisible(true);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == (this.button)) {
            this.remove(panel);
            this.panel = new GamePanel();
            this.add(panel);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }


    //need to add restart functionanlity here at the Frame level??

}
