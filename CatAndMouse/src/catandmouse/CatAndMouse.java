package catandmouse;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class CatAndMouse extends JFrame {

    private int paneWidth;
    private int paneHeight;
    int characterSpeed = 10;
    Character cat;
    Character mouse;
    boolean paused;
    int speed = 150;
    int catX = 10;//target x and y points for cat
    int catY = 10;
    int mouseX = 350;//target x and y points for mouse
    int mouseY = 250;
    boolean gameOver = false;

    public void setCat() {
        cat.setLocation(catX, catY);
    }

    public void setMouse() {
        mouse.setLocation(mouseX, mouseY);
    }

    public void checkCaught() {
        if ((catX > mouseX - mouse.size && catX < mouseX + mouse.size)
                && (catY > mouseY - mouse.size && catY < mouseY + mouse.size)) {
            gameOver = true;
            JOptionPane.showMessageDialog(this, "Your toast!");
            System.exit(0);
        }
    }

    public void startChase() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!gameOver) {
                    if (mouse.getX() > cat.getX()) {
                        catX += characterSpeed;
                    } else if (mouse.getX() < cat.getX()) {
                        catX -= characterSpeed;
                    }
                    if (mouse.getY() > cat.getY()) {
                        catY += characterSpeed;
                    } else if (mouse.getY() < cat.getY()) {
                        catY -= characterSpeed;
                    }
                    setCat();
                    checkCaught();
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException ex) {
                        return;
                    }
                }
            }
        }).start();
    }

    public CatAndMouse() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        cat = new Character(Color.BLACK);
        add(cat);
        mouse = new Character(Color.GREEN);
        add(mouse);
        setMouse();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        mouseX = mouseX - characterSpeed;
                        break;
                    case KeyEvent.VK_RIGHT:
                        mouseX = mouseX + characterSpeed;
                        break;
                    case KeyEvent.VK_DOWN:
                        mouseY = mouseY + characterSpeed;
                        break;
                    case KeyEvent.VK_UP:
                        mouseY = mouseY - characterSpeed;
                        break;
                    case KeyEvent.VK_P:
                        paused = !paused;
                        break;
                }
                mouseX = Math.max(0, mouseX);
                mouseX = Math.min(mouseX, paneWidth - mouse.size);
                mouseY = Math.max(0, mouseY);
                mouseY = Math.min(mouseY, paneHeight - mouse.size);
                setMouse();
            }
        });
        JMenuBar menu = new JMenuBar();
        JMenu start = new JMenu("Start");
        JRadioButtonMenuItem strt = new JRadioButtonMenuItem("Start");
        strt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                startChase();
            }
        });
        JMenu gameSpeed = new JMenu("Speed");
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem slow = new JRadioButtonMenuItem("Slow");
        group.add(slow);
        slow.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                speed = 300;
            }
        });
        JRadioButtonMenuItem med = new JRadioButtonMenuItem("Medium");
        group.add(med);
        med.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                speed = 200;
            }
        });
        JRadioButtonMenuItem fast = new JRadioButtonMenuItem("Fast");
        group.add(fast);
        fast.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                speed = 100;
            }
        });
        start.add(strt);
        menu.add(start);
        gameSpeed.add(slow);
        gameSpeed.add(med);
        gameSpeed.add(fast);
        menu.add(gameSpeed);
        this.setJMenuBar(menu);
        setVisible(true);
        paneWidth = this.getContentPane().getWidth();
        paneHeight = this.getContentPane().getHeight();
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                paneWidth = CatAndMouse.this.getContentPane().getWidth();
                paneHeight = CatAndMouse.this.getContentPane().getHeight();
            }
        });
    }

    public static void main(String[] args) {
        new CatAndMouse();
    }

}
