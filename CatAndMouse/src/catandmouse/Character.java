package catandmouse;

import java.awt.*;
import javax.swing.*;

public class Character extends JPanel{
    Color color;
    int size = 100;
    
    public Character(Color color){
        setSize(size, size);
        this.color = color;
        
}
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fillOval(0, 0, size, size);
    }
    
}
