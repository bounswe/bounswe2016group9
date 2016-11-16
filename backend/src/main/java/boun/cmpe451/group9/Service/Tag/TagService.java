package boun.cmpe451.group9.Service.Tag;


import boun.cmpe451.group9.Models.DB.Tag;

import java.util.List;

public interface TagService {

    void addTag(Tag tag);

    Tag getTagById(long id);

    Tag getTagByName(String name);

    void updateTag(Tag tag);

    void removeTag(long id);

    boolean checkIfTagExistsByName(String name);

    boolean checkIfTagExistsById(long id);

    List<Tag> getTagsByTopicId(long id);
}
