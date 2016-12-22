package com.cmpe451.group9.infograppo.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by menaf on 16/11/16.
 */

public class Topic extends BaseModel{

    @SerializedName("name")
    private String name;

    @SerializedName("user")
    private User user;

    @SerializedName("trendingCount")
    private int trendingCount;

    @SerializedName("postCount")
    private int postCount;

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
}
