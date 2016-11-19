package boun.cmpe451.group9.Service.SemanticTag;


import boun.cmpe451.group9.Models.DB.SemanticTag;
import boun.cmpe451.group9.Service.BaseService;

public interface SemanticTagService extends BaseService<SemanticTag> {

    SemanticTag getSTagByName(String name);

    boolean checkExistenceByName(String name);
}
