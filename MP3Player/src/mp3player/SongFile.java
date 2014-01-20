package mp3player;

import java.util.ArrayList;

public class SongFile {

    public static ArrayList<Song> files = new ArrayList<>();

    public static void displayList() {
        if (files.isEmpty()) {
            System.out.println("File empty, please edit to add a new song."+System.lineSeparator());
        } else {
            for (Song file : files) {
                System.out.println(file);
            }
        }
    }
}
