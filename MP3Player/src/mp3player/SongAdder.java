
package mp3player;

import java.util.Scanner;

public class SongAdder {
    static Scanner sc = new Scanner(System.in);
    
    public static void addSong(){
        System.out.print("Please enter the name of the song you wish to add: ");
        String name = sc.nextLine();
        System.out.print("Please enter the name of this songs artist: ");
        String artist = sc.nextLine();
        System.out.print("Please enter the name of the album for this song: ");
        String album = sc.nextLine();
        Song song = new Song(name, album, artist);
        SongFile.files.add(song);
    } 
}
