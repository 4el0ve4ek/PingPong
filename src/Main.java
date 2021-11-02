import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        mainFrame.setResizable(false);
        mainFrame.setSize(610, 435);
        mainFrame.setTitle("Ping-Pong");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().add(new GameWindow());
        mainFrame.setVisible(true);
    }
}
