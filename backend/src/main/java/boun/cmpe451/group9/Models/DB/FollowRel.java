package boun.cmpe451.group9.Models.DB;

import boun.cmpe451.group9.Models.Base;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The object representation of the table "FOLLOW_REL"
 */
@Entity
@Table(name = "FOLLOW_REL")
public class FollowRel extends Base {

    @ManyToOne(cascade = CascadeType.ALL)
    private User follower;

    @ManyToOne(cascade = CascadeType.ALL)
    private User following;

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }
}
