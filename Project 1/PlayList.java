import java.util.Scanner;
import java.text.DecimalFormat;
/**
 * Collects 3 songs from the user, returning ...
 * @author anthonygoeckner
 */

public class PlayList {
    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in);
        // collect all information from user
        System.out.print("Enter title: ");
        String title = scnr.nextLine();
        System.out.print("Enter artist: ");
        String artist = scnr.nextLine();
        System.out.print("Enter album: ");
        String album = scnr.nextLine();
        System.out.print("Enter play time (mm:ss): ");
        String playTime = scnr.nextLine();
        int colon = playTime.indexOf(":");
        int minutes = Integer.parseInt(playTime.substring(0, colon));
        int seconds = Integer.parseInt(playTime.substring(colon + 1, colon + 3));
        int totalSeconds = (minutes * 60) + seconds;
        
        Song song1 = new Song(title, artist, album, totalSeconds);

        System.out.print("Enter title: ");
        title = scnr.nextLine();
        System.out.print("Enter artist: ");
        artist = scnr.nextLine();
        System.out.print("Enter album: ");
        album = scnr.nextLine();
        System.out.print("Enter play time (mm:ss): ");
        playTime = scnr.nextLine();
        colon = playTime.indexOf(":");
        minutes = Integer.parseInt(playTime.substring(0, colon));
        seconds = Integer.parseInt(playTime.substring(colon + 1, colon + 3));
        totalSeconds = (minutes * 60) + seconds;
        
        Song song2 = new Song(title, artist, album, totalSeconds);

        System.out.print("Enter title: ");
        title = scnr.nextLine();
        System.out.print("Enter artist: ");
        artist = scnr.nextLine();
        System.out.print("Enter album: ");
        album = scnr.nextLine();
        System.out.print("Enter play time (mm:ss): ");
        playTime = scnr.nextLine();
        colon = playTime.indexOf(":");
        minutes = Integer.parseInt(playTime.substring(0, colon));
        seconds = Integer.parseInt(playTime.substring(colon + 1, colon + 3));
        totalSeconds = (minutes * 60) + seconds;
        
        Song song3 = new Song(title, artist, album, totalSeconds);

        scnr.close();

        // calculate and format average playtime
        double average = (song1.getPlayTime() + song2.getPlayTime() + song3.getPlayTime()) / 3.0;
        DecimalFormat twoDecimals = new DecimalFormat("#.00");
        System.out.println("Average play time for songs: " + twoDecimals.format(average) + "\n");

        // closest song to 240 seconds
        int song1Distance = Math.abs(song1.getPlayTime() - 240);
        int song2Distance = Math.abs(song2.getPlayTime() - 240);
        int song3Distance = Math.abs(song3.getPlayTime() - 240);

        Song smallest = song1Distance < song2Distance ? song1 : song2;
        smallest = Math.abs(smallest.getPlayTime() - 240) < song3Distance ? smallest : song3;
        System.out.println("Song with play time closest to 240 secs is: " + smallest.getTitle() + "\n");

        // print sorted playlist
        System.out.println("========================================================================================");
        System.out.println("Title                          Artist               Album                           Time");
        System.out.println("========================================================================================");

        Song shortest = song1.getPlayTime() <= song2.getPlayTime() ? song1 : song2;
        shortest = shortest.getPlayTime() <= song3.getPlayTime() ? shortest : song3;

        Song longest = song2.getPlayTime() >= song1.getPlayTime() ? song2 : song1;
        longest = longest.getPlayTime() > song3.getPlayTime() ? longest : song3;

        Song middle = song1;
        if (song2 != shortest && song2 != longest)
            middle = song2;
        else if (song3 != shortest && song3 != longest)
            middle = song3;

        System.out.println(shortest);
        System.out.println(middle);
        System.out.println(longest);
        System.out.println("========================================================================================");
    }
}