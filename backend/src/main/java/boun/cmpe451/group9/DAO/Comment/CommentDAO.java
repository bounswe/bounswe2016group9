package boun.cmpe451.group9.DAO.Comment;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.Comment;

import java.util.List;

/**
 * Created by seha on 19.11.2016.
 * Database access methods of "COMMENT" table
 */
public interface CommentDAO extends BaseDAO<Comment> {
    /**
     *  Retrieves the list of row with the given user id from the table "COMMENT"
     * @param userID id of the user
     * @return list of comments
     */
    List<Comment> getCommentsByUserId(long userID);

    /**
     * Retrieves the list of row with the given post id from table "COMMENT"
     * @param postID id of the post
     * @return list of comments
     */
    List<Comment> getCommentsByPostId(long postID);

    /**
     * retrieves the list of most recent rows with given userIdList from the table "COMMENT"
     * @param userIdList the list of id's of users
     * @return List of comments
     */
    List<Comment> getCommentsByUserIdForTimeline(List<Long> userIdList);


}
