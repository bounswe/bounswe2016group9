package boun.cmpe451.group9.DAO.SemanticTag;

import boun.cmpe451.group9.Models.DB.SemanticTag;

/**
 * Created by mert on 15.11.2016.
 */
public interface SemanticTagDAO {

    void addSTag(SemanticTag semanticTag);

    SemanticTag getSTagById(long id);

    SemanticTag getSTagByName(String name);

    void updateSTag(SemanticTag tag);

    void removeSTagById(long id);

    boolean checkIfSTagExistsByName(String name);
}
