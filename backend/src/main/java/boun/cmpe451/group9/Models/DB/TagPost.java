package boun.cmpe451.group9.Models.DB;
import boun.cmpe451.group9.Models.Base;

import javax.persistence.*;

/**
 * Created by seha on 22.11.2016.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "TAG_POST")
public class TagPost extends Base {

    @ManyToOne(cascade = CascadeType.ALL)
    private Tag tag;

    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
