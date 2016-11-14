package boun.cmpe451.group9.Controllers.FollowRel;

import boun.cmpe451.group9.Models.DB.User;
import boun.cmpe451.group9.Service.FollowRel.FollowRelService;
import boun.cmpe451.group9.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("users/{id}")
public class FollowRelController {

    private FollowRelService followRelService;
    private UserService userService;

    @Autowired
    public void setFollowRelService(FollowRelService followRelService){
        this.followRelService = followRelService;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping("following")
    public ResponseEntity<List<User>> getFollowingsById(@PathVariable("id") long id){
        if(userService.checkUserExists(id)){
            List<User> users = followRelService.getFollowingByUserId(id);

            for(User user : users){
                user.add(linkTo(User.class).slash(user.getEntityId()).withSelfRel());
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("follower")
    public ResponseEntity<List<User>> getFollowerById(@PathVariable("id") long id){
        if(userService.checkUserExists(id)){
            List<User> users = followRelService.getFollowerByUserId(id);

            for(User user : users){
                user.add(linkTo(User.class).slash(user.getEntityId()).withSelfRel());
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
