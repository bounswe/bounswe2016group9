package boun.cmpe451.group9.Models.DB;

import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User extends ResourceSupport {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long userID;

    @Column(name = "USERNAME")
    @Length(min = 3, max = 20)
    private String username;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
