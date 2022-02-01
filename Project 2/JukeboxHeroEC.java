import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
/* Project 2: JukeboxHero
 * Provides user an interface with which to load a song list from a csv
 * file and options for listing, searching and analysing data
 * @version Fall21
 * @author anthonygoeckner
 */
public class JukeboxHeroEC {
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        boolean menuLoop = true;
        ArrayList<Song> songList = new ArrayList<Song>();

        displayMenu();
        while (menuLoop) {
            System.out.print("\nPlease enter a command (press 'm' for Menu): ");
            String input = kbd.nextLine().toUpperCase();
            switch (input) {
                case "L":
                    loadCatalog(songList, kbd);
                    break;
                case "S":
                    searchCatalog(songList, kbd);
                    break;
                case "A":
                    analyseCatalog(songList);
                    break;
                case "P":
                    printCatalog(songList);
                    break;
                case "Q":
                    System.out.print("Goodbye!");
                    menuLoop = false;
                    break;
                case "M":
                    displayMenu();
                    break;
                default:
                    System.out.println("Invalid Selection");
            }
        }
        kbd.close();
    }

    /**
     * Prints the menu
     */
    private static void displayMenu() {
        System.out.println("*****************************");
        System.out.println("*      Program Menu         *");
        System.out.println("*****************************");
        System.out.println("(L)oad catalog");
        System.out.println("(S)earch catalog");
        System.out.println("(A)nalyse catalog");
        System.out.println("(P)rint catalog");
        System.out.println("(Q)uit");
    }

    /**
     * Loads the catalog of songs based on a csv file
     * @param sl ArrayList<Song> to keep a copy of all the songs in csv
     * @param kbd Scanner to parse csv file
     */
    private static void loadCatalog(ArrayList<Song> sl, Scanner kbd) {
        System.out.print("Load Catalog...\nPlease enter filename: ");
        String fileName = kbd.nextLine();
        File songCSV = new File(fileName);
        try {
            Scanner file = new Scanner(songCSV);
            file.useDelimiter(",");
            sl.clear();
            while (file.hasNextLine()) {
                String artist = file.next();
                String album = file.next();
                String title = file.next();
                int duration = Integer.parseInt(file.nextLine().substring(1));
                Song newSong = new Song(title, artist, album, duration);
                sl.add(newSong);
            }
            file.close();
            System.out.println("Successfully loaded " + sl.size() + " songs!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open file.");
        }
    }

    /**
     * prints contents of catalog given ArrayList of songs
     * @param sl ArrayList<Song> of songs loaded from csv
     */
    private static void printCatalog(ArrayList<Song> sl) {
        System.out.println("Song list contains " + sl.size() + " songs...");
        System.out.println("---------------------------------");
        for (Song s : sl) {
            System.out.println(s.toString());
        }
    }

    /**
     * searches the catalog based on the song's title
     * @param sl ArrayList<Song> of songs loaded from csv
     * @param kbd Scanner recieves search query from the user
     */
    private static void searchCatalog(ArrayList<Song> sl, Scanner kbd) {
        System.out.print("Search Catalog...\nPlease enter the search query: ");
        String query = kbd.nextLine().toUpperCase();
        ArrayList<Song> results = new ArrayList<Song>();
        for (Song s : sl) {
            if (s.getTitle().toUpperCase().contains(query)) {
                results.add(s);
            }
        }
        System.out.println("Song list contains " + results.size() + " songs...");
        System.out.println("---------------------------------");
        for (Song s : results) {
            System.out.println(s.toString());
        }
    }

    /**
     * provides an analysis of the catalog
     * @param sl list of songs loaded from csv
     */
    private static void analyseCatalog(ArrayList<Song> sl) {
        System.out.println("Catalog Analysis...");
        ArrayList<String> artist = new ArrayList<String>();
        ArrayList<String> album = new ArrayList<String>();
        int totalPlayTime = 0;
        for (Song s: sl) {
            if (!artist.contains(s.getArtist())) {
                artist.add(s.getArtist());
            }

            if (!album.contains(s.getAlbum())) {
                album.add(s.getAlbum());
            }

            totalPlayTime += s.getPlayTime();
        }
        System.out.println("\tNumber of Artists: " + artist.size());
        System.out.println("\tNumber of Albums: " + album.size());
        System.out.println("\tNumber of Songs: " + sl.size());
        System.out.println("\tCatalog Playtime: " + totalPlayTime);
    }
}