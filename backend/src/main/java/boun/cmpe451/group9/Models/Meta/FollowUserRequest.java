package boun.cmpe451.group9.Models.Meta;

/**
 * Created by narya on 12/21/16.
 */
@SuppressWarnings({"unused", "DefaultFileTemplate"})
public class FollowUserRequest {
    private Integer followerId;
    private Integer followingId;

    FollowUserRequest(){

    }


    public Integer getFollowerId() {
        return followerId;
    }

    public Integer getFollowingId() {
        return followingId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }

    public void setFollowingId(Integer followingId) {
        this.followingId = followingId;
    }

}

