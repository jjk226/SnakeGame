import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;

public class GamePanel extends JPanel implements ActionListener {

    public static final int SCREEN_WIDTH = 500;
    public static final int SCREEN_HEIGHT = 500;
    public static final int UNIT_SIZE = 20;
    public static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
    public static final int DELAY = 75;
    public final int[] x = new int[GAME_UNITS];
    public final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    public GamePanel() {
        System.out.println(">> GamePanel constructor");
        this.random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.startGame();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw(g);
    }

    public void startGame() {
        this.newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void draw(Graphics g) {
        g.setColor(Color.gray);
        for (int i = 0; i < SCREEN_WIDTH/UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }

        g.setColor(Color.RED);
        g.fillOval(appleX , appleY, UNIT_SIZE, UNIT_SIZE);

    }

    public void newApple() {
        this.appleX = random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE) * UNIT_SIZE;
        this.appleY = random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE) * UNIT_SIZE;

    }

    public void move() {

    }

    public void checkApple() {

    }

    public void checkCollisions() {

    }

    public void gameOver(Graphics g) {

    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
