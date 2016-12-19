package boun.cmpe451.group9.Controllers.User;

import boun.cmpe451.group9.Controllers.Comment.CommentController;
import boun.cmpe451.group9.Controllers.Post.PostController;
import boun.cmpe451.group9.Controllers.Topic.TopicController;
import boun.cmpe451.group9.Models.DB.Comment;
import boun.cmpe451.group9.Models.DB.Post;
import boun.cmpe451.group9.Models.DB.Topic;
import boun.cmpe451.group9.Models.DB.User;
import boun.cmpe451.group9.Service.Comment.CommentService;
import boun.cmpe451.group9.Service.FollowTopic.FollowTopicService;
import boun.cmpe451.group9.Service.Post.PostService;
import boun.cmpe451.group9.Service.Topic.TopicService;
import boun.cmpe451.group9.Service.User.UserService;
import boun.cmpe451.group9.Service.UserRole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * The controller for the resource "User"
 */
@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    private TopicService topicService;

    private PostService postService;

    private CommentService commentService;

    private FollowTopicService followTopicService;

    private UserRoleService userRoleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTopicService(TopicService topicService){ this.topicService = topicService; }

    @Autowired
    public void setPostService(PostService postService){this.postService=postService;}

    @Autowired
    public void setCommentService(CommentService commentService){ this.commentService= commentService;}

    @Autowired
    public void setFollowTopicService(FollowTopicService followTopicService){ this.followTopicService=followTopicService;}

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService){
        this.userRoleService = userRoleService;
    }

    /**
     * Returns a response for the request "GET /users/{id}"
     * @param id the id of the resource "User"
     * @return OK with the resource with the given id if the it is found, NOT_FOUND if it is not found
     */
    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id){
        if(userService.checkIfEntityExistsById(id)) {
            User user = addLinkToUser(userService.getById(id));

            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if(currentUser.getUsername().equals(user.getUsername()))
                return new ResponseEntity<>(user, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Returns a response for the request "PUT /users/{id}"
     * @param id the id of the resource "User"
     * @param user the updated resource
     * @return OK with the updated resource if the resource is found and updated, NOT_FOUND if the resource is not found
     */
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){

        if(userService.checkIfEntityExistsById(id)){
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if(currentUser.getEntityId() == id) {
                user.setEntityId(id);
                userService.save(user);

                user = addLinkToUser(user);

                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Returns a response for the request "POST /users"
     * @param user the new resource "User"
     * @return CREATED if the resource is added successfully, CONFLICT if it is not found
     */
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        if(!userService.checkUserExistsByUsername(user.getUsername())){
            userService.save(user);

            user = addLinkToUser(user);

            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Returns a response for the request "DELETE /users/{id}"
     * @param id the id of the deleted resource "User"
     * @return NO_CONTENT if the resource is deleted successfully, NOT_FOUND if it is not found
     */
    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id){
        if(userService.checkIfEntityExistsById(id)) {
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Returns a response for the request "GET /users/{id}/topics"
     * @param id the id of the resource "User"
     * @return OK with the list of topics the user "id" created, NOT_FOUND if the user is not found
     */
    @GetMapping("{id}/topics")
    public ResponseEntity<List<Topic>> getTopicsByUserId(@PathVariable("id") long id){
        if(userService.checkIfEntityExistsById(id)) {

            List<Topic> topics = topicService.getTopicsByUserId(id);
            topics.forEach(TopicController::addLinkToTopic);

            return new ResponseEntity<>(topics, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *Returns a response for the request "GET /users/{id}/posts"
     * @param id the id of the resource "User"
     * @return OK with the list of posts the user "id" created, NOT_FOUND if the user is not found
     */
    @GetMapping("{id}/posts")
    public ResponseEntity<List<Post>> getPostsByUserId (@PathVariable("id") long id){
        if(userService.checkIfEntityExistsById(id)){
            List<Post> posts= postService.getPostByUserId(id);
            posts.forEach(PostController::addLinksToPost);
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *Returns a response for the request "GET /users/{id}/comments"
     * @param id the id of the resource "User"
     * @return OK with the list of comments the user "id" created, NOT_FOUND if the user is not found
     */
    @GetMapping("{id}/comments")
    public ResponseEntity<List<Comment>> getCommentsByUserId (@PathVariable("id") long id){
        if(userService.checkIfEntityExistsById(id)){
            List<Comment> comments= commentService.getCommentsByUserId(id);
            comments.forEach(CommentController::addLinksToComment);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /**
     * Returns a response for the request "GET /users"
     * @return the list of all users
     */
    @SuppressWarnings("unchecked")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(checkAuthority(currentUser)) {
            List<User> allUsers = userService.findAll();

            if (!allUsers.isEmpty()) {
                allUsers.forEach(UserController::addLinkToUser);

                return new ResponseEntity<>(allUsers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity(currentUser, HttpStatus.OK);
        }
    }

    private boolean checkAuthority(User user){
        List<String> authList = userRoleService.findRoleByUsername(user);

        for (String anAuthList : authList) {
            if (anAuthList.equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }

    /**
     *Returns a response for the request "GET /users/{id}/followingTopics"
     * @param id the id of the resource "User"
     * @return OK with the list of followingTopics the user "id" follows, NOT_FOUND if the user is not found
     */
    @GetMapping("{id}/followingTopics")
    public ResponseEntity<List<Topic>> getFollowingTopicsByUserId(@PathVariable("id") long id){
        if (userService.checkIfEntityExistsById(id)){
            List<Topic> followingTopics = followTopicService.getFollowingTopicsById(id);
            followingTopics.forEach(TopicController::addLinkToTopic);
            return new ResponseEntity<>(followingTopics, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /*@GetMapping("{id}/timeline")
    public ResponseEntity<List<Post>> getTimelineByUserId(@PathVariable("id") long id){
        if(userService.checkIfEntityExistsById(id)){

        }

    }*/
    public static User addLinkToUser(User user){
        user.add(linkTo(UserController.class).slash(user.getEntityId()).withSelfRel());
        user.add(linkTo(UserController.class).slash(user.getEntityId()).slash("topics").withRel("topics"));
        return user;
    }
}
