import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements KeyListener, ActionListener {

    final int WIDTH = 525, HEIGHT = 550;
    final int wallXVelocity = 5;
    final int wallWidth = 50;
    int birdHeight = HEIGHT / 4;
    int velocity = 0, acceleration = 8, impulse = 1;
    int score = 0;
    int[] wallX = {WIDTH, WIDTH + WIDTH / 2};
    int[] gap = {(int) (Math.random() * (HEIGHT - 150)), (int) (Math.random() * (HEIGHT - 150))};
    boolean gameOver = false;
    Timer time = new Timer(40, this);

    public Panel() {
        setSize(WIDTH, HEIGHT);
        setFocusable(true);
        addKeyListener(this);
        setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!gameOver) {
            g.setColor(Color.YELLOW);
            g.drawString("SCORE: " + score, WIDTH / 2, 10);
            drawWall(g);
            logic();
            drawBird(g);
        } else {
            g.setColor(Color.YELLOW);
            g.drawString("SCORE: " + score, WIDTH / 2, 10);
        }
    }

    private void drawBird(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(75, birdHeight + velocity, 25, 25);
    }

    private void drawWall(Graphics g) {
        for (int i = 0; i < 2; i++) {

            g.setColor(Color.RED);
            g.fillRect(wallX[i], 0, wallWidth, HEIGHT);

            g.setColor(Color.BLACK);
            g.fillRect(wallX[i], gap[i], wallWidth, 100);
        }
    }

    private void logic() {
        for (int i = 0; i < 2; i++) {
            if (wallX[i] <= 100 && wallX[i] + wallWidth >= 100 || wallX[i] <= 75
                    && wallX[i] + wallWidth >= 75) {
                if (birdHeight + velocity >= 0 && birdHeight + velocity <= gap[i]
                        || birdHeight + velocity + 25 >= gap[i] + 100 && birdHeight + velocity + 25 <= HEIGHT) {
                    gameOver = true;
                }
            }

            if (birdHeight + velocity <= 0 || birdHeight + velocity + 25 >= HEIGHT) {
                gameOver = true;
            }

            if (75 > wallX[i] + wallWidth) {
                score++;
            }

            if (wallX[i] + wallWidth <= 0) {
                wallX[i] = WIDTH;
                gap[i] = (int) (Math.random() * (HEIGHT - 150));
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        acceleration += impulse;
        velocity += acceleration;
        wallX[0] -= wallXVelocity;
        wallX[1] -= wallXVelocity;
        repaint();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == e.VK_SPACE) {
            acceleration = -10;
        }

        if (code == e.VK_S) {
            time.start();
        }

        if (code == e.VK_R) {
            time.stop();
            birdHeight = HEIGHT / 4;
            velocity = 0;
            acceleration = 8;
            score = 0;
            wallX[0] = WIDTH;
            wallX[1] = WIDTH + WIDTH / 2;
            gap[0] = (int) (Math.random() * (HEIGHT - 150));
            gap[1] = (int) (Math.random() * (HEIGHT - 150));
            gameOver = false;
            repaint();
        }

    }

    public void keyReleased(KeyEvent e) {

    }

}
