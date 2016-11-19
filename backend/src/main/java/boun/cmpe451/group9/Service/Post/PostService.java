package boun.cmpe451.group9.Service.Post;

import boun.cmpe451.group9.Models.DB.Post;
import boun.cmpe451.group9.Service.BaseService;

import java.util.List;


public interface PostService extends BaseService<Post>{
    /**
     * Retrieves all the posts created by the user "id"
     * @param id the id of the user
     * @return list of topics that the user "id" created
     */

    List<Post> getPostByUserId(long id);
}
