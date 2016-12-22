package boun.cmpe451.group9.Models.Meta;

@SuppressWarnings("unused")
public class FollowTopicRequest {
    private Integer topicId;
    private Integer userId;

    FollowTopicRequest(){

    }


    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
