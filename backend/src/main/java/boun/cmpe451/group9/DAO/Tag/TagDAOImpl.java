package boun.cmpe451.group9.DAO.Tag;

import boun.cmpe451.group9.Models.DB.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TagDAOImpl implements TagDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addTag(Tag tag) {
        Session session = sessionFactory.getCurrentSession();

        session.persist(tag);
    }

    @Override
    public Tag getTagById(long id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Tag.class, id);
    }

    @Override
    public Tag getTagByName(String name) {
        Session session = sessionFactory.getCurrentSession();

        return (Tag) session.createSQLQuery("SELECT * FROM tag t WHERE t.name = :name")
                .addEntity(Tag.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    public void updateTag(Tag tag) {
        Session session = sessionFactory.getCurrentSession();

        session.merge(tag);
    }

    @Override
    public void removeTagById(long id) {
        Session session = sessionFactory.getCurrentSession();

        session.delete(getTagById(id));
    }

    @Override
    public boolean checkIfTagExistsByName(String name) {
        return getTagByName(name) != null;
    }
}
