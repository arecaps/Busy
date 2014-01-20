
package mp3player;

public class Song {
    
    private String name;
    private String album;
    private String artist;
    
    public Song(String name, String album, String artist){
        this.name = name;
        this.album = album;
        this.artist = artist;
    }
    public String getName(){
        return name;
    }
    public String getAlbum(){
        return album;
    }
    public String getArtist(){
        return artist;
    }
    @Override
    public String toString(){
        String song = "Song:   "+ name 
                  + "\nAlbum:  " + album
                  + "\nArtist: " + artist + "\n";
        return song;
    }
}
