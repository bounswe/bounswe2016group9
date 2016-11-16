package boun.cmpe451.group9.DAO.SemanticTag;

import boun.cmpe451.group9.Models.DB.SemanticTag;

/**
 * Created by mert on 15.11.2016.
 */
public interface SemanticTagDAO {

    /**
     * Adds a new row on the table "SEMANTIC_TAG"
     * @param semanticTag the row we just added
     */
    void addSTag(SemanticTag semanticTag);

    /**
     * Adds a new row on the table "SEMANTIC_TAG" by using Hibernate's "save"
     * @param semanticTag the row we just added
     */
    void addSTagWithSave(SemanticTag semanticTag);

    /**
     * Retrieves the row with the given "id" from the table "SEMANTIC_TAG"
     * @param id id of the row
     * @return the row with "id"
     */
    SemanticTag getSTagById(long id);

    /**
     * Retrieves the row with the given "name" from the table "SEMANTIC_TAG"
     * @param name "name" column in the row
     * @return row with the given name
     */
    SemanticTag getSTagByName(String name);

    /**
     * Updates the row with the given row in the table "SEMANTIC_TAG"
     * @param tag updated row
     */
    void updateSTag(SemanticTag tag);

    /**
     * Removes the row with the given id
     * @param id id of the row
     */
    void removeSTagById(long id);

    /**
     * Checks if the row exists with the given name in the table "SEMANTIC_TAG"
     * @param name name of the row
     * @return row with the given name
     */
    boolean checkIfSTagExistsByName(String name);
}
