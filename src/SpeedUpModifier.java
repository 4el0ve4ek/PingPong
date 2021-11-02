import java.awt.*;

public class SpeedUpModifier implements Modifier{

    private int x;

    public SpeedUpModifier(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int y;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x - 5, y - 5, 10, 10);
    }

    @Override
    public void doAction(Ball ball) {
        new ActionThread(ball).start();
    }

    @Override
    public Rectangle getRect(){
        return new Rectangle(x - 5, y - 5, 10, 10);
    }

    private static class ActionThread extends Thread{
        private final Ball ball;

        public ActionThread(Ball ball) {
            super();
            this.ball = ball;
        }

        public void run(){
            ball.setAcceleration(2);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ball.setAcceleration(0.1);
        }
    }
}
