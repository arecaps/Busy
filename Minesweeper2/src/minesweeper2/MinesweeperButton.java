package minesweeper2;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MinesweeperButton extends JButton {
    private int neighboringBombs;
    private boolean isBomb;
    private static ImageIcon bomb = new ImageIcon("images/bomb.gif");
    private static ImageIcon flag = new ImageIcon("images/flag.gif");
    
    public boolean isBomb() {
        return isBomb;
    }

    public void setIsBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    public int getNeighboringBombs() {
        return neighboringBombs;
    }

    public void setNeighboringBombs(int neighboringBombs) {
        this.neighboringBombs = neighboringBombs;
    }
    
    public void uncover() {
        if (isBomb) {
            setIcon(bomb);
        } else if (neighboringBombs > 0) {
            setText(""+neighboringBombs);
        }
        setEnabled(false);
    }
    
    public void toggleFlag() {
        if (getIcon() != flag) {
            setIcon(flag);
        } else {
            setIcon(null);
        }
    }
}
