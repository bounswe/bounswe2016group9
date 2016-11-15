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
        Session session = sessionFactory.getCurrentSession();
        session.persist(t);
    }

    @Override
    public Topic getTopicById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Topic.class, id);
    }

    @Override
    public Topic getTopicByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return (Topic) session.createSQLQuery("SELECT * FROM topic WHERE name = :name")
                .addEntity(Topic.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    public void updateTopic(Topic t) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(t);
    }

    @Override
    public void removeTopicById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getTopicById(id));
    }

    @Override
    public List<Topic> getTopicsByUserId(long id) {
        Session session = sessionFactory.getCurrentSession();

        return (List<Topic>) session.createSQLQuery("SELECT t FROM user u JOIN topic t ON u.id = t.user_id WHERE u.id = :id")
                .addEntity(Topic.class)
                .setParameter("id", id)
                .list();
    }

    @Override
    public boolean checkTopicExistsById(long id) {
        return getTopicById(id) != null;
    }

    @Override
    public boolean checkTopicExistsByName(String name) {
        return getTopicByName(name) != null;
    }

    @Override
    public List<Topic> getAllTopics() {
        Session session = sessionFactory.getCurrentSession();

        return session.createSQLQuery("SELECT * FROM TOPIC").list();
    }
}
