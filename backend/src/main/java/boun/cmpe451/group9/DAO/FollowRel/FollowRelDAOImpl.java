package boun.cmpe451.group9.DAO.FollowRel;

import boun.cmpe451.group9.Models.DB.FollowRel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
