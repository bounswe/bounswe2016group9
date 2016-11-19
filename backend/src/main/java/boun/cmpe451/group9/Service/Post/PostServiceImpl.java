
package boun.cmpe451.group9.Service.Post;

import boun.cmpe451.group9.Models.DB.Post;
import boun.cmpe451.group9.Service.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import boun.cmpe451.group9.DAO.Post.PostDAO;

import java.util.List;

@Service
public class PostServiceImpl extends BaseServiceImpl<Post> implements PostService {
    private PostDAO postDAO;

    @Autowired
    public void setPostDAO(PostDAO postDAO){
        this.postDAO = postDAO;
    }

    @Override
    public List<Post> getPostByUserId(long id) {
        return postDAO.getPostByUserId(id);
    }
}
