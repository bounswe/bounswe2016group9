package boun.cmpe451.group9.DAO.Post;

import boun.cmpe451.group9.Models.DB.Post;

import java.util.List;

/**
 * Created by seha on 12.11.2016.
 * Database access methods for the table "POST"
 */
public interface PostDAO {

    /**
     * Adds a new row in the table "POST"
     * @param post the obj representation of the added row
     */
    void addPost(Post post);

    /**
     * Updates the row with the given id from the table "USER"
     * @param post  the obj representation of the updated row
     */
    void updatePost(Post post);

    /**
     *
     * Removes the row with the given id from the table "POST"
     * @param postID
     */
    void removePostById(long postID);

    /**
     * Retrieves the row with the given id from the table "POST"
     * @param postID the id of the row
     * @return the obj representation of the row
     */
    Post getPostById(long postID);

    /**
     * Retrieves the list of row with the given user id from the table "POST"
     * @param userID the id of the user
     * @return List of post
     */
    List<Post> getPostByUserId(long userID);


    /**
     * Retrieves the row with the given id from the table "POST"
     * @param locationID the id of the post
     * @return List of posts
     */
    List<Post> getPostByLocation(int locationID);

    boolean checkIfPostExistsById(long id);
}
