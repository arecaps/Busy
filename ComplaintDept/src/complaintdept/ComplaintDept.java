package complaintdept;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class ComplaintDept extends JFrame {

    int x = 275;
    int y = 300;

    public void runAway(JButton button, int a, int b) {
        if (a < 250) {
            a += 275;
        } else if (a > 350) {
            a -= 275;
        } else {
            a += 150;
        }
        if (b < 300) {
            b += 300;
        } else if (b > 400) {
            b -= 300;
        } else {
            b += 150;
        }
        button.setLocation(a, b);
        x = a;
        y = b;

    }

    public ComplaintDept() {
        setSize(700, 700);
        this.setTitle("Complaint Department");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel board = new JPanel();
        board.setLayout(null);
        final JButton button = new JButton("Press to complain");
        button.setSize(new Dimension(125, 75));
        board.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int a = e.getX();
                int b = e.getY();
                if ((a > x - 30 && a < x + 155)
                        && (b > y - 30 && b < y + 105)) {
                    runAway(button, a, b);
                }

            }
        });
        add(board);
        board.add(button);
        button.setLocation(x, y);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ComplaintDept();
    }

}
