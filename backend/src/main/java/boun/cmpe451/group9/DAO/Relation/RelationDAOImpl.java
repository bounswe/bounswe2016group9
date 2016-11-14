package boun.cmpe451.group9.DAO.Relation;

import boun.cmpe451.group9.Models.DB.Relation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RelationDAOImpl implements RelationDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addRelation(Relation relation) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(relation);
    }

    @Override
    public Relation getRelationById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Relation.class, id);
    }

    @Override
    public void updateRelation(Relation relation) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(relation);
    }

    @Override
    public void removeRelationById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getRelationById(id));
    }

    @Override
    public boolean checkIfRelationExistsById(long id) {
        return getRelationById(id) != null;
    }

    @Override
    public boolean checkIfRelationExistsByTopicIds(long from, long to) {
        Session session = sessionFactory.getCurrentSession();

        int size = session.createSQLQuery("SELECT rel FROM relation WHERE (rel.fromTopic.id = :fromId) AND (rel.toTopic.id = :toId)")
                .addEntity(Relation.class)
                .setParameter("fromId", from)
                .setParameter("toId", to).list().size();

        return size > 0;
    }
}
