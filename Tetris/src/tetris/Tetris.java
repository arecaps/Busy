package tetris;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Tetris extends JFrame {

    public ArrayList<Piece> pieces = new ArrayList<>();
    private Color color= new Color(238, 238, 238);
    JPanel board;
    int shape = 5;
    int j = 0;
    

    public void dropPiece() {
        while (shape < 300) {
            for(Piece piece : pieces){
                if(piece.getBackground().equals(Color.red)){
                    piece.setBackground(color);
                }
            }
            for (int i = 1; i < 4; i++) {
                pieces.get(shape).setBackground(Color.red);
                shape++;
            }
            shape += 13;
            pieces.get(shape).setBackground(Color.red);
            board.repaint();
            try {
                Thread.sleep(750);
            } catch (InterruptedException ex) {
                return;
            }
        }
    }

    public void startGame() {
        Thread animationThread = new Thread(
                new Runnable() {
                    public void run() {
                        dropPiece();
                    }
                }
        );
        animationThread.start();
    }

    public void addButtons(JPanel board) {
        for (int i = 0; i < 320; i++) {
            Piece piece = new Piece();
            board.add(piece);
            pieces.add(piece);

        }
    }

    public Tetris() {
        setSize(600, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Tetris");
        JLabel title = new JLabel("TETRIS", JLabel.CENTER);
        title.setFont(new Font("Helvetica", Font.PLAIN, 24));
        add(title, BorderLayout.NORTH);
        JPanel controls = new JPanel();
        Controls controlPanel = new Controls(Tetris.this, controls);
        controlPanel.startButton();
        add(controls, BorderLayout.SOUTH);
        board = new JPanel();
        board.setLayout(new GridLayout(20, 16, 1, 1));
        addButtons(board);
        
        add(board);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {

        new Tetris();
    }
}
