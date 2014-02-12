
package tetris;

import java.awt.event.*;
import javax.swing.*;

public class Controls {
    
    private Tetris game;
    private JPanel panel;
    
    public Controls(Tetris tetris, JPanel panel){
        game = tetris;
        this.panel = panel;
    }
    public void startButton(){
        JButton start = new JButton("START");
        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                game.startGame();
            }
        });
        start.addKeyListener(game);
        panel.add(start);
    }
}
