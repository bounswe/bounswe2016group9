package boun.cmpe451.group9.Models.DB;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;

@Entity
@Table(name = "TOPIC")
public class Topic extends ResourceSupport {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long topicID;

    @Column(name = "CONTEXT")
    private String context;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public long getTopicID() {
        return topicID;
    }

    public void setTopicID(long topicID) {
        this.topicID = topicID;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
