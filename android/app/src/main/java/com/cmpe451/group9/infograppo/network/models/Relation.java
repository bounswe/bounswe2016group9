package com.cmpe451.group9.infograppo.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by menaf on 16/11/16.
 */

public class Relation extends BaseModel {

    @SerializedName("fromTopic")
    private Topic fromTopic;

    @SerializedName("toTopic")
    private Topic toTopic;

    @SerializedName("voteCount")
    private int voteCount;

    @SerializedName("createdUser")
    private User createdUser;

    @SerializedName("relationType")
    private RelationType relationType;

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

    public RelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }
}
