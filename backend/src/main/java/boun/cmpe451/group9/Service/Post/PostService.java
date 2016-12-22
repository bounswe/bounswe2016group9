package boun.cmpe451.group9.Service.Post;

import boun.cmpe451.group9.Models.DB.Post;
import boun.cmpe451.group9.Service.BaseService;

import java.util.List;

/**
 * Service methods for resource Post
 */
@SuppressWarnings("unused")
public interface PostService extends BaseService<Post>{
    /**
     * Retrieves all the posts created by the user "id"
     * @param id the id of the user
     * @return list of posts that the user "id" created
     */

    List<Post> getPostByUserId(long id);

    /**
     * Retrieves all posts is written under a topic
     * @param id the id of the topic
     * @return list of posts that is under topic "id"
     */
    List<Post> getPostByTopicId(long id);

    /**
     * Retrieves all posts that is sent in specific location
     * @param id the id of the location
     * @return list of posts that is created at location "id"
     */
    List<Post> getPostByLocation(int id);

    /**
     * Retrieves list of most recent posts belong to list of topic "topicIdList"
     * @param topicIdList the list of ids
     * @return list of most recent posts belong to topic "topicIdList"
     */
    List<Post> getPostsByTopicIdForTimeline(List<Long> topicIdList);

    /**
     * Retrieves list of most recent posts belong to list of user "userIdList"
     * @param userIdList the list of ids
     * @return list of most recent posts belong to users "userIdList"
     */
    List<Post> getPostsByUserIdForTimeline(List<Long> userIdList);
}
