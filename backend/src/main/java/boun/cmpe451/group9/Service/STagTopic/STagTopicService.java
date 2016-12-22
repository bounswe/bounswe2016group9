package boun.cmpe451.group9.Service.STagTopic;

import boun.cmpe451.group9.Models.DB.STagTopic;
import boun.cmpe451.group9.Models.DB.SemanticTag;
import boun.cmpe451.group9.Service.BaseService;

import java.util.List;

/**
 * Service methods for resource STagTopic
 */
public interface STagTopicService extends BaseService<STagTopic> {

    /**
     * Retrieves all semanticTags of a topic "id"
     * @param id id of the topic
     * @return list of semanticTags of topic "id"
     */
    List<SemanticTag> getSTagsByTopicId(long id);
}
