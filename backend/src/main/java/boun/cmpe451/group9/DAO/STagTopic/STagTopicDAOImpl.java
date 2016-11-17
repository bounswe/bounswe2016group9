package boun.cmpe451.group9.DAO.STagTopic;

import boun.cmpe451.group9.DAO.BaseDAOImpl;
import boun.cmpe451.group9.Models.DB.STagTopic;
import boun.cmpe451.group9.Models.DB.SemanticTag;
import org.springframework.stereotype.Repository;

import java.util.List;


@SuppressWarnings("unchecked")
@Repository
public class STagTopicDAOImpl extends BaseDAOImpl<STagTopic> implements STagTopicDAO {
    @Override
    public List<SemanticTag> getSTagByTopicId(long id) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT t.* FROM semantictag_topic stag " +
                        "JOIN semantic_tag t " +
                        "ON stag.semantic_tag_id = t.id " +
                        "WHERE stag.topic_id = :id").addEntity(SemanticTag.class)
                .setParameter("id", id)
                .list();
    }
}
