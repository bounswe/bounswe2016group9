package boun.cmpe451.group9.Controllers.FollowRel;

import boun.cmpe451.group9.Controllers.User.UserController;
import boun.cmpe451.group9.Models.DB.FollowRel;
import boun.cmpe451.group9.Models.DB.User;
import boun.cmpe451.group9.Models.Meta.FollowUserRequest;
import boun.cmpe451.group9.Service.FollowRel.FollowRelService;
import boun.cmpe451.group9.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping(value="/users")
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


    @PostMapping("/follow_user")
    public ResponseEntity<FollowRel> addFollowRel(@RequestBody FollowUserRequest request){
        if(!followRelService.checkIfFollowRelExistsByIds(request.getFollowerId(), request.getFollowingId())){
            FollowRel fR= new FollowRel();
            fR.setFollower(userService.getById(request.getFollowerId()));
            fR.setFollowing(userService.getById(request.getFollowingId()));
            followRelService.save(fR);
            fR = addLinksToFollowRel(fR);
            return new ResponseEntity<>(fR, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }



    /**
     * Returns all users that user "id" follows
     * @param id user "id"
     * @return all users that user "id" follows
     */
    @GetMapping("{id}/following")
    public ResponseEntity<List<User>> getFollowingsById(@PathVariable("id") long id){
        if(userService.checkIfEntityExistsById(id)){
            System.out.println("Controller Id " + id);
            List<User> users = followRelService.getFollowingByUserId(id);

            users.forEach(UserController::addLinkToUser);

            return new ResponseEntity<>(users, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Returns all users that follow user "id"
     * @param id user "id"
     * @return all users that follow user "id"
     */
    @GetMapping("{id}/follower")
    public ResponseEntity<List<User>> getFollowersById(@PathVariable("id") long id){
        if(userService.checkIfEntityExistsById(id)){
            List<User> users = followRelService.getFollowerByUserId(id);

            users.forEach(UserController::addLinkToUser);

            return new ResponseEntity<>(users, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public static FollowRel addLinksToFollowRel(FollowRel followRel){
        followRel.add(linkTo(FollowRelController.class).slash(followRel.getEntityId()).withSelfRel());
        return followRel;
    }
}
