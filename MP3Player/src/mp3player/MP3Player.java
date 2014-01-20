
package mp3player;

import java.util.Scanner;

public class MP3Player {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Enter P to Play, L to List all songs, E to edit, or Q to Quit");
            String choice = sc.next();
            switch(choice.toUpperCase()){
                case "Q":
                    System.exit(0);
                    break;
                case "E":
                    System.out.println("Enter A to add a song, or D to delete, M to return to main menu:");
                    String edit = sc.next();
                        switch(edit.toUpperCase()){
                            case "A":
                                SongAdder.addSong();
                                break;
                            case "D":
                                Deleter.deleteSong();
                                break;
                            default:
                                break;
                        }
                case "L":
                    SongFile.displayList();
                    break;
                case "P":
                    PlaySong.playSong();
            }
    }
    
}}
