package boun.cmpe451.group9.Service.SemanticTag;


import boun.cmpe451.group9.Models.DB.SemanticTag;

public interface SemanticTagService {

    void addSTag(SemanticTag semanticTag);

    SemanticTag getSTagById(long id);

    SemanticTag getSTagByName(String name);

    void updateSTag(SemanticTag semanticTag);

    void removeSTagById(long id);

    boolean checkIfSTagExistsByName(String name);
}
