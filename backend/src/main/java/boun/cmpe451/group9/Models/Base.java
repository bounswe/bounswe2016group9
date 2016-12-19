package boun.cmpe451.group9.Models;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * The base class for all classes that represents a table.
 * Includes variables that are common for all tables
 */
@SuppressWarnings("unused")
@MappedSuperclass
public class Base extends ResourceSupport {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "CREATION_TIME")
    private LocalDateTime creationTime;

    public long getEntityId() {
        return id;
    }

    public void setEntityId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @PrePersist
    @PreUpdate
    public void createCreationTime(){
        creationTime = LocalDateTime.now();
    }
}
