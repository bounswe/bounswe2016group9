package boun.cmpe451.group9.Models.DB;

import boun.cmpe451.group9.Models.Base;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The object representation of the table "USER"
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "USER")
public class User extends Base {

    @NotBlank
    @Column(name = "USERNAME")
    @Length(min = 3, max = 20)
    private String username;

    @Column(name = "NAME")
    @Length(min = 3, max = 20)
    private String name;

    @Column(name = "SURNAME")
    @Length(min = 3, max = 20)
    private String surname;

    @Column(name = "AGE")
    private int age;

    @NotBlank
    @Column(name = "PASSWORD")
    @Length(min = 60, max = 300)
    private String password;

    @NotBlank
    @Email
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ENABLED")
    private boolean enabled;

    @OneToOne
    private Image profilePictureUrl;

    public User(){

    }

    public User(User user){
        this.setEntityId(user.getEntityId());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEnabled(user.isEnabled());
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Image getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(Image profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
