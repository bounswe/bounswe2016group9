package boun.cmpe451.group9.DAO.Topic;

import boun.cmpe451.group9.DAO.BaseDAOImpl;
import boun.cmpe451.group9.Models.DB.Topic;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class TopicDAOImpl extends BaseDAOImpl<Topic> implements TopicDAO {
    @Override
    public Topic getTopicByName(String name) {
        return (Topic) this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT * FROM topic WHERE name = :name")
                .addEntity(Topic.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    public List<Topic> getTopicsByUserId(long id) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT t.* FROM user u JOIN topic t ON u.id = t.user_id WHERE u.id = :id")
                .addEntity(Topic.class)
                .setParameter("id", id)
                .list();
    }

    @Override
    public List<Topic> searchTopicByName(String[] keywords) {
        String sqlText = "'" + keywords[0];

        for(int i=1;i<keywords.length;i++){
            sqlText += "|" + keywords[i];
        }
        sqlText += "'";

        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT t.* FROM topic t WHERE t.name REGEXP :regex")
                .addEntity(Topic.class)
                .setParameter("regex", sqlText)
                .list();
    }

    @Override
    public List<Topic> autoComp(String keyword) {
        String regex = "^"+keyword;
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT t.* FROM topic t WHERE t.name REGEXP :regex")
                .addEntity(Topic.class)
                .setParameter("regex", regex)
                .list();
    }
}
