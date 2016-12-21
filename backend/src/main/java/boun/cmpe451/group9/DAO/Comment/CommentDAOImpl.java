package boun.cmpe451.group9.DAO.Comment;

import boun.cmpe451.group9.DAO.BaseDAOImpl;
import boun.cmpe451.group9.Models.DB.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by seha on 19.11.2016.
 */
@SuppressWarnings({"unchecked", "DefaultFileTemplate"})
@Repository
public class CommentDAOImpl extends BaseDAOImpl<Comment> implements CommentDAO {
    @Override
    public List<Comment> getCommentsByUserId(long userID) {

        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT * FROM comment c WHERE c.created_user_id = :id")
                .addEntity(Comment.class)
                .setParameter("id", userID)
                .list();
    }

    @Override
    public List<Comment> getCommentsByPostId(long postID) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT * FROM comment c WHERE c.post_of_comment_id = :id")
                .addEntity(Comment.class)
                .setParameter("id", postID)
                .list();
    }

    @Override
    public List<Comment> getCommentsByUserIdForTimeline(List<Long> userIdList) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT * FROM comment where user_id in :user_list ORDER by creation_time DESC LIMIT 3")
                .addEntity(Comment.class)
                .setParameterList("user_list",userIdList)
                .list();
    }
}
