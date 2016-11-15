package boun.cmpe451.group9.Service.TagTopic;

import boun.cmpe451.group9.DAO.TagTopic.TagTopicDAO;
import boun.cmpe451.group9.Models.DB.TagTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagTopicServiceImpl implements TagTopicService {

    private TagTopicDAO tagTopicDAO;

    @Autowired
    public void setTagTopicDAO(TagTopicDAO tagTopicDAO){
        this.tagTopicDAO = tagTopicDAO;
    }

    @Override
    public void addTagTopic(TagTopic tagTopic) {
        tagTopicDAO.addTagTopic(tagTopic);
    }

    @Override
    public void addTagTopicWithSave(TagTopic tagTopic) {
        tagTopicDAO.addTagTopicWithSave(tagTopic);
    }

    @Override
    public TagTopic getTagTopicById(long id) {
        return tagTopicDAO.getTagTopicById(id);
    }

    @Override
    public void updateTagTopic(TagTopic tagTopic) {
        tagTopicDAO.updateTagTopic(tagTopic);
    }

    @Override
    public void removeTagTopicById(long id) {
        tagTopicDAO.removeTagTopicById(id);
    }
}
