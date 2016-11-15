package boun.cmpe451.group9.DAO.SemanticTag;

import boun.cmpe451.group9.Models.DB.SemanticTag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SemanticTagDAOImpl implements SemanticTagDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addSTag(SemanticTag semanticTag) {
        Session session = sessionFactory.getCurrentSession();

        session.persist(semanticTag);
    }

    @Override
    public void addSTagWithSave(SemanticTag semanticTag) {
        Session session = sessionFactory.getCurrentSession();

        session.save(semanticTag);
    }

    @Override
    public SemanticTag getSTagById(long id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(SemanticTag.class, id);
    }

    @Override
    public SemanticTag getSTagByName(String name) {
        Session session = sessionFactory.getCurrentSession();

        return (SemanticTag) session.createSQLQuery("SELECT * FROM semantic_tag t WHERE t.type = :name")
                .addEntity(SemanticTag.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    public void updateSTag(SemanticTag tag) {
        Session session = sessionFactory.getCurrentSession();

        session.merge(tag);
    }

    @Override
    public void removeSTagById(long id) {
        Session session = sessionFactory.getCurrentSession();

        session.delete(getSTagById(id));
    }

    @Override
    public boolean checkIfSTagExistsByName(String name) {
        return getSTagByName(name) != null;
    }
}
