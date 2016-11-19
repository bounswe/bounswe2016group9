package boun.cmpe451.group9.Models.Meta;

import boun.cmpe451.group9.Models.DB.Tag;
import boun.cmpe451.group9.Models.DB.Topic;

import java.util.List;


@SuppressWarnings("unused")
public class RequestTypeResource {
    private Topic topic;
    private List<Tag> tags;

    public RequestTypeResource(){

    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
