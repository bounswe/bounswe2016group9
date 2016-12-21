package boun.cmpe451.group9.Controllers.FollowTopic;

import boun.cmpe451.group9.Controllers.Topic.TopicController;
import boun.cmpe451.group9.Models.DB.FollowTopic;
import boun.cmpe451.group9.Service.FollowTopic.FollowTopicService;
import boun.cmpe451.group9.Service.Topic.TopicService;
import boun.cmpe451.group9.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
/**
 * Created by seha on 21.12.2016.
 */
@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping(value="/users")
public class FollowTopicController {

    private FollowTopicService followTopicService;
    private UserService userService;
    private TopicService topicService;
    @Autowired
    public void setFollowTopicService(FollowTopicService followTopicService) {
        this.followTopicService = followTopicService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    /**
     * Adds a new relation
     * @return relation we just added
     */
    @PostMapping("{userId}/follow_topic/{topicId}")
    public ResponseEntity<FollowTopic> addFollowTopic(@PathVariable("userId") long userId, @PathVariable("topicId") long topicId){
        if(!followTopicService.checkIfFollowTopicExistsByIds(userId, topicId)){
            FollowTopic followTopic= new FollowTopic();
            followTopic.setFollower(userService.getById(userId));
            followTopic.setTopic(topicService.getById(topicId));


            followTopicService.save(followTopic);

            followTopic = addLinksToFollowTopic(followTopic);

            return new ResponseEntity<>(followTopic, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }



    public static FollowTopic addLinksToFollowTopic(FollowTopic followTopic){
        followTopic.add(linkTo(FollowTopicController.class).slash(followTopic.getEntityId()).withSelfRel());
        return followTopic;
    }
}


