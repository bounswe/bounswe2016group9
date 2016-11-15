package boun.cmpe451.group9.Service.STagTopic;

import boun.cmpe451.group9.Models.DB.STagTopic;


public interface STagTopicService {

    void addSTagTopic(STagTopic sTagTopic);

    void addSTagTopicWithSave(STagTopic sTagTopic);

    STagTopic getSTagTopicById(long id);

    void updateSTagTopic(STagTopic sTagTopic);

    void removeSTagTopicById(long id);
}
