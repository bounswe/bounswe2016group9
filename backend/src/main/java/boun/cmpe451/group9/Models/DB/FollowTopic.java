package boun.cmpe451.group9.Models.DB;
import boun.cmpe451.group9.Models.Base;

import javax.persistence.*;
/**
 * Created by seha on 10.12.2016.
 */
@SuppressWarnings("unused")
@Entity
@Table(name= "FOLLOW_TOPIC")
public class FollowTopic extends Base{

    @ManyToOne(cascade = CascadeType.ALL)
    private User follower;

    @ManyToOne(cascade = CascadeType.ALL)
    private Topic topic;

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
