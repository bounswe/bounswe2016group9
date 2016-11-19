package boun.cmpe451.group9.DAO.Tag;

import boun.cmpe451.group9.DAO.BaseDAOImpl;
import boun.cmpe451.group9.Models.DB.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class TagDAOImpl extends BaseDAOImpl<Tag> implements TagDAO {
    @Override
    public Tag getTagByName(String name) {
        return (Tag) this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT * FROM tag t WHERE t.name = :name")
                .addEntity(Tag.class)
                .setParameter("name", name)
                .uniqueResult();
    }


    @Override
    public boolean checkIfTagExistsByName(String name) {
        return getTagByName(name) != null;
    }

    @Override
    public List<Tag> getTagsByTopicId(long id) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT t.* FROM tag t JOIN tag_topic top ON (t.id = top.tag_id) WHERE (top.topic_id = :id)")
                .addEntity(Tag.class)
                .setParameter("id", id)
                .list();
    }
}
