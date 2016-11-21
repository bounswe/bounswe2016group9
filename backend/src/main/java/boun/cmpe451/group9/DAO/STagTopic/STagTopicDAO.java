package boun.cmpe451.group9.DAO.STagTopic;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.STagTopic;
import boun.cmpe451.group9.Models.DB.SemanticTag;

import java.util.List;


public interface STagTopicDAO extends BaseDAO<STagTopic> {

    List<SemanticTag> getSTagByTopicId(long id);
}