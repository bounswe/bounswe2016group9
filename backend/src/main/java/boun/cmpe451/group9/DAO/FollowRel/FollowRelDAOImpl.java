package boun.cmpe451.group9.DAO.FollowRel;

import boun.cmpe451.group9.Models.DB.FollowRel;
import boun.cmpe451.group9.Models.DB.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FollowRelDAOImpl implements FollowRelDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addFollowRel(FollowRel followRel) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(followRel);
    }

    @Override
    public FollowRel getFollowRelById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(FollowRel.class, id);
    }

    @Override
    public void updateFollowRel(FollowRel followRel) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(followRel);
    }

    @Override
    public void removeFollowRelById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getFollowRelById(id));
    }

    @Override
    public List<User> getfollowingById(long id) {
        Session session = sessionFactory.getCurrentSession();

        return session.createSQLQuery("SELECT r.FOLLOWING_ID FROM relation r JOIN user u ON (u.id = :id) AND (u.id = r.FOLLOWER_ID)")
                .addEntity(User.class)
                .setParameter("id", id)
                .list();
    }

    @Override
    public List<User> getfollowerById(long id) {
        Session session = sessionFactory.getCurrentSession();

        return  session.createSQLQuery("SELECT r.FOLLOWER_ID FROM relation r JOIN user u ON (u.id = :id) AND (u.id = r.FOLLOWING_ID)")
                .addEntity(User.class)
                .setParameter("id", id)
                .list();
    }
}
