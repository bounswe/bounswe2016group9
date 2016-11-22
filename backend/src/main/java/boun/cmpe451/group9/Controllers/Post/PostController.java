package boun.cmpe451.group9.Controllers.Post;

import boun.cmpe451.group9.Controllers.Comment.CommentController;
import boun.cmpe451.group9.Controllers.Tag.TagController;
import boun.cmpe451.group9.Models.DB.Comment;
import boun.cmpe451.group9.Models.DB.Post;
import boun.cmpe451.group9.Models.DB.Tag;
import boun.cmpe451.group9.Service.Comment.CommentService;
import boun.cmpe451.group9.Service.Post.PostService;
import boun.cmpe451.group9.Service.Tag.TagService;
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
    private CommentService commentService;
    private TagService tagService;

    @Autowired
    public void setPostService(PostService postService){
        this.postService = postService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) { this.commentService = commentService; }

    @Autowired
    public void setTagService(TagService tagService) {this.tagService = tagService; }

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

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts = postService.findAll();

        if(!posts.isEmpty()){
            posts.forEach(PostController::addLinksToPost);

            return new ResponseEntity<>(posts, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     Returns a response for the request "GET /posts/{id}/tags"
     * @param id the id of the resource "Post"
     * @return OK with the list of tags that post owns, NOT_FOUND if the post is not found
     */
   @GetMapping("{id}/tags")
    public ResponseEntity<List<Tag>> getAllTagsByPostId(@PathVariable("id") long id){
        if(postService.checkIfEntityExistsById(id)){
            List<Tag> tags = tagService.getTagsByPostId(id);

            if(!tags.isEmpty()){
                tags.forEach(TagController::addLinksToTag);

                return new ResponseEntity<>(tags, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     Returns a response for the request "GET /posts/{id}/comments"
     * @param id the id of the resource "Post"
     * @return OK with the list of comments that post owns, NOT_FOUND if the post is not found
     */
    @GetMapping("{id}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByPostId(@PathVariable("id") long id){
        if(postService.checkIfEntityExistsById(id)){
            List <Comment> comments= commentService.getCommentByPostId(id);
            if(!comments.isEmpty()){
                comments.forEach(CommentController::addLinksToComment);
                return  new ResponseEntity<>(comments, HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public static Post addLinksToPost(Post post){
        post.add(linkTo(PostController.class).slash(post.getEntityId()).withSelfRel());
        post.add(linkTo(PostController.class).slash("tags").withRel("tags"));
        post.add(linkTo(PostController.class).slash("comments").withRel("comments"));
        return post;
    }


}
