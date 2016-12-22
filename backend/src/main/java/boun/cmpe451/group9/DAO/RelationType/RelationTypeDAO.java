package boun.cmpe451.group9.DAO.RelationType;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.RelationType;

import java.util.List;

/**
 * Database access methods for the table "RELATION_TYPE"
 */
public interface RelationTypeDAO extends BaseDAO<RelationType> {
    /**
     * Retrieves the list of row with the given keywords using regex
     * @param keywords the keyword of the query
     * @return List of Relation Types
     */
    List<RelationType> autoComp(String keywords);

}
