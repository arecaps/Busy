
package tetris;

import java.awt.Color;
import javax.swing.JButton;

public class Piece extends JButton {

    private Color color= new Color(238, 238, 238);

    public Piece() {

        setBackground(color);
        setOpaque(true);
        setBorderPainted(false);
    }
}