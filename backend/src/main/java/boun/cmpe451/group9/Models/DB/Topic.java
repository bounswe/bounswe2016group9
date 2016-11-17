package boun.cmpe451.group9.Models.DB;

import boun.cmpe451.group9.Models.Base;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * The object representation of the table "TOPIC"
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "TOPIC")
public class Topic extends Base {

    @NotBlank
    @Column(name = "NAME")
    @Length(min = 2, max = 50)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @Column(name = "TRENDING_COUNT")
    private int trendingCount;

    @Column(name = "POST_COUNT")
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
