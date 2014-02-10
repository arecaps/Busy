package minesweeper2;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Minesweeper2 extends JFrame {

    int rows = 10;
    int columns = 10;
    int bombs = 10;
    MinesweeperButton[][] buttons;
    static boolean gameOver;
    int uncovered;
    JPanel board;
    JPanel header;
    JLabel title;
    static JTextField timeField = new JTextField("0:00", 3);

    private void showAll() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //buttons[i][j].setText(""+buttons[i][j].getNeighboringBombs());
                if (buttons[i][j].isBomb()) {
                    buttons[i][j].uncover();
                }
            }
        }
    }

    static void updateTime(String time) {
        timeField.setText(time);
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

    private void uncoverButton(int r, int c) {
        MinesweeperButton button = buttons[r][c];
        if (button.isEnabled()) {
            uncovered++;
            button.uncover();
            if (button.getNeighboringBombs() == 0) {
                for (int i = r - 1; i <= r + 1; i++) {
                    for (int j = c - 1; j <= c + 1; j++) {
                        if (i < 0 || i >= rows
                                || j < 0 || j >= columns) {
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
                JOptionPane.showMessageDialog(this, "You Win!!");
            }
        }
    }

    public void addButtons(JPanel board) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                MinesweeperButton button = new MinesweeperButton();
                final int row = i;
                final int column = j;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (!gameOver) {
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
        if (board != null) {
            remove(board);
        }
        if (header != null) {
            remove(header);
        }
        buttons = new MinesweeperButton[rows][columns];
        board = new JPanel();
        board.setLayout(new GridLayout(rows, columns));
        addButtons(board);
        placeBombs();
        header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.TRAILING, 120, 5));
        header.add(title);
        timeField.setEditable(false);
        timeField.setText("0:00");
        header.add(timeField);
        add(header, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);
        Timer timer = new Timer();
        timer.start();
        gameOver = false;
        uncovered = 0;
        validate();
    }

    public Minesweeper2() {
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("MINESWEEPER");
        title = new JLabel("MINESWEEPER", JLabel.CENTER);
        title.setForeground(Color.darkGray);
        title.setFont(new Font(title.getName(), Font.PLAIN, 24));
        //timeField = new JTextField("0:00", 3);
        timeField.setFont(new Font("DigitalFont.TTF", Font.BOLD, 25));
        timeField.setBackground(Color.WHITE);
        timeField.setForeground(Color.GRAY);
        timeField.setBorder(BorderFactory.createLoweredBevelBorder());
        //this.add(title, BorderLayout.NORTH);

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
                    gameOver = true;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
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

    public static void main(String[] args) {
        new Minesweeper2();
    }
}
