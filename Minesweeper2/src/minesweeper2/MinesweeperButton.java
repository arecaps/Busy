package minesweeper2;

import javax.swing.JButton;

public class MinesweeperButton extends JButton {
    private int neighboringBombs;
    private boolean isBomb;
    private boolean checked;

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
}
