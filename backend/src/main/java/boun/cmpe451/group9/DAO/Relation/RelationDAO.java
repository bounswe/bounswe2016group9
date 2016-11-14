package boun.cmpe451.group9.DAO.Relation;

import boun.cmpe451.group9.Models.DB.Relation;

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
     * Updates the row in the table "RELATION"
     * @param relation the obj representation of the updated row
     */
    void updateRelation(Relation relation);

    /**
     * Deletes the row with the given id from the table "RELATION"
     * @param id the id of the removed row
     */
    void removeRelationById(long id);

    boolean checkIfRelationExistsById(long id);

    boolean checkIfRelationExistsByTopicIds(long from, long to);
}
