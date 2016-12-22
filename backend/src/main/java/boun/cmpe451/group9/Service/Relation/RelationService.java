package boun.cmpe451.group9.Service.Relation;


import boun.cmpe451.group9.Models.DB.Relation;
import boun.cmpe451.group9.Service.BaseService;

import java.util.List;

/**
 * Service methods for the resource "Relation"
 */
public interface RelationService extends BaseService<Relation>{

    /**
     *  Retrieves the list of all relations that from topic "id"
     * @param id id of topic
     * @return the list of relations that rooted from topic "id"
     */
    List<Relation> getRelationFromTopicByTopicId(long id);

    /**
     *  Retrieves the list of all relations that to topic "id"
     * @param id id of topic
     * @return the list of relations that ended at topic "id"
     */
    List<Relation> getAllRelationsToTopicByTopicId(long id);

    /**
     *  Checks whether there is a Relation between topic "from" and topic "to"
     * @param from id of first topic
     * @param to id of second topic
     * @return true if there is such relation, false if not
     */
    boolean checkIfRelationExistsByTopicIds(long from, long to);
}
