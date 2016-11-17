package boun.cmpe451.group9.DAO.Topic;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.Topic;

import java.util.List;

/**
 * Database access methods for the table "TOPIC"
 */
@SuppressWarnings("unused")
public interface TopicDAO extends BaseDAO<Topic> {

    /**
     * Retrieves the row with the given "name" value in the table "TOPIC"
     * @param name the value "name" in the row
     * @return the row with the given value
     */
    Topic getTopicByName(String name);

    /**
     * Retrieves all rows in the table "TOPIC" with the user_id = id
     * @param id the id of the user
     * @return a list of rows with user_id = id
     */
    List getTopicsByUserId(long id);
}
