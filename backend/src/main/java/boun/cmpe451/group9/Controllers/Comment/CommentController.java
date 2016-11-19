package boun.cmpe451.group9.Controllers.Comment;

import boun.cmpe451.group9.Models.DB.Comment;
import boun.cmpe451.group9.Service.Comment.CommentService;
import boun.cmpe451.group9.Service.Post.PostService;
import boun.cmpe451.group9.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
/**
 * Created by seha on 19.11.2016.
 */
@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping(value="/comments")

public class CommentController {

    private CommentService commentService;
    private UserService userService;
    private PostService postService;

    @Autowired
    public void setCommentService(CommentService commentService) {this.commentService = commentService;}
    @Autowired
    public void setUserService(UserService userService) {this.userService = userService;}
    @Autowired
    public void setPostService(PostService postService) {this.postService = postService;}

    @GetMapping("{id}")
    public ResponseEntity<Comment> getComment(@PathVariable("id") long id){
        if(commentService.checkIfEntityExistsById(id)){
            Comment comment =addLinksToComment(commentService.getById(id));

            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment){
        if(!commentService.checkIfEntityExistsById(comment.getEntityId())){
            commentService.save(comment);
            comment=addLinksToComment(comment);

            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        }
        return new ResponseEntity<>( HttpStatus.CONFLICT);
    }
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
    @DeleteMapping("{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("id") long id, @RequestBody Comment comment){
        if(commentService.checkIfEntityExistsById(id)){
            commentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // Need to be checked
    public static Comment addLinksToComment(Comment comment) {
        comment.add(linkTo(CommentController.class).slash(comment.getEntityId()).withSelfRel());
        comment.add(linkTo(CommentController.class).slash("posts").withRel("posts"));
        return comment;
    }


}
