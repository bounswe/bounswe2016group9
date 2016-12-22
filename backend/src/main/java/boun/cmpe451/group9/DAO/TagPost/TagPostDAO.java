package boun.cmpe451.group9.DAO.TagPost;
import boun.cmpe451.group9.DAO.BaseDAO;
import boun.cmpe451.group9.Models.DB.Post;
import boun.cmpe451.group9.Models.DB.TagPost;

import java.util.Collection;

/**
 * DAO methods for entity TagPost
 */
public interface TagPostDAO extends BaseDAO<TagPost>{
    Collection<? extends Post> searchPostByTag(String[] strings);
}
