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


    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id){

        if(userService.checkUserExists(id)) {
            User user = userService.getUserById(id);

            Link selfLink = linkTo(UserController.class).slash(user.getUserID()).withSelfRel();
            user.add(selfLink);

            return new ResponseEntity<>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){

        if(userService.checkUserExists(id)){
            userService.updateUser(id, user);

            Link selfLink = linkTo(UserController.class).slash(id).withSelfRel();
            user.add(selfLink);

            return new ResponseEntity<>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        if(!userService.checkUserExistsByUsername(user.getUsername())){
            userService.addUser(user);

            Link selfLink = linkTo(UserController.class).slash(userService.getUserByUsername(user.getUsername()).getUserID())
                    .withSelfRel();
            user.add(selfLink);

            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id){
        if(userService.checkUserExists(id)) {
            userService.removeUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}/topics")
    public ResponseEntity<List<Topic>> getTopicsByUserId(@PathVariable("id") long id){
        if(userService.checkUserExists(id)) {

            List<Topic> topics = topicService.getTopicsByUserId(id);
            for (Topic topic : topics) {
                Link selfLink = linkTo(TopicController.class).slash(topic.getTopicID()).withSelfRel();
                topic.add(selfLink);
            }

            return new ResponseEntity<>(topics, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();

        for(User user: allUsers){
            Link selfLink = linkTo(UserController.class).slash(user.getUserID()).withSelfRel();
            user.add(selfLink);
            if(topicService.getTopicsByUserId(user.getUserID()).size() > 0){
                Link topicsLink = linkTo(UserController.class).slash(user.getUserID()).slash("topics").withRel("allTopics");
                user.add(topicsLink);
            }
        }

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
