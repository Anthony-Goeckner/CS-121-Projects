import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
/**
 * Drives the TextBook.java class, providing user interactivity
 * @author anthonygoeckner
 * @version Fall 21
 */
public class TextBookDriver {
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        System.out.print("Please enter your name to enter Textbook: ");
        String author = kbd.nextLine();
        System.out.println("Welcome, " + author + ", to Textbook: the text based social media site!");
        printMenu();
        boolean done = false;
        TextBook tb = new TextBook();

        while(!done) {
            System.out.print("Please select an option from the list (P,A,D,R,C,Q): ");
            String choice = kbd.nextLine().toUpperCase();
            ArrayList<Post> posts = tb.getPosts();
            switch(choice) {
                case "P":
                    printList(posts);
                    break;
                case "A":
                    System.out.print("Please complete the following...\nPost text: ");
                    String postText = kbd.nextLine();
                    tb.addPost(new Post(postText, author));
                    break;
                case "D":
                    printList(posts);
                    if (posts.size() == 0) 
                        break;
                    System.out.print("Please enter the index of the post to delete (0 - " + (posts.size() - 1) + "): ");
                    int postDIndex = Integer.parseInt(kbd.nextLine());
                    if (postDIndex >= 0 && postDIndex < posts.size()) {
                        tb.removePost(postDIndex);
                    }
                    break;
                case "R":
                    printList(posts);
                    if (posts.size() == 0) 
                        break;
                    System.out.print("Please enter the index of the post to read (0 - " + (posts.size() - 1) + "): ");
                    int postRIndex = Integer.parseInt(kbd.nextLine());
                    if (postRIndex >= 0 && postRIndex < posts.size()) {
                        Post toRead = posts.get(postRIndex);
                        String fileName = toRead.getPostFilename();
                        try {
                            File readFile = new File(fileName);
                            Scanner file = new Scanner(readFile);
                            System.out.println("Comments for: " + file.nextLine());
                            while (file.hasNextLine()) {
                                System.out.println(" - " + file.nextLine());
                            }
                            file.close();
                        } catch (FileNotFoundException fnfe) {
                            System.out.println("Post file could not be found.");
                        }
                    }
                    break;
                case "C":
                    printList(posts);
                    if (posts.size() == 0)
                        break;
                    System.out.print("Please enter the index of the post to add a comment on (0 - " + (posts.size() - 1) + "): ");
                    int postCIndex = Integer.parseInt(kbd.nextLine());
                    if (postCIndex >= 0 && postCIndex < posts.size()) {
                        Post postComment = posts.get(postCIndex);
                        System.out.print("Enter the comment you have for Post " + postCIndex + ": ");
                        String comment = kbd.nextLine();
                        postComment.addComment(author, comment);
                    } else {
                        System.out.println("Index out of range.");
                    }
                    break;
                case "Q":
                    System.out.println("Goodbye.");
                    done = true;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }

        kbd.close();
    }

    /**
     * prints the Textbook Menu
     */
    private static void printMenu() {
        System.out.println("Textbook Menu:\n-----------------------------------");
        System.out.println("(P)rint TextBook posts");
        System.out.println("(A)dd a new post");
        System.out.println("(D)elete a post");
        System.out.println("(C)omment on a post");
        System.out.println("(R)ead a post's comments");
        System.out.println("(Q)uit");
        System.out.println("-----------------------------------");
    }

    /**
     * loops through an ArrayList<Post> to print info from every post
     * @param posts <ArrayList<Post> of posts to be printed
     */
    private static void printList(ArrayList<Post> posts) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("TextBook has " + posts.size() + " post(s).");
        for (int i = 0; i < posts.size(); ++i) {
            System.out.println(i + " - " + posts.get(i).toString());
        }
        System.out.println("------------------------------------------------------------------");
    }
}
