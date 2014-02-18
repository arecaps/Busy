package minesweeper2;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;

public class Minesweeper2 extends JFrame {
    int rows = 10;
    int columns = 10;
    int bombs = 10;
    MinesweeperButton [][] buttons;
    boolean gameOver;
    int uncovered;
    JPanel board;
    JLabel time;
    Thread timer;
    private int seconds;
    
    public void displayHigh(int[] scoreList){
        String list = "The 10 highest scores ..." + System.lineSeparator();
         for (int i = 0; i < 10; i++) {
                list += (scoreList[i] + System.lineSeparator());
            }
        JOptionPane.showMessageDialog(this, list);
    }
    private void showAll() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                //buttons[i][j].setText(""+buttons[i][j].getNeighboringBombs());
                if (buttons[i][j].isBomb()) {
                    buttons[i][j].uncover();
                }
            }
        }
    }
    
    private void placeBomb() {
        Random random = new Random();
        while(true) {
            int r = random.nextInt(rows);
            int c = random.nextInt(columns);
            if (! buttons[r][c].isBomb()) {
                buttons[r][c].setIsBomb(true);
                for (int i = r - 1; i <= r + 1; i++) {
                    for(int j = c - 1; j <= c + 1; j++) {
                        if (i < 0 || i >= rows ||
                                j < 0 || j >= columns ||
                                (i == r && j == c)) {
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
    
    private void uncoverButton(int r, int c) {
        MinesweeperButton button = buttons[r][c];
        if (button.isEnabled()) {
            uncovered++;
            button.uncover();
            if (button.getNeighboringBombs() == 0) {
                for (int i = r - 1; i <= r + 1; i++) {
                    for(int j = c - 1; j <= c + 1; j++) {
                        if (i < 0 || i >= rows ||
                                j < 0 || j >= columns) {
                            continue;
                        }
                        uncoverButton(i, j);
                    }
                }
            }
        }
    }
    
    private void checkButton(int row, int column) {
        MinesweeperButton button = buttons[row][column];
        if (button.isBomb()) {
            gameOver = true;
            showAll();
            JOptionPane.showMessageDialog(this, "You Lose, Game Over!");
        } else {
            uncoverButton(row, column);
            if (uncovered == (rows * columns) - bombs) {
                gameOver = true;
                try {
                    new ScoreBoard(seconds);
                } catch (IOException ex) {
                    return;
                }
                JOptionPane.showMessageDialog(this, "You Win! Your time was " + seconds + " seconds.");
            }
        }
    }
    
    public void addButtons(JPanel board) {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                MinesweeperButton button = new MinesweeperButton();
                final int row = i;
                final int column = j;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (!gameOver) {
                            if (timer == null) {
                                //timer = new Thread(new SecondsTimer(Minesweeper2.this));
                                //timer.start();
                                timer = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        while(!gameOver && ! Thread.interrupted()) {
                                            try {
                                                Thread.sleep(1000);
                                                ++seconds;
                                                int minutes = seconds / 60;
                                                int sec = seconds % 60;
                                                time.setText(minutes + ":" + (sec < 10 ? "0" : "") + sec);
                                            } catch (InterruptedException e) {
                                                return;
                                            }
                                        }
                                    }  
                                });
                                timer.start();
                            }
                            if (e.getButton() == MouseEvent.BUTTON1) {
                                checkButton(row, column);
                            } else {
                                MinesweeperButton button = buttons[row][column];
                                if (button.isEnabled()) {
                                    button.toggleFlag();
                                }
                            }
                        }
                    }
                });
                board.add(button, i, j);
                buttons[i][j] = button;
            }
        }
    }
    
    public void startGame() {
        if (timer != null) {
            timer.interrupt();
        }
        timer = null;
        seconds = 0;
        time.setText("0:00");
        if (board != null) {
            remove(board);
        }
        buttons = new MinesweeperButton[rows][columns];
        board = new JPanel();
        board.setLayout(new GridLayout(rows, columns));
        addButtons(board);
        placeBombs();
        add(board, BorderLayout.CENTER);
        uncovered = 0;
        validate();
        gameOver = false;
    }
    
    public Minesweeper2() {
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("MINESWEEPER");
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("MINESWEEPER", JLabel.CENTER);
        title.setForeground(Color.darkGray);
        title.setFont(new Font(title.getName(), Font.PLAIN, 24));
        titlePanel.add(title, BorderLayout.CENTER);
        
        time = new JLabel("0:00");
        time.setFont(new Font(time.getName(), Font.BOLD, 24));
        time.setOpaque(true);
        time.setBackground(Color.BLACK);
        time.setForeground(Color.WHITE);
        time.setBorder(new EmptyBorder(2,2,2,2));
        titlePanel.add(time, BorderLayout.EAST);
        
        add(titlePanel, BorderLayout.NORTH);
        
        JMenuBar menus = new JMenuBar();
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        ButtonGroup group = new ButtonGroup();
        JMenu difficulty = new JMenu("Difficulty");
        difficulty.setMnemonic(KeyEvent.VK_D);
        JRadioButtonMenuItem easy = new JRadioButtonMenuItem("Easy");
        group.add(easy);
        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameOver || JOptionPane.showConfirmDialog(Minesweeper2.this, "Are You sure you want to start a new game?", "Start New Game?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    rows = 8;
                    columns = 6;
                    bombs = 5;
                    startGame();
                }
            }
        });
        
        JRadioButtonMenuItem medium = new JRadioButtonMenuItem("Medium", null, true);
        group.add(medium);
        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameOver || JOptionPane.showConfirmDialog(Minesweeper2.this, "Are You sure you want to start a new game?", "Start New Game?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    rows = 10;
                    columns = 10;
                    bombs = 10;
                    startGame();
                }
            }
        });
        
        JRadioButtonMenuItem hard = new JRadioButtonMenuItem("Hard");
        group.add(hard);
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameOver || JOptionPane.showConfirmDialog(Minesweeper2.this, "Are You sure you want to start a new game?", "Start New Game?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    rows = 12;
                    columns = 12;
                    bombs = 20;
                    startGame();
                }
            }
        });
        
        JRadioButtonMenuItem custom = new JRadioButtonMenuItem("Custom");
        group.add(custom);
        custom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameOver || JOptionPane.showConfirmDialog(Minesweeper2.this, "Are You sure you want to start a new game?", "Start New Game?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    CustomDialog customDialog = new CustomDialog(Minesweeper2.this);
                    if (customDialog.buttonSelected == JOptionPane.YES_OPTION) {
                        rows = customDialog.getNumberOfRows();
                        columns = customDialog.getNumberOfColumns();
                        bombs = customDialog.getNumberOfBombs();
                        startGame();
                    }
                }
            }
        });
        
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameOver || JOptionPane.showConfirmDialog(Minesweeper2.this, "Are You sure you want to start a new game?", "Start New Game?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    startGame();
                }
            }
        });

        difficulty.add(easy);
        difficulty.add(medium);
        difficulty.add(hard);
        difficulty.add(custom);
        
        file.add(difficulty);
        file.add(new JSeparator());
        file.add(newGame);
        
        menus.add(file);
        
        this.setJMenuBar(menus);
        
        startGame();
        setVisible(true);
    }
    
    /*public boolean isGameOver() {
        return gameOver;
    }
    
    public JLabel getTimerLabel() {
        return time;
    }*/
    
    public static void main(String[] args) {
        new Minesweeper2();
    }
}
