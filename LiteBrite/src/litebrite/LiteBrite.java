package litebrite;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JLabel;

public class LiteBrite extends JFrame {


    public void addButtons(JPanel board) {
        for (int i = 0; i < 2500; i++) {
            final JButton button = new JButton();
            button.setText("o");
            button.setForeground(Color.LIGHT_GRAY);
            button.setFont(new Font("Impact", Font.BOLD, 20));
            board.add(button);
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if(button.getForeground().equals(Color.LIGHT_GRAY))
                            button.setForeground(Color.RED);
                    else if(button.getForeground().equals(Color.RED))
                            button.setForeground(Color.GREEN);
                    else if(button.getForeground().equals(Color.GREEN))
                            button.setForeground(Color.BLUE);
                    else if(button.getForeground().equals(Color.BLUE))
                            button.setForeground(Color.LIGHT_GRAY);
                }
            });
        }
    }

    public LiteBrite() {
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("LiteBrite");
        JLabel title = new JLabel("LiteBrite", JLabel.CENTER);
        title.setFont(new Font("Helvetica", Font.PLAIN, 24));
        add(title, BorderLayout.NORTH);
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(50, 50));
        addButtons(board);
        this.add(board);
        setVisible(true);
    }

    public static void main(String[] args) {

        new LiteBrite();
    }
}