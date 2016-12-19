package boun.cmpe451.group9.DAO.RelationType;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.RelationType;

import java.util.List;

/**
 * Database access methods for the table "RELATION_TYPE"
 */
public interface RelationTypeDAO extends BaseDAO<RelationType> {

    List<RelationType> autoComp(String keywords);
}
