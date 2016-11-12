package boun.cmpe451.group9.Models.DB;

import boun.cmpe451.group9.Models.Base;
import com.sun.org.apache.xpath.internal.operations.String;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created by seha on 12.11.2016.
 */
@Entity
@Table(name = "POST")
public class Post extends Base {

    @NotNull
    @Column(name="CONTENT")
    private String content;

    @NotNull
    @Column(name="CREATOR_ID")
    private long creatorID;

    @NotNull
    @Column(name="TOPIC_ID")
    private long topicID;

    @Column(name = "VOTE_COUNT")
    private int voteCount;

    @Column(name="LOCATION_ID")
    private int locationID;

    @ManyToOne (cascade = CascadeType.ALL)
    private User createdUser;

    @ManyToOne (cascade = CascadeType.ALL)
    private Topic topic;


}
