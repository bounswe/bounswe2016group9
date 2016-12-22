package boun.cmpe451.group9.DAO.Relation;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.Relation;

import java.util.List;

/**
 * Database access methods for the table "RELATION"
 */
public interface RelationDAO extends BaseDAO<Relation>{
    /**
     * Retrieves outgoing relations of a topic with given id
     * Retrieves the list of row with the given topic id from the table "Topic"
     * @param id the id of topic
     * @return List of topics
     */
    List<Relation> getAllRelationFromTopicByTopicId(long id);
    /**
     * Retrieves incoming relations of a topic with given id
     * Retrieves the list of row with the given topic id from the table "Topic"
     * @param id the id of topic
     * @return List of topics
     */
    List<Relation> getAllRelationsToTopicByTopicId(long id);

    /**
     * Checks if a relation exists from "from" to "to"
     * @param from topic the relations come from
     * @param to topic the relation goes to
     * @return TRUE if exists, FALSE if not
     */
    boolean checkIfRelationExistsByTopicIds(long from, long to);


}
