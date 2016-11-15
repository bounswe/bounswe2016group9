package boun.cmpe451.group9.Service.STagTopic;

import boun.cmpe451.group9.DAO.STagTopic.STagTopicDAO;
import boun.cmpe451.group9.Models.DB.STagTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class STagTopicServiceImpl implements STagTopicService {

    private STagTopicDAO sTagTopicDAO;

    @Autowired
    public void setsTagTopicDAO(STagTopicDAO sTagTopicDAO){
        this.sTagTopicDAO = sTagTopicDAO;
    }

    @Override
    public void addSTagTopic(STagTopic sTagTopic) {
        sTagTopicDAO.addSTagTopic(sTagTopic);
    }

    @Override
    public STagTopic getSTagTopicById(long id) {
        return sTagTopicDAO.getSTagTopicById(id);
    }

    @Override
    public void updateSTagTopic(STagTopic sTagTopic) {
        sTagTopicDAO.updateSTagTopic(sTagTopic);
    }

    @Override
    public void removeSTagTopicById(long id) {
        sTagTopicDAO.removeSTagTopicById(id);
    }
}
