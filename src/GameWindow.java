import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameWindow extends JPanel {

    private Ball ball;
    private final int WIDTH = 600, HEIGHT = 400;
    private int score = 0;
    private final int FINAL_SCORE = 21;
    private Timer timer;
    private Plank userPlank, compPlank;
    private int userScore = 0, compScore = 0;
    private Modifier mod;

    public GameWindow() {
        init();
    }

    private void init() {
        setSize(WIDTH, HEIGHT);
        setLocation(0, 0);
        ball = new Ball(WIDTH, HEIGHT);
        userPlank = new Plank(10, HEIGHT);
        compPlank = new Plank(WIDTH - 10, HEIGHT);

        addKeyListener(new MoveListener());
        setFocusable(true);
        timer = new Timer(50, new UpdateCoords());
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);

        Font score = new Font("ZapfDingbats", Font.PLAIN, 18);
        g.setFont(score);
        g.drawString(Integer.toString(userScore), 30, 30);
        g.drawString(Integer.toString(compScore), WIDTH - 40, 30);

        ball.paint(g);
        userPlank.paint(g);
        compPlank.paint(g);

        if(mod != null)
            mod.paint(g);
    }

    private class MoveListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN)
                userPlank.setVelocityY(+10);
            if (e.getKeyCode() == KeyEvent.VK_UP)
                userPlank.setVelocityY(-10);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN)
                userPlank.setVelocityY(0);
            if (e.getKeyCode() == KeyEvent.VK_UP)
                userPlank.setVelocityY(0);
        }
    }

    private class UpdateCoords implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ball.move();
            userPlank.move();
            compPlank.move();

            if (ball.getRect().intersects(userPlank.getRect())) {
                ball.setX(30);
                ball.setVelocityX(-ball.getVelocityX());
                ball.changeVelocityY(+userPlank.getVelocityY() * 0.5);
            }

            if (ball.getRect().intersects(compPlank.getRect())) {
                ball.setX(WIDTH - 30);
                ball.setVelocityX(-ball.getVelocityX());
                ball.changeVelocityY(+compPlank.getVelocityY() * 0.5);
            }
            compPlank.setVelocityY((ball.getY() - compPlank.getY()) * 0.3);

            if(ball.getX() <= 10) {
                ball = new Ball(WIDTH, HEIGHT);
                compScore++;
            }

            if(ball.getX() >= WIDTH - 10) {
                ball = new Ball(WIDTH, HEIGHT);
                userScore++;
            }

            if(mod == null){
                var rnd = new Random();
                int modX = rnd.nextInt(30, WIDTH - 30);
                int modY = rnd.nextInt(10, HEIGHT - 10);
                mod = new SpeedUpModifier(modX, modY);
            }

            if(mod.getRect().intersects(ball.getRect())){
                mod.doAction(ball);
                mod = null;
            }
            repaint();
        }
    }
}
