package minesweeper;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;



public class Minesweeper2 extends JFrame {

    int rows = 10;
    int columns = 10;
    int bombs = 12;
    MinesweeperButton[][] buttons = new MinesweeperButton[rows][columns];
    boolean gameOver;
    int uncovered;
    private static ImageIcon bomb = new ImageIcon("images/bomb.jpg");

    private void showAll() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //buttons[i][j].setText("" + buttons[i][j].getNeighboringBombs());
                if (buttons[i][j].isBomb()) {
                    buttons[i][j].setIcon(bomb);
                }
            }
        }
    }

    private void placeBomb() {
        Random random = new Random();
        while (true) {
            int r = random.nextInt(rows);
            int c = random.nextInt(columns);
            if (!buttons[r][c].isBomb()) {
                buttons[r][c].setIsBomb(true);
                for (int i = r - 1; i <= r + 1; i++) {
                    for (int j = c - 1; j <= c + 1; j++) {
                        if (i < 0 || i >= rows
                                || j < 0 || j >= columns
                                || (i == r && j == c)) {
                            continue;
                        }

                        buttons[i][j].setNeighboringBombs(
                                buttons[i][j].getNeighboringBombs() + 1);
                    }
                }
                break;
            }
        }
    }

    private void placeBombs() {
        for (int i = 0; i < bombs; i++) {
            placeBomb();
        }
    }

    private void checkNeighbors(int row, int column) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {

                if (i < 0 || i >= rows
                        || j < 0 || j >= columns
                        || (i == row && j == column)) {
                    continue;
                }
                if (buttons[i][j].isChecked()) {
                    continue;
                }
                buttons[i][j].setBorderPainted(false);
                buttons[i][j].setChecked(true);
                uncovered++;
                if(uncovered == rows*columns-bombs){
                    JOptionPane.showMessageDialog(this, "You Win!");
                }
                if (buttons[i][j].getNeighboringBombs() > 0) {
                    buttons[i][j].setText("" + buttons[i][j].getNeighboringBombs());
                } else {
                    checkNeighbors(i, j);
                }
            }

        }
    }

    private void askPlayAgain() {
        //write here code for play again
    }

    private void checkButton(MinesweeperButton button) {
        int row = (int) button.getClientProperty("row");
        int column = (int) button.getClientProperty("column");
        if (button.isBomb()) {
            showAll();
            gameOver = true;
            showAll();
            JOptionPane.showConfirmDialog(this, "Game Over! Would you like to play again?");
            askPlayAgain();
        } else {
            button.setBorderPainted(false);
            if (button.getNeighboringBombs() > 0) {
                button.setText("" + button.getNeighboringBombs());
                uncovered++;
            } else {
                checkNeighbors(row, column);
                //prints out button clicked, for debugging
                //System.out.println("clicked row " + button.getClientProperty("row")
                // + ", column " + button.getClientProperty("column"));

            }
        }
    }

    public void addButtons(JPanel board) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                final MinesweeperButton button = new MinesweeperButton();
                button.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            button.toggleFlag();
                        } else if (e.getButton() == MouseEvent.BUTTON1) {
                            MinesweeperButton source = (MinesweeperButton) e.getSource();
                            if (!gameOver) {
                                checkButton(button);
                            }
                        }
                    }
                });
                board.add(button, i, j);
                buttons[i][j] = button;
                buttons[i][j].putClientProperty("row", i);
                buttons[i][j].putClientProperty("column", j);
            }
        }
    }

    public Minesweeper2() {
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("MINESWEEPER");
        JLabel title = new JLabel("MINESWEEPER", JLabel.CENTER);
        title.setForeground(Color.darkGray);
        title.setFont(new Font(title.getName(), Font.PLAIN, 24));
        this.add(title, BorderLayout.NORTH);
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(rows, columns));
        addButtons(board);
        placeBombs();
        add(board, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Minesweeper2();
    }
}
