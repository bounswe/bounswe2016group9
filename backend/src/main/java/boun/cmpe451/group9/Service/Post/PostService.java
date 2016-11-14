package boun.cmpe451.group9.Service.Post;

import boun.cmpe451.group9.Models.DB.Post;


public interface PostService {

    Post getPostById(long id);

    void addPost(Post post);

    void updatePost(Post post);

    void removePostById(long id);

    boolean checkIfPostExists(long id);
}
