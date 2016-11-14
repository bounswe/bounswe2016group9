package boun.cmpe451.group9.Service.Post;

import boun.cmpe451.group9.DAO.Post.PostDAO;
import boun.cmpe451.group9.Models.DB.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private PostDAO postDAO;

    @Autowired
    public void setPostDAO(PostDAO postDAO){
        this.postDAO = postDAO;
    }

    @Override
    public Post getPostById(long id) {
        return postDAO.getPostById(id);
    }

    @Override
    public void addPost(Post post) {
        postDAO.addPost(post);
    }

    @Override
    public void updatePost(Post post) {
        postDAO.updatePost(post);
    }

    @Override
    public void removePostById(long id) {
        postDAO.removePostById(id);
    }

    @Override
    public boolean checkIfPostExists(long id) {
        return postDAO.checkIfPostExistsById(id);
    }
}
