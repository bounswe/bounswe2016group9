package boun.cmpe451.group9.Service.STagTopic;

import boun.cmpe451.group9.Models.DB.STagTopic;
import boun.cmpe451.group9.Models.DB.SemanticTag;
import boun.cmpe451.group9.Service.BaseService;

import java.util.List;


public interface STagTopicService extends BaseService<STagTopic> {

    List<SemanticTag> getSTagsByTopicId(long id);
}
