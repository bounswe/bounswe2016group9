package boun.cmpe451.group9.DAO.TagTopic;

import boun.cmpe451.group9.Models.DB.TagTopic;

/**
 * Created by mert on 15.11.2016.
 */
public interface TagTopicDAO {

    void addTagTopic(TagTopic tagTopic);

    TagTopic getTagTopicById(long id);

    void updateTagTopic(TagTopic tagTopic);

    void removeTagTopicById(long id);
}
