import java.awt.*;

public class Snake {

    static final int[] x = new int[GamePanel.GAME_UNITS];
    static final int[] y = new int[GamePanel.GAME_UNITS];
    static int bodyParts = 6;
    static int applesEaten;
    private char direction = 'D';


    public Snake() {

    }

    public void draw(Graphics g) {
        for (int i = 0; i < bodyParts; i++) {
            g.setColor(Color.GREEN);
            g.fillRect(x[i], y[i], GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
        }
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'R':
                x[0] = x[0] + GamePanel.UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - GamePanel.UNIT_SIZE;
                break;
            case 'U':
                y[0] = y[0] - GamePanel.UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + GamePanel.UNIT_SIZE;
                break;
        }

    }
}
