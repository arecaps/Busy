package minesweeper2;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ScoreBoard {

    BufferedReader in = null;
    BufferedWriter out = null;
    protected int[] scores = new int[10];
    int score;
    String level;
    String file;

    public ScoreBoard(int score, String level) throws IOException {
        boolean higher = false;
        this.score = score;
        this.level = level;
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
    public int[] getScoreList() {
        return scores;
    }

    public void fillArray() throws IOException {
        switch(level){
            case "med":
                file = "text/medScores.txt";
                break;
            case "hard":
                file = "text/hardScores.txt";
                break;
            default:
                file = "text/easyScores.txt";
                break;
        }
        try {
            in = new BufferedReader(new FileReader(file));
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
            out = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < 10; i++) {
                out.write(scores[i] + System.lineSeparator());
            }
        } finally {
            out.close();
        }
    }
}
