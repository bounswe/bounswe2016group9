package boun.cmpe451.group9.Service.RelationType;

import boun.cmpe451.group9.Models.DB.RelationType;
import boun.cmpe451.group9.Service.BaseService;

import java.util.List;

public interface RelationTypeService extends BaseService<RelationType>{
    List<RelationType> autoComp(String keywords);
}
