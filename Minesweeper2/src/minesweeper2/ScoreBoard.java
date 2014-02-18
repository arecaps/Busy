package minesweeper2;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ScoreBoard {

    BufferedReader in = null;
    BufferedWriter out = null;
    protected int[] scores = new int[10];
    int score;

    public ScoreBoard(int score) throws IOException {
        boolean higher = false;
        this.score = score;
        fillArray();
        for (int i = 0; i < 10; i++) {
            int x = scores[i];
            if (score < x || x == 0) {
                higher = true;
            }
        }
        if (higher) {
            if (scores[0] == 0) {
                scores[0] = score;
            } else {
                scores[9] = score;
            }
                Arrays.sort(scores);
                writeScores();
           
        }

    }

    public int getScore() {
        return score;
    }

    public void fillArray() throws IOException {
        try {
            in = new BufferedReader(new FileReader("text/mineSweeperScores.txt"));
            int j = 0;
            String line;
            while ((line = in.readLine()) != null) {
                if (j < 10) {
                    int x = Integer.parseInt(line);
                    scores[j] = x;
                    j++;
                }
            }
        } finally {
            in.close();
        }
    }

    public void writeScores() throws IOException {
        try {
            out = new BufferedWriter(new FileWriter("text/mineSweeperScores.txt"));

            for (int i = 0; i < 10; i++) {
                out.write(scores[i] + System.lineSeparator());
            }
        } finally {
            out.close();
        }
        Minesweeper2.displayHigh(scores);
    }
}
