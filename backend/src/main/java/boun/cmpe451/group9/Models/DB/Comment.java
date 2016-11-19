package boun.cmpe451.group9.Models.DB;
import boun.cmpe451.group9.Models.Base;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
/**
 * Created by seha on 19.11.2016.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "COMMENT")
public class Comment extends Base{

    @NotBlank
    @Column(name = "CONTENT", columnDefinition = "TEXT")
    @Length(min =2, max =140)
    private String content;

    @Column(name = "VOTE_COUNT")
    private int voteCount;

    @ManyToOne(cascade = CascadeType.ALL)
    private User createdUser;

    @ManyToOne(cascade = CascadeType.ALL)
    private Post postOfComment;

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

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public Post getPostOfComment() {
        return postOfComment;
    }

    public void setPostOfComment(Post postOfComment) {
        this.postOfComment = postOfComment;
    }
}
