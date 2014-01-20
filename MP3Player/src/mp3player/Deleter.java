
package mp3player;

import java.util.Scanner;

public class Deleter {
    
    public static void deleteSong(){
        boolean found = false;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the song you want to delete: ");
        String choice = sc.nextLine();
        for(int i = 0; i<=SongFile.files.size()-1; i++){
            if(choice.equalsIgnoreCase(SongFile.files.get(i).getName())){
                SongFile.files.remove(i);
                found = true;
            }if(found == false){
                System.out.println("File not found\n");
            }
        }
    }
}
