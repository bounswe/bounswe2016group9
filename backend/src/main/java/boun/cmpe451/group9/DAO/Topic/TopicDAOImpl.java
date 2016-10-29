package boun.cmpe451.group9.DAO.Topic;

import boun.cmpe451.group9.Models.DB.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicDAOImpl implements TopicDAO {

    private SessionFactory sessionFactory;

    @Autowired
    private void setSessionFactory(SessionFactory sessionFactory){ this.sessionFactory = sessionFactory; }

    @Override
    public void addTopic(Topic t) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(t);
    }

    @Override
    public Topic getTopicById(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Topic.class, id);
    }

    @Override
    public void updateTopic(Topic t) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(t);
    }

    @Override
    public void removeTopicById(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(getTopicById(id));
    }

    @Override
    public List<Topic> getTopicsByUserId(long id) {
        Session session = this.sessionFactory.getCurrentSession();

        return (List<Topic>) session.createSQLQuery("SELECT topic.* FROM user JOIN topic ON user.ID = topic.user_id WHERE user.id = :id")
                .addEntity(Topic.class)
                .setParameter("id", id)
                .list();
    }

    @Override
    public boolean checkTopicExistsById(long id) {
        return getTopicById(id) != null;
    }
}
