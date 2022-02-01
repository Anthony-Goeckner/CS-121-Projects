import java.util.ArrayList;
/**
 * Using the TextBookInterface, provides methods for working with 
 * ArrayList<Post> Objects created in Post.java
 * @author anthonygoeckner
 * @version Fall 21
 */
public class TextBook implements TextBookInterface {
    public ArrayList<Post> posts;

    /**
     * Constructor, initiates posts as an empty ArrayList<Post>
     */
    public TextBook() {
        this.posts = new ArrayList<Post>();
    }

    /**
     * Getter
     * @return ArrayList<Post> copy of variable posts
     */
    public ArrayList<Post> getPosts() {
        ArrayList<Post> toReturn = new ArrayList<Post>();
        for (Post p : this.posts) {
            toReturn.add(p);
        }
        return toReturn;
    }

    /**
     * adds new post to local list
     * @param newPost post to be added to list
     */
    public void addPost(Post newPost) {
        this.posts.add(newPost);
    }

    /**
     * removes the post at the specified index
     * @param index of post to be removed
     */
    public void removePost(int index) {
        if (index >= 0 && index < this.posts.size()) {
            this.posts.remove(index);
        }
    }

    /**
     * returns the Post object at specified index
     * @param index of post to be returned
     */
    public Post getPost(int index) {
        if (index >= 0 && index < this.posts.size()) {
            return this.posts.get(index);
        } else {
            return null;
        }
    }

    /**
     * @return String of all posts in list
     */
    public String toString() {
        String toReturn = "";
        for (int i = 0; i < posts.size(); ++i) {
            Post currentPost = this.posts.get(i);
            toReturn += currentPost.toString() + "\n";
        }
        return toReturn;
    }
}