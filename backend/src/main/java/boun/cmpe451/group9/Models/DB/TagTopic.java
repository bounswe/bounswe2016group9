package boun.cmpe451.group9.Models.DB;

import boun.cmpe451.group9.Models.Base;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@SuppressWarnings("unused")
@Entity
@Table(name = "TAG_TOPIC")
public class TagTopic extends Base {

    @ManyToOne(cascade = CascadeType.ALL)
    private Tag tag;

    @ManyToOne(cascade = CascadeType.ALL)
    private Topic topic;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
