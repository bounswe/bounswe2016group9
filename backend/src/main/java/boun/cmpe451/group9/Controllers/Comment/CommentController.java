package boun.cmpe451.group9.Controllers.Comment;

import boun.cmpe451.group9.Controllers.Post.PostController;
import boun.cmpe451.group9.Models.DB.Comment;
import boun.cmpe451.group9.Service.Comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by seha on 19.11.2016.
 */

@SuppressWarnings({"MVCPathVariableInspection", "DefaultFileTemplate"})
@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {this.commentService = commentService;}

    /**
     * Returns a response for the request "GET /comments/{id}"
     * @param id the id of the resource "Comment"
     * @return OK with resource with the given id if it is found, NOT_FOUND if the resource is not found
     */
    @GetMapping("{id}")
    public ResponseEntity<Comment> getComment(@PathVariable("id") long id){
        if(commentService.checkIfEntityExistsById(id)){
            Comment comment =addLinksToComment(commentService.getById(id));

            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Returns a response for the request "POST /comments"
     * @param comment a new resource "Comment"
     * @return CREATED if a new resource is successfully created, CONFLICT if the resource already exists
     */
    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment){
        if(!commentService.checkIfEntityExistsById(comment.getEntityId())){
            commentService.save(comment);
            comment=addLinksToComment(comment);

            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    /**
     * Returns a response for the request "PUT /comments/{id}"
     * @param id the id of the updated resource "comment"
     * @param comment the updated resource "comment"
     * @return OK with the updated resource if the update is successful, NOT_FOUND if the resource is not found
     */
    @PutMapping("{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") long id, @RequestBody Comment comment){
        if(commentService.checkIfEntityExistsById(id)){
            comment.setEntityId(id);
            commentService.save(comment);
            comment=addLinksToComment(comment);

            return new ResponseEntity<>(comment,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a comment by comment id DELETE/comments/{id}
     * @param id id of comment
     * @return NO_CONTENT if the deletion is successful, NOT_FOUND if the resource is not found
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("id") long id){
        if(commentService.checkIfEntityExistsById(id)){
            commentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Adds HATEOAS links to comment entity
     * @param comment entity that links are added to
     * @return entity with links
     */
    public static Comment addLinksToComment(Comment comment) {
        comment.add(linkTo(CommentController.class).slash(comment.getEntityId()).withSelfRel());
        comment.add(linkTo(PostController.class).slash(comment.getPostOfComment().getEntityId()).withRel("post"));
        return comment;
    }
}
