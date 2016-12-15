package boun.cmpe451.group9.Models.DB;

import boun.cmpe451.group9.Models.Base;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
    private String password;

    @NotBlank
    @Email
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "ENABLED", columnDefinition = "boolean default true")
    private boolean enabled = true;

    @URL
    @Column(name = "IMAGE_URL")
    private String image_url;

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

    private void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
