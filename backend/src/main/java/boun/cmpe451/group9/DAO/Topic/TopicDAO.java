package boun.cmpe451.group9.DAO.Topic;

import boun.cmpe451.group9.Models.DB.Topic;

import java.util.List;

/**
 * Database access methods for the table "TOPIC"
 */
public interface TopicDAO {

    /**
     * Adds a new row in the table "TOPIC"
     * @param topic the obj representation of the added row
     */
    void addTopic(Topic topic);

    /**
     * Retrieves the row with the given id from the table "TOPIC"
     * @param id the id of the row
     * @return the obj representation of the retrieved row
     */
    Topic getTopicById(long id);

    /**
     * Retrieves the row with the given "name" value in the table "TOPIC"
     * @param name the value "name" in the row
     * @return the row with the given value
     */
    Topic getTopicByName(String name);

    /**
     * Updates the row with the given one in the table "TOPIC"
     * @param topic the obj representation of the updated row
     */
    void updateTopic(Topic topic);

    /**
     * Removes the row with the given id from the table "TOPIC"
     * @param id the id of the removed row
     */
    void removeTopicById(long id);

    /**
     * Retrieves all rows in the table "TOPIC" with the user_id = id
     * @param id the id of the user
     * @return a list of rows with user_id = id
     */
    List<Topic> getTopicsByUserId(long id);

    /**
     * Checks if the row with the given id exists in the table "TOPIC"
     * @param id the id of the row
     * @return TRUE if the row exists, FALSE if not
     */
    boolean checkTopicExistsById(long id);

    /**
     * Checks if the row with the given "name" value exists in the table "TOPIC"
     * @param name the value "name" in the row
     * @return TRUE if the row exists, FALSE if not
     */
    boolean checkTopicExistsByName(String name);

    /**
     * Retieves all rows in the table "TOPIC"
     * @return all rows
     */
    List<Topic> getAllTopics();
}
