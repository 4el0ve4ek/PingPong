import java.awt.*;

public class Plank {
    private int x, y;
    private double velocityY;
    private final int MAX_Y;

    public Plank(int x, int maxY) {
        this.x = x;
        MAX_Y = maxY;
        this.y = MAX_Y / 2;
        velocityY = 0;
    }

    public void move() {
        y += velocityY;
        stabilize();
    }

    private void stabilize() {
        if (y <= 30) y = 30;
        if (y >= MAX_Y - 30) y = MAX_Y - 30;
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x - 5, y - 30, 10, 60);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public Rectangle getRect() {
        return new Rectangle(x - 5, y - 30, 10, 60);
    }
}
