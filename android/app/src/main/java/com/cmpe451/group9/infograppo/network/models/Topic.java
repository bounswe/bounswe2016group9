package com.cmpe451.group9.infograppo.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by menaf on 16/11/16.
 */

public class Topic {

    @SerializedName("name")
    private String name;

    @SerializedName("user")
    private User user;

    @SerializedName("trending_count")
    private int trendingCount;

    @SerializedName("post_count")
    private int postCount;

    @SerializedName("entity_id")
    private int entityId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTrendingCount() {
        return trendingCount;
    }

    public void setTrendingCount(int trendingCount) {
        this.trendingCount = trendingCount;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

}
