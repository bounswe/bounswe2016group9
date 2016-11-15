package boun.cmpe451.group9.DAO.Tag;

import boun.cmpe451.group9.Models.DB.Tag;

/**
 * Created by mert on 15.11.2016.
 */
public interface TagDAO {

    void addTag(Tag tag);

    Tag getTagById(long id);

    void updateTag(Tag tag);

    void removeTagById(long id);
}
