package mp3player;

import java.util.Scanner;

public class PlaySong {

    public static void playSong() {
        if (SongFile.files.isEmpty()) {
            System.out.println("File empty, please edit to add a new song." + System.lineSeparator());
        } else {
            boolean found = false;
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the name of the song you want to play: ");
            String choice = sc.nextLine();
            for (Song file : SongFile.files) {
                if (choice.equalsIgnoreCase(file.getName())) {
                    System.out.println("Now Playing..."
                            + "\nSong:   " + file.getName() + "\n");
                    found = true;
                } else if(found == false){
                    System.out.println("File not found\n");
                }
            }
        }
    }
}
