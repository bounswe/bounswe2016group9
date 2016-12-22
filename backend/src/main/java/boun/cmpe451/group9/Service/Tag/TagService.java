package boun.cmpe451.group9.Service.Tag;


import boun.cmpe451.group9.Models.DB.Tag;
import boun.cmpe451.group9.Service.BaseService;

import java.util.List;

public interface TagService extends BaseService<Tag> {

    /**
     * Retrieves the resource "Tag" with the given name
     * @param name the name of the tag
     * @return the retrieved resource
     */
    Tag getTagByName(String name);

    /**
     * Checks if the tag with the given "name" exists
     * @param name the name of the tag
     * @return TRUE if the tag exists, FALSE if not
     */
    boolean checkIfTagExistsByName(String name);

    /**
     * Retrieves all tags that belongs to topic "id"
     * @param id the id of the topic
     * @return list of tags that belongs to topic "id"
     */
    List<Tag> getTagsByTopicId(long id);

    /**
     * Retrieves all tags that belongs to post "id"
     * @param id the id of the post
     * @return list of tags that belongs to post "id"
     */
    List<Tag> getTagsByPostId(long id);
}
