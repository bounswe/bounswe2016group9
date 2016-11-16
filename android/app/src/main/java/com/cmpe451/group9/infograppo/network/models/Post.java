package com.cmpe451.group9.infograppo.network.models;

/**
 * Created by menaf on 16/11/16.
 */

public class Post {

    private String content;
    private int voteCount;
    private int locationID;
    private User createdUser;
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
