import java.awt.*;

public interface Modifier {
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
    void paint(Graphics g);
    void doAction(Ball ball);
    Rectangle getRect();
}
