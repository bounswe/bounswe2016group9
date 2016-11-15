package boun.cmpe451.group9.Models.DB;

import boun.cmpe451.group9.Models.Base;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by mert on 15.11.2016.
 */

@Entity
@Table(name = "SEMANTICTAG_TOPIC")
public class STagTopic extends Base {

    @ManyToOne(cascade = CascadeType.ALL)
    private SemanticTag semanticTag;

    @ManyToOne(cascade = CascadeType.ALL)
    private Topic topic;

    public SemanticTag getSemanticTag() {
        return semanticTag;
    }

    public void setSemanticTag(SemanticTag semanticTag) {
        this.semanticTag = semanticTag;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
