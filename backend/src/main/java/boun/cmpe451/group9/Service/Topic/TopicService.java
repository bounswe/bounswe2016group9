package boun.cmpe451.group9.Service.Topic;

import boun.cmpe451.group9.Models.DB.Topic;
import boun.cmpe451.group9.Service.BaseService;

import java.util.List;

/**
 * Service methods for the resource "Topic"
 */
public interface TopicService extends BaseService<Topic> {

    /**
     * Retrieves all the topics created by the user "id"
     * @param id the id of the user
     * @return list of topics that the user "id" created
     */
    List<Topic> getTopicsByUserId(long id);

    /**
     * Retrieves all the topics has string "keyword" as their names
     * @param keyword keywords that wanted to find
     * @return list of topics that predicted by autoComplete
     */
    List<Topic> autoComp(String keyword);

    /**
     * Retrieves most related topics of topic "id" according to trending_count
     * @param id the id of topics
     * @return list of topics that is most relevant
     */
    List<Topic> getMostRelatedTopics(long id);

    /**
     * Retrieves most popular topics
     * @return list of most popular topics
     */
    List<Topic> getGrappi();

    /**
     * Retrieves list of most recent topics belong to list of user "userIdList"
     * @param userIdList the list of ids
     * @return list of most recent topics belong to users "userIdList"
     */
    List<Topic> getTopicsByUserIdForTimeline(List<Long> userIdList);
}
