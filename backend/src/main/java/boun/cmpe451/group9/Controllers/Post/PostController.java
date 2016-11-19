package boun.cmpe451.group9.Controllers.Post;

import boun.cmpe451.group9.Models.DB.Post;
import boun.cmpe451.group9.Service.Post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@SuppressWarnings("MVCPathVariableInspection")
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
        if(postService.checkIfEntityExistsById(id)){
            Post post = addLinksToPost(postService.getById(id));

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
        postService.save(post);

        post = addLinksToPost(post);

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
        if(postService.checkIfEntityExistsById(id)){
            post.setEntityId(id);
            postService.save(post);

            post = addLinksToPost(post);

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
        if(postService.checkIfEntityExistsById(id)){
            postService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts = postService.findAll();

        if(posts.isEmpty()){
            posts.forEach(this::addLinksToPost);

            return new ResponseEntity<>(posts, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private Post addLinksToPost(Post post){
        post.add(linkTo(PostController.class).slash(post.getEntityId()).withSelfRel());
        return post;
    }
}
