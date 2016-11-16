package boun.cmpe451.group9.Models.Meta;

import boun.cmpe451.group9.Models.DB.Tag;
import boun.cmpe451.group9.Models.DB.Topic;

import java.util.List;

/**
 * Created by mert on 16.11.2016.
 */
public class TopicTagResponse {
    private Topic topic;
    private List<Tag> tags;
    private String label;

    TopicTagResponse(){

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
