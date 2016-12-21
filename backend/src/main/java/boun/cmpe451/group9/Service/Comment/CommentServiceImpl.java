package boun.cmpe451.group9.Service.Comment;

import boun.cmpe451.group9.DAO.Comment.CommentDAO;
import boun.cmpe451.group9.Models.DB.Comment;
import boun.cmpe451.group9.Service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by seha on 19.11.2016.
 */
@SuppressWarnings({"unchecked", "DefaultFileTemplate"})
@Service
@Transactional(readOnly = true)
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService{

    private CommentDAO commentDAO;

    @Autowired
    public void setCommentDAO(CommentDAO commentDAO){ this.commentDAO = commentDAO; }

    @Override
    public List<Comment> getCommentsByUserId(long id) {
        return commentDAO.getCommentsByUserId(id);
    }

    @Override
    public List<Comment> getCommentByPostId(long id) {
        return commentDAO.getCommentsByPostId(id);
    }

    @Override
    public List<Comment> getCommentsByUserIdForTimeline(List<Long> userIdList) { return commentDAO.getCommentsByUserIdForTimeline(userIdList);
    }
}
