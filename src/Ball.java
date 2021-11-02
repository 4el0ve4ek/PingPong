import java.awt.*;
import java.util.Random;

public class Ball {
    private int x, y;
    private double velocityX, velocityY;

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    private double acceleration;
    private final double MAX_SPEED = 100;
    private final int MAX_X, MAX_Y;
    private static final int normalVelocityX = 10, normalVelocityY = 0;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void changeVelocityY(double velocityY) {
        this.velocityY += velocityY;
    }

    public Ball(int borderX, int borderY) {
        MAX_X = borderX;
        MAX_Y = borderY;
        x = MAX_X / 2;
        y = MAX_Y / 2;
        acceleration = 0.025;

        var rnd = new Random();
        double angle;
        do{
            angle = rnd.nextDouble(Math.PI * 2);
        } while (Math.abs(angle - Math.PI / 2) <= 0.1 || Math.abs(angle - Math.PI * 3 / 2) <= 0.1);

        double normalVelocity = Math.sqrt(normalVelocityX * normalVelocityX + normalVelocityY * normalVelocityY);
        velocityX = normalVelocity * Math.cos(angle);
        velocityY = normalVelocity * Math.sin(angle);
    }

    public void move() {
        x += velocityX;
        y += velocityY;
        double velocity = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
        if (Math.abs(velocity) < MAX_SPEED) {
            double velocityNew = velocity + acceleration;
            velocityX = velocityNew * velocityX / velocity;
            velocityY = velocityNew * velocityY / velocity;
        }
        stabilize();
    }

    private void stabilize() {
        if (x <= 10) {
            x = 10;
            velocityX = normalVelocityX;
            velocityY = normalVelocityY;
        }
        if (x >= MAX_X - 10) {
            x = MAX_X - 10;
            velocityX = -normalVelocityX;
            velocityY = +normalVelocityY;
        }

        if (y <= 10) {
            y = 10;
            velocityY = -velocityY;
        }
        if (y >= MAX_Y - 10) {
            y = MAX_Y - 10;
            velocityY = -velocityY;
        }
    }


    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x - 10, y - 10, 20, 20);
    }

    public Rectangle getRect() {
        return new Rectangle(x - 10, y - 10, 20, 20);
    }
}
