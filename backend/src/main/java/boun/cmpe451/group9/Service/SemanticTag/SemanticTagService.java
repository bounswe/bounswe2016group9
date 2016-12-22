package boun.cmpe451.group9.Service.SemanticTag;


import boun.cmpe451.group9.Models.DB.SemanticTag;
import boun.cmpe451.group9.Service.BaseService;

public interface SemanticTagService extends BaseService<SemanticTag> {

    /**
     * Retrieves semanticTag object by string "name"
     * @param name name of SemanticTag
     * @return semanticTag whose "name"
     */
    SemanticTag getSTagByName(String name);

    /**
     * Checks whether there is a semanticTag object with "name" or not
     * @param name name of semanticTag that want to be checked
     * @return true if exists, false if not
     */
    boolean checkExistenceByName(String name);
}
