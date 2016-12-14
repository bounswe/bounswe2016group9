package boun.cmpe451.group9.DAO.Post;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.Post;

import java.util.List;

/**
 * Created by seha on 12.11.2016.
 * Database access methods for the table "POST"
 */
@SuppressWarnings("unused")
public interface PostDAO extends BaseDAO<Post> {

    /**
     * Retrieves the list of row with the given user id from the table "POST"
     * @param userID the id of the user
     * @return List of posts
     */
    List<Post> getPostByUserId(long userID);

    /**
     * Retrieves the list of row with the given topic id from the table "POST"
     * @param topicID the id of the user
     * @return List of posts
     */
    List<Post> getPostsByTopicId(long topicID);


    /**
     * Retrieves the row with the given id from the table "POST"
     * @param locationID the id of the post
     * @return List of posts
     */
    List<Post> getPostByLocation(int locationID);

    List<Post> searchPostByName(String[] split);
}
