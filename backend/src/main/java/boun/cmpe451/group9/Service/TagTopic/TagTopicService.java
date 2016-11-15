package boun.cmpe451.group9.Service.TagTopic;

import boun.cmpe451.group9.Models.DB.TagTopic;


public interface TagTopicService {

    void addTagTopic(TagTopic tagTopic);

    TagTopic getTagTopicById(long id);

    void updateTagTopic(TagTopic tagTopic);

    void removeTagTopicById(long id);
}
