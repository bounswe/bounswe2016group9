package boun.cmpe451.group9.DAO.Tag;

import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.Tag;

import java.util.List;


public interface TagDAO extends BaseDAO<Tag> {
    /**
     *
     * @param name
     * @return
     */
    Tag getTagByName(String name);

    boolean checkIfTagExistsByName(String name);

    List<Tag> getTagsByTopicId(long id);

    List<Tag> getTagsByPostId(long id);
}
