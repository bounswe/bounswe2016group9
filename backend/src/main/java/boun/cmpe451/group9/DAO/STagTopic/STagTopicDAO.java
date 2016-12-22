package boun.cmpe451.group9.DAO.STagTopic;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.STagTopic;
import boun.cmpe451.group9.Models.DB.SemanticTag;
import boun.cmpe451.group9.Models.DB.Topic;

import java.util.List;


public interface STagTopicDAO extends BaseDAO<STagTopic> {
    /**
     * Retrieves semantic tags of topics with given id from table "STAGTopic"
     * @param id the id of topic
     * @return list of Semantic Tags
     */
    List<SemanticTag> getSTagByTopicId(long id);

    /**
     * Retrieves result set by checking semantic tags related to query
     * @param keywords search query
     * @return list of topics
     */
    List<Topic> searchTopicBySTag(String[] keywords);
}
