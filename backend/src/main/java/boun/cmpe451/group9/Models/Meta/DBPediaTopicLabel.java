package boun.cmpe451.group9.Models.Meta;

import boun.cmpe451.group9.Models.DB.Topic;

public class DBPediaTopicLabel {
    private Topic topic;
    private String label;

    public DBPediaTopicLabel(){

    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
