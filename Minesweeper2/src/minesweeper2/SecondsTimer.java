/*package minesweeper2;

import javax.swing.*;

public class SecondsTimer implements Runnable { //extends Thread
    private Minesweeper2 game;
    private int seconds;
    
    public SecondsTimer(Minesweeper2 minesweeper) {
        game = minesweeper;
    }
    
    public void run() {
        while(!game.isGameOver()) {
            try {
                Thread.sleep(100);
                ++seconds;
                int minutes = seconds / 60;
                int sec = seconds % 60;
                game.getTimerLabel().setText(minutes + ":" + (sec < 10 ? "0" : "") + sec);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
*/