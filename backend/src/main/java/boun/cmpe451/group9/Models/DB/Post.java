package boun.cmpe451.group9.Models.DB;

import boun.cmpe451.group9.Models.Base;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@SuppressWarnings("unused")
@Entity
@Table(name = "POST")
public class Post extends Base {

    @NotBlank
    @Column(name = "CONTENT", columnDefinition = "TEXT")
    private String content;

    @NotNull
    @Column(name = "VOTE_COUNT", columnDefinition = "int default 0")
    private int voteCount;

    @Column(name="LOCATION_ID")
    private int locationID;

    @ManyToOne(cascade = CascadeType.ALL)
    private User createdUser;

    @ManyToOne(cascade = CascadeType.ALL)
    private Topic topic;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
