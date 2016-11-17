package boun.cmpe451.group9.DAO.Relation;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.Relation;

import java.util.List;

/**
 * Database access methods for the table "RELATION"
 */
public interface RelationDAO extends BaseDAO<Relation>{

    List<Relation> getAllRelationFromTopicByTopicId(long id);

    List<Relation> getAllRelationsToTopicByTopicId(long id);

    /**
     * Checks if a relation exists from "from" to "to"
     * @param from topic the relations come from
     * @param to topic the relation goes to
     * @return TRUE if exists, FALSE if not
     */
    boolean checkIfRelationExistsByTopicIds(long from, long to);


}
