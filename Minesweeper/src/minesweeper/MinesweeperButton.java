package minesweeper;

import javax.swing.*;

public class MinesweeperButton extends JButton {
    private int neighboringBombs;
    private boolean isBomb;
    private boolean checked;

    private static ImageIcon flag = new ImageIcon("images/flag.jpg");

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    

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
    public void toggleFlag() {
        if (getIcon() != flag) {
            setIcon(flag);
        } else {
            setIcon(null);
        }
    }
}
