package boun.cmpe451.group9.DAO.Post;

import boun.cmpe451.group9.Models.DB.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by seha on 12.11.2016.
 */
@Repository
public class PostDAOImpl implements PostDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addPost(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(post);
    }

    @Override
    public void updatePost(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(post);
    }

    @Override
    public void removePostById(long postID) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getPostById(postID));
    }


    @Override
    public Post getPostById(long postID) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Post.class, postID);
    }

    @Override
    public List<Post> getPostByUserId(long userID) {
        Session session = sessionFactory.getCurrentSession();
    
        return (List<Post>) session.createSQLQuery("SELECT * FROM post p WHERE p.user_id = :id")
                .addEntity(Post.class)
                .setParameter("id", userID)
                .list();
    }

    @Override
    public List<Post> getPostByLocation(int locationID) {
        return null;
    }

    @Override
    public boolean checkIfPostExistsById(long id) {
        return getPostById(id) != null;
    }
}
