package boun.cmpe451.group9.Service.RelationType;

import boun.cmpe451.group9.Models.DB.RelationType;
import boun.cmpe451.group9.Service.BaseService;

import java.util.List;

/**
 * Service methods for resource RelationType
 */
public interface RelationTypeService extends BaseService<RelationType>{

    /**
     * Retrieves all relationTypes that includes string "keywords"
     * @param keywords string that wanted to find
     * @return list of relationType object
     */
    List<RelationType> autoComp(String keywords);
}
