package boun.cmpe451.group9.Service.Comment;

import boun.cmpe451.group9.Service.BaseService;
import java.util.List;

import boun.cmpe451.group9.Models.DB.Comment;
/**
 * Created by seha on 19.11.2016.
 * Service method for Comment
 */
public interface CommentService extends BaseService<Comment> {
    /**
     * Retrieves all the comments created by the user "id"
     * @param id id of user
     * @return list of comments that created by user "id"
     */
    List<Comment> getCommentsByUserId(long id);

    /**
     * Retrieves all the comments under by post "id"
     * @param id id of post
     * @return List of comments that belongs to post
     */
    List<Comment> getCommentByPostId (long id);
}
