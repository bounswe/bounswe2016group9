package boun.cmpe451.group9.Service.Search;


import boun.cmpe451.group9.Models.DB.Post;
import boun.cmpe451.group9.Models.DB.Topic;

import java.util.List;

public interface SearchService {

    List<Topic> searchTopic(String keywords);

    List<Post> searchPost(String keywords);

}
