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

    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") long id){
        if(postService.checkIfPostExists(id)){
            Post post = postService.getPostById(id);

            Link selfLink = linkTo(Post.class).slash(id).withSelfRel();
            post.add(selfLink);

            return new ResponseEntity<>(post, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Post> addPost(@RequestBody Post post){
        postService.addPost(post);

        Link selfLink = linkTo(Post.class).slash(post.getEntityId()).withSelfRel();
        post.add(selfLink);

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") long id,@RequestBody Post post){
        if(postService.checkIfPostExists(id)){
            postService.updatePost(post);

            Link selfLink = linkTo(Post.class).slash(id).withSelfRel();
            post.add(selfLink);

            return new ResponseEntity<>(post, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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
