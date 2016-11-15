package boun.cmpe451.group9.DAO.TagTopic;

import boun.cmpe451.group9.Models.DB.TagTopic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class TagTopicDAOImpl implements TagTopicDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addTagTopic(TagTopic tagTopic) {
        Session session = sessionFactory.getCurrentSession();

        session.persist(tagTopic);
    }

    @Override
    public void addTagTopicWithSave(TagTopic tagTopic) {
        Session session = sessionFactory.getCurrentSession();

        session.save(tagTopic);
    }

    @Override
    public TagTopic getTagTopicById(long id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(TagTopic.class, id);
    }

    @Override
    public void updateTagTopic(TagTopic tagTopic) {
        Session session = sessionFactory.getCurrentSession();

        session.merge(tagTopic);
    }

    @Override
    public void removeTagTopicById(long id) {
        Session session = sessionFactory.getCurrentSession();

        session.delete(getTagTopicById(id));
    }
}
