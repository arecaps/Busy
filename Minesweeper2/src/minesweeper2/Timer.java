package minesweeper2;

public class Timer extends Thread {

    @Override
    public void run() {

        for (int i = 1; i < 10000; i++) {
            while (!Minesweeper2.gameOver) {
                try {
                    Timer.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
                if (i < 10) {
                    Minesweeper2.updateTime("0:0" + i);
                } else if (i < 60) {
                    Minesweeper2.updateTime("0:" + i);
                } else {
                    Minesweeper2.updateTime(i / 60 + ":" + i % 60);
                }
                break;
            }
        }
    }

}
