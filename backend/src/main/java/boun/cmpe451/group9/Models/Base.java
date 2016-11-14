package boun.cmpe451.group9.Models;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.Date;

/**
 * The base class for all classes that represents a table.
 * Includes variables that are common for all tables
 */
@MappedSuperclass
public class Base extends ResourceSupport {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "CREATION_TIME")
    private Date creationTime;

    public long getEntityId() {
        return id;
    }

    public void setEntityId(long id) {
        this.id = id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
