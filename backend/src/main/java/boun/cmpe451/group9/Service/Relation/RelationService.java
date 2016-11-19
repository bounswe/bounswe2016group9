package boun.cmpe451.group9.Service.Relation;


import boun.cmpe451.group9.Models.DB.Relation;
import boun.cmpe451.group9.Service.BaseService;

import java.util.List;

/**
 * Service methods for the resource "Relation"
 */
public interface RelationService extends BaseService<Relation>{

    List<Relation> getRelationFromTopicByTopicId(long id);

    List<Relation> getAllRelationsToTopicByTopicId(long id);

    boolean checkIfRelationExistsByTopicIds(long from, long to);
}
