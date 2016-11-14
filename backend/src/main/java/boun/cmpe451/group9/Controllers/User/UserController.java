package boun.cmpe451.group9.Controllers.User;

import boun.cmpe451.group9.Controllers.Topic.TopicController;
import boun.cmpe451.group9.Models.DB.Topic;
import boun.cmpe451.group9.Models.DB.User;
import boun.cmpe451.group9.Service.Topic.TopicService;
import boun.cmpe451.group9.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * The controller for the resource "User"
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    private TopicService topicService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTopicService(TopicService topicService){ this.topicService = topicService; }

    /**
     * Returns a response for the request "GET /users/{id}"
     * @param id the id of the resource "User"
     * @return OK with the resource with the given id if the it is found, NOT_FOUND if it is not found
     */
    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id){

        if(userService.checkUserExists(id)) {
            User user = userService.getUserById(id);

            Link selfLink = linkTo(UserController.class).slash(user.getEntityId()).withSelfRel();
            user.add(selfLink);

            return new ResponseEntity<>(user, HttpStatus.OK);
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

        if(userService.checkUserExists(id)){
            user.setEntityId(id);
            userService.updateUser(user);

            Link selfLink = linkTo(UserController.class).slash(id).withSelfRel();
            user.add(selfLink);

            return new ResponseEntity<>(user, HttpStatus.OK);
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
            userService.addUser(user);

            Link selfLink = linkTo(UserController.class).slash(userService.getUserByUsername(user.getUsername()).getEntityId())
                    .withSelfRel();
            user.add(selfLink);

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
        if(userService.checkUserExists(id)) {
            userService.removeUser(id);
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
        if(userService.checkUserExists(id)) {

            List<Topic> topics = topicService.getTopicsByUserId(id);
            for (Topic topic : topics) {
                Link selfLink = linkTo(TopicController.class).slash(topic.getEntityId()).withSelfRel();
                topic.add(selfLink);
            }

            return new ResponseEntity<>(topics, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Returns a response for the request "GET /users"
     * @return the list of all users
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();

        for(User user: allUsers){
            Link selfLink = linkTo(UserController.class).slash(user.getEntityId()).withSelfRel();
            user.add(selfLink);
            if(topicService.getTopicsByUserId(user.getEntityId()).size() > 0){
                Link topicsLink = linkTo(UserController.class).slash(user.getEntityId()).slash("topics").withRel("allTopics");
                user.add(topicsLink);
            }
        }

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
