package boun.cmpe451.group9.DAO.Relation;

import boun.cmpe451.group9.Models.DB.Relation;

import java.util.List;

/**
 * Database access methods for the table "RELATION"
 */
public interface RelationDAO {

    /**
     * Adds a new row in the table "RELATION"
     * @param relation the obj representation of the row
     */
    void addRelation(Relation relation);

    /**
     * Retrieves the row with the given id from the table "RELATION"
     * @param id the id of the row
     * @return the obj representation of the row
     */
    Relation getRelationById(long id);

    /**
     * Retrieves the content of a relation with the given id from thee table "RELATION
     * @param id the id of the row
     * @return content string of the row
     */
    String getRelationContent(long id);

    List<Relation> getallRelationFromTopicByTopicId(long id);

    /**
     * Updates the row in the table "RELATION"
     * @param relation the obj representation of the updated row
     */
    void updateRelation(Relation relation);

    /**
     * Deletes the row with the given id from the table "RELATION"
     * @param id the id of the removed row
     */
    void removeRelationById(long id);

    /**
     * Checks if the row with the given "id" exists in the table "RELATION"
     * @param id id of the relation
     * @return TRUE if exists, FALSE if not
     */
    boolean checkIfRelationExistsById(long id);

    /**
     * Checks if a relation exists from "from" to "to"
     * @param from topic the relations come from
     * @param to topic the relation goes to
     * @return TRUE if exists, FALSE if not
     */
    boolean checkIfRelationExistsByTopicIds(long from, long to);


}
