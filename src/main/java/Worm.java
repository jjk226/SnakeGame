import java.awt.*;

public class Worm {

    int[] x = new int[GamePanel.GAME_UNITS];
    int[] y = new int[GamePanel.GAME_UNITS];
    static int UNIT_SIZE = GamePanel.UNIT_SIZE;
    static int bodyParts = 6;
    static char direction = 'L';

    public Worm() {
        x[0] = 200;
        y[0] = 50;

    }

    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        for (int i = 0; i < bodyParts; i++) {
            g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        }

    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch(direction) {
            case ('L'):
                x[0] = x[0] - UNIT_SIZE;
        }
    }
}
