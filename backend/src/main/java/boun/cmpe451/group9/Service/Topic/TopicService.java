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

    List<Topic> searchTopicByName(String keywords);
}
