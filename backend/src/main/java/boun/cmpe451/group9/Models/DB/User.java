package boun.cmpe451.group9.Models.DB;

import boun.cmpe451.group9.Models.Base;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * The object representation of the table "USER"
 */
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

    @NotBlank
    @Column(name = "AGE")
    @Min(value = 15)
    @Max(value = 100)
    private int age;

    @NotBlank
    @Column(name = "PASSWORD")
    @Length(min = 6, max = 127)
    private String password;

    @NotBlank
    @Email
    @Column(name = "EMAIL")
    private String email;

    @OneToOne
    private Image profilePictureUrl;

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

    public Image getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(Image profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
