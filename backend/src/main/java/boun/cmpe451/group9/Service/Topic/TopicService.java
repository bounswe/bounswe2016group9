package boun.cmpe451.group9.Service.Topic;

import boun.cmpe451.group9.Models.DB.Topic;

import java.util.List;

/**
 * Service methods for the resource "Topic"
 */
public interface TopicService {

    /**
     * Adds a new resource
     * @param topic the added resource
     */
    void addTopic(Topic topic);

    /**
     * Retrieves the resource "Topic" with the given id
     * @param id the id of the resource
     * @return the retrieved resource
     */
    Topic getTopicById(long id);

    /**
     * Updates a resource "Topic"
     * @param topic the updated resource
     */
    void updateTopic(Topic topic);

    /**
     * Removes the resource "Topic" with the given id
     * @param id the id of the resource
     */
    void removeTopic(long id);

    /**
     * Retrieves all the topics created by the user "id"
     * @param id the id of the user
     * @return list of topics that the user "id" created
     */
    List<Topic> getTopicsByUserId(long id);

    /**
     * Checks if the topic exists
     * @param id the id of the topic
     * @return TRUE if the topic exists, FALSE if not
     */
    boolean checkTopicExistsById(long id);

    List<Topic> getAllTopics();
}
