package boun.cmpe451.group9.Models.DB;

import boun.cmpe451.group9.Models.Base;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The object representation of the table "IMAGE"
 */
@Entity
@Table(name = "IMAGE")
public class Image extends Base {

    @URL
    @NotBlank
    @Column(name = "URL")
    @Length(max = 100)
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
