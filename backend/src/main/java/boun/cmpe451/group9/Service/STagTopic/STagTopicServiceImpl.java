package boun.cmpe451.group9.Service.STagTopic;

import boun.cmpe451.group9.DAO.STagTopic.STagTopicDAO;
import boun.cmpe451.group9.Models.DB.STagTopic;
import boun.cmpe451.group9.Models.DB.SemanticTag;
import boun.cmpe451.group9.Service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class STagTopicServiceImpl extends BaseServiceImpl<STagTopic> implements STagTopicService {

    private STagTopicDAO sTagTopicDAO;

    @Autowired
    public void setsTagTopicDAO(STagTopicDAO sTagTopicDAO){
        this.sTagTopicDAO = sTagTopicDAO;
    }

    @Override
    public List<SemanticTag> getSTagsByTopicId(long id) {
        return sTagTopicDAO.getSTagByTopicId(id);
    }
}
