package boun.cmpe451.group9.Service.Search;


import boun.cmpe451.group9.Models.DB.Post;
import boun.cmpe451.group9.Models.DB.Topic;

import java.util.List;

public interface SearchService {

    /**
     * Retrieves all searched topics by string "keywords"
     * @param keywords topic name
     * @return list of topic correspond of query
     */
    List<Topic> searchTopic(String keywords);

    /**
     * Retrieves all searched posts by string "keywords"
     * @param keywords post name
     * @return list of posts correspond of query
     */
    List<Post> searchPost(String keywords);

}
