package boun.cmpe451.group9.DAO.Tag;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.Tag;

import java.util.List;

/**
 * Includes all methods that are related to "TAG" entity
 */
public interface TagDAO extends BaseDAO<Tag> {
    /**
     * Gets the tag with the given name
     * @param name name of the tag
     * @return the desired tag
     */
    Tag getTagByName(String name);

    /**
     * Checks if a tag exists with the given name
     * @param name name of the tag
     * @return TRUE if exists, FALSE otherwise
     */
    boolean checkIfTagExistsByName(String name);

    /**
     * Retrieves all tags related to topic with given id
     * @param id id of the topic
     * @return all tags related to topic id
     */
    List<Tag> getTagsByTopicId(long id);

    /**
     * Retrieves all tags related to post with the given id
     * @param id id of the post
     * @return all tags related to post id
     */
    List<Tag> getTagsByPostId(long id);
}
