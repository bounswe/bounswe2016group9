package boun.cmpe451.group9.DAO.Relation;

import boun.cmpe451.group9.DAO.BaseDAOImpl;
import boun.cmpe451.group9.Models.DB.Relation;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class RelationDAOImpl extends BaseDAOImpl<Relation> implements RelationDAO {
    @Override
    public List<Relation> getallRelationFromTopicByTopicId(long id) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT r.* FROM relation r JOIN topic t ON (t.id = r.from_topic_id) WHERE (t.id = :id)")
                .addEntity(Relation.class)
                .setParameter("id", id)
                .list();
    }

    @Override
    public List<Relation> getAllRelationsToTopicByTopicId(long id) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT r.* FROM relation r JOIN topic t ON (t.id = r.to_topic_id) WHERE (t.id = :id)")
                .addEntity(Relation.class)
                .setParameter("id", id)
                .list();
    }

    @Override
    public boolean checkIfRelationExistsByTopicIds(long from, long to) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT rel.* FROM relation rel WHERE (rel.from_topic_id = :fromId) AND (rel.to_topic_id = :toId)")
                .addEntity(Relation.class)
                .setParameter("fromId", from)
                .setParameter("toId", to)
                .uniqueResult() != null;
    }
}
