import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
/**
 * Using PostInterface, provides methods for creating Posts
 * and comments, and getting the values of Posts and comments
 * from a file
 * @author anthonygoeckner
 * @version Fall 21
 */
public class Post implements PostInterface {
    private String text;
    private String author;
    private Date date;
    private long postID;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static long IDno = 200200100;

    /**
     * constructor for Post object
     * @param text post's message
     * @param author post's author
     */
    public Post(String text, String author) {
        this.text = text;
        this.author = author;
        this.date = new Date();
        this.postID = IDno;
        IDno++;
        File file = new File(getPostFilename());
        try {
            file.createNewFile();
            PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
            pw.println(sdf.format(this.date) + ": " + text + " (" + author + "; " + getPostFilename() + ")");
            pw.close();
        } catch (IOException ioe) {
            System.out.println("Unexpected error occured");
        }
    }
    
    /**
     * returns a string of the post, formatted to be readbale
     * @return String containing text, author, date, and post ID
     */
    public String toString() {
        String contents = "";
        contents += sdf.format(this.date);
        contents += ": " + text + " (" + author + ", " + postID + ")";
        return contents;
    }

    /**
     * Getter
     * @return String containing text of the post
     */
    public String getText() {
        return this.text;
    }

    /**
     * Getter
     * @return String containing author of the post
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Getter
     * @return Date object of the post's date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Getter
     * @return long containing post's ID number
     */
    public long getPostID() {
        return this.postID;
    }

    /**
     * uses current post's ID to create a file name
     * @return String containing file name of the post
     */
    public String getPostFilename() {
        return "posts/Post-" + this.postID + ".txt";
    }

    /**
     * Opens file provided by getPostFilename()
     * and returns the whole contents as a String
     * @return String object of contents of text file
     */
    public String getFileContents() {
        String contents = "";
        try {
            File file = new File(getPostFilename());
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                contents += fileReader.nextLine() + "\n";
            }
            fileReader.close();
            return contents;
        }
        catch (FileNotFoundException fnfe) {
            return "File could not be opened.";
        }
    }

    /**
     * adds a comment by appending the author and comment to the text file
     * @param author String of author's name
     * @param comment String containing comment text
     */
    public void addComment(String author, String comment) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File(getPostFilename()), true));
            String toAppend = "";
            Date date = new Date();
            toAppend += sdf.format(date) + ": " + comment + " (" + author + ")";
            pw.println(toAppend);
            pw.close();
        }
        catch (FileNotFoundException fnfe) {
            System.out.println("File could not be found");
        }
    }

    /**
     * checks if post is valid by checking that there
     * is text, an author and the post file exists
     * @return boolean true if valid, false if invalid
     */
    public boolean isValid() {
        if (text != null && author != null && !getPostFilename().equals("File could not be opened.")) {
            return true;
        } else {
            return false;
        }
    }
}