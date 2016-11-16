package boun.cmpe451.group9.Controllers.Post;

import boun.cmpe451.group9.Models.DB.Post;
import boun.cmpe451.group9.Service.Post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public void setPostService(PostService postService){
        this.postService = postService;
    }

    /**
     * Retrieves post "id"
     * @param id id of the post
     * @return post "id"
     */
    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") long id){
        if(postService.checkIfPostExists(id)){
            Post post = postService.getPostById(id);

            Link selfLink = linkTo(PostController.class).slash(id).withSelfRel();
            post.add(selfLink);

            return new ResponseEntity<>(post, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Adds new post
     * @param post post we want to add
     * @return post we just added
     */
    @PostMapping
    public ResponseEntity<Post> addPost(@RequestBody Post post){
        postService.addPost(post);

        Link selfLink = linkTo(PostController.class).slash(post.getEntityId()).withSelfRel();
        post.add(selfLink);

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    /**
     * Updates post "id"
     * @param id id of the post
     * @param post updated post
     * @return post we just updated
     */
    @PutMapping("{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") long id,@RequestBody Post post){
        if(postService.checkIfPostExists(id)){
            post.setEntityId(id);
            postService.updatePost(post);

            Link selfLink = linkTo(PostController.class).slash(id).withSelfRel();
            post.add(selfLink);

            return new ResponseEntity<>(post, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes post "id"
     * @param id id of the post
     * @return NO_CONTENT if post is found and deleted, NOT_FOUND if post is not found
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") long id){
        if(postService.checkIfPostExists(id)){
            postService.removePostById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
