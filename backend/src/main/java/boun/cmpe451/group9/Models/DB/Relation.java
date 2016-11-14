package boun.cmpe451.group9.Models.DB;

import boun.cmpe451.group9.Models.Base;

import javax.persistence.*;

/**
 * The object representation of the table "RELATION"
 */
@Entity
@Table(name = "RELATION")
public class Relation extends Base {

    @ManyToOne(cascade = CascadeType.ALL)
    private Topic fromTopic;

    @ManyToOne(cascade = CascadeType.ALL)
    private Topic toTopic;

    @Column(name = "VOTE_COUNT")
    private int voteCount;

    @ManyToOne(cascade = CascadeType.ALL)
    private User createdUser;

    public Topic getFromTopic() {
        return fromTopic;
    }

    public void setFromTopic(Topic fromTopic) {
        this.fromTopic = fromTopic;
    }

    public Topic getToTopic() {
        return toTopic;
    }

    public void setToTopic(Topic toTopic) {
        this.toTopic = toTopic;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }
}
