package boun.cmpe451.group9.DAO.SemanticTag;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.SemanticTag;


public interface SemanticTagDAO extends BaseDAO<SemanticTag>{

    /**
     * Retrieves the row with the given "name" from the table "SEMANTIC_TAG"
     * @param name "name" column in the row
     * @return row with the given name
     */
    SemanticTag getSTagByName(String name);


    /**
     * Checks if a semantic tag exists in table
     * @param name name of the topic
     * @return TRUE if exists, FALSE if not
     */
    boolean checkExistenceByName(String name);
}
