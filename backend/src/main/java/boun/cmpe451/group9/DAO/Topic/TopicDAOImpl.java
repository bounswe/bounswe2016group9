package boun.cmpe451.group9.DAO.Topic;

import boun.cmpe451.group9.DAO.BaseDAOImpl;
import boun.cmpe451.group9.Models.DB.Post;
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
        String sqlText = keywords[0];

        for(int i=1;i<keywords.length;i++){
            sqlText += "|" + keywords[i];
        }

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

    @Override
    public List<Topic> getGrappi() {
        return (List<Topic>)this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT * FROM topic ORDER BY trending_count DESC LIMIT 10")
                .addEntity(Topic.class)
                .list();
    }

    @Override
    public List<Topic> getMostRelatedTopics(long id) {
        List<Topic> from= this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT r.from_topic_id FROM relation r where r.to_topic_id =:id ORDER BY vote_count DESC LIMIT 3")
                .addEntity(Topic.class)
                .setParameter("id", id)
                .list();
        List <Topic> to = this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT r.to_topic_id FROM relation r where r.from_topic_id =:id ORDER BY vote_count DESC LIMIT 3")
                .addEntity(Topic.class)
                .setParameter("id", id)
                .list();
        from.addAll(to);
        return from;
    }

    @Override
    public List<Topic> getTopicsByUserIdForTimeline(List<Long> userIdList) {
        return this.getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT * FROM topic where user_id in :user_list ORDER by creation_time DESC LIMIT 3")
                .addEntity(Topic.class)
                .setParameterList("user_list",userIdList)
                .list();
    }
}
