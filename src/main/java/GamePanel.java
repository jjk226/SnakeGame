import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static java.awt.font.TextAttribute.FONT;

public class GamePanel extends JPanel implements ActionListener {

    public static final int SCREEN_WIDTH = 500;
    public static final int SCREEN_HEIGHT = 500;
    public static final int UNIT_SIZE = 10;
    public static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
    public static int DELAY = 175;
    public final int[] x = new int[GAME_UNITS];
    public final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    public static int numberOfObstacles = 10;
    public static int[] obstacleX = new int[numberOfObstacles];
    public static int[] obstacleY = new int[numberOfObstacles];
    char direction = 'D';
    boolean running = false;
    boolean gameOver = false;
    Timer timer;
    Random random;

    public GamePanel() {
        System.out.println(">> GamePanel constructor");
        this.random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setLocation(0, 0);
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
        this.newObstacle();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
        System.out.println(">> delay: " + DELAY);
    }

    public void draw(Graphics g) {
        if (running) {
//            g.setColor(Color.gray);
//            for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
//                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
//                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
//            }

            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < numberOfObstacles; i++) {
                g.setColor(Color.ORANGE);
                g.fillRect(obstacleX[i], obstacleY[i], UNIT_SIZE, UNIT_SIZE);
            }

            //draw the snake
            for (int i = 0; i < bodyParts; i++) {
                g.setColor(Color.GREEN);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            g.setFont(new Font("SansSerif", Font.PLAIN, 12));
            g.setColor(Color.WHITE);
            g.drawString("apples: " + applesEaten, UNIT_SIZE/2, g.getFont().getSize());

        } else {
            this.gameOver(g);
        }

    }

    public void newApple() {
        this.appleX = random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE) * UNIT_SIZE;
        this.appleY = random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE) * UNIT_SIZE;

    }

    public void newObstacle() {
        for (int i = 0; i < numberOfObstacles; i++) {
            obstacleX[i] = random.nextInt(SCREEN_WIDTH/UNIT_SIZE) * UNIT_SIZE;
            obstacleY[i] = random.nextInt(SCREEN_HEIGHT/UNIT_SIZE) * UNIT_SIZE;

            if (obstacleX[i] == appleX && obstacleY[i] == appleY) {
                obstacleX[i] = -1;
                obstacleY[i] = -1;
            }
        }

    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
        }


    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            newApple();
            newObstacle();
            DELAY -= 5;
            this.timer.setDelay(DELAY);
        }
    }

    public void checkCollisions() {
        for (int i = 1; i < bodyParts; i++) {
            //check head-to-body collision
            if (x[0] == x[i] && y[0] == y[i]) {
                System.out.println("Collision detected");
                running = false;
            }
        }

        //check if head touches obstacle
        for (int i = 0; i < numberOfObstacles; i++) {
            if (x[0] == obstacleX[i] && y[0] == obstacleY[i]) {
                System.out.println("Collision detected");
                running = false;
            }
        }

        //check if head touches border
        if (x[0] == SCREEN_WIDTH  || x[0] < 0) {
            System.out.println("Collision detected");
            running = false;
        }

        if (y[0] == SCREEN_HEIGHT  || y[0] < 0) {
            System.out.println("Collision detected");
            running = false;
        }

        if(!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        FontMetrics metrics = g.getFontMetrics();

        g.drawString("game over", SCREEN_WIDTH/2 - metrics.stringWidth("game over")/2,
                SCREEN_HEIGHT/2 - metrics.getHeight()/2 - g.getFont().getSize());

        g.drawString("apples: " + applesEaten, SCREEN_WIDTH/2 - metrics.stringWidth("apples:  ")/2,
                SCREEN_HEIGHT/2 - metrics.getHeight()/2 + g.getFont().getSize());

        g.drawString("Press Enter to Restart", SCREEN_WIDTH/2 - metrics.stringWidth("Press Enter to Restart")/2,
                SCREEN_HEIGHT/2 - metrics.getHeight()/2 + 3 * (g.getFont().getSize()));

        this.gameOver = true;

    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case 37: {
                    if (direction == 'R') {
                        break;
                    } else {
                        direction = 'L';
                    }
                    break;
                }
                case 38: {
                    if (direction == 'D') {
                        break;
                    } else {
                        direction = 'U';
                    }
                    break;
                }
                case 39: {
                    if (direction == 'L') {
                        break;
                    } else {
                        direction = 'R';
                    }
                    break;
                }
                case 40: {
                    if (direction == 'U') {
                        break;
                    } else {
                        direction = 'D';
                    }
                    break;
                }

            }
            if (gameOver == true && e.getKeyCode() == KeyEvent.VK_ENTER) {
                reset();
            }

        }

        private void reset() {
            GamePanel.this.reset();
        }

    }

    private void reset() {
        gameOver = false;
        direction = 'D';
        applesEaten = 0;
        DELAY = 175;
        bodyParts = 6;

        for (int i = 0; i < GAME_UNITS; i++) {
            x[i] = 0;
            y[i] = 0;
        }

        this.repaint();
        this.startGame();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //performs action when running == true;
        if (running) {
            this.move();
            this.checkApple();
            this.checkCollisions();
        }
        this.repaint();
    }
}
