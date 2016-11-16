package boun.cmpe451.group9.DAO.Tag;

import boun.cmpe451.group9.Models.DB.Tag;

import java.util.List;

/**
 * Created by mert on 15.11.2016.
 */
public interface TagDAO {

    void addTag(Tag tag);

    Tag getTagById(long id);

    Tag getTagByName(String name);

    void updateTag(Tag tag);

    void removeTagById(long id);

    boolean checkIfTagExistsByName(String name);

    boolean checkIfTagExistsById(long id);

    List<Tag> getTagsByTopicId(long id);
}
