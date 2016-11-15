package boun.cmpe451.group9.DAO.STagTopic;

import boun.cmpe451.group9.Models.DB.STagTopic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class STagTopicDAOImpl implements STagTopicDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addSTagTopic(STagTopic sTagTopic) {
        Session session = sessionFactory.getCurrentSession();

        session.persist(sTagTopic);
    }

    @Override
    public STagTopic getSTagTopicById(long id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(STagTopic.class, id);
    }

    @Override
    public void updateSTagTopic(STagTopic sTagTopic) {
        Session session = sessionFactory.getCurrentSession();

        session.merge(sTagTopic);
    }

    @Override
    public void removeSTagTopicById(long id) {
        Session session = sessionFactory.getCurrentSession();

        session.delete(getSTagTopicById(id));
    }
}
