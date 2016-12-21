package boun.cmpe451.group9.Controllers.FollowTopic;

import boun.cmpe451.group9.Models.DB.FollowTopic;
import boun.cmpe451.group9.Models.Meta.FollowTopicRequest;
import boun.cmpe451.group9.Service.FollowTopic.FollowTopicService;
import boun.cmpe451.group9.Service.Topic.TopicService;
import boun.cmpe451.group9.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
/**
 * Created by seha on 21.12.2016.
 */
@SuppressWarnings({"MVCPathVariableInspection", "DefaultFileTemplate"})
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
    @PostMapping("/follow-topic")
    public ResponseEntity<FollowTopic> addFollowTopic(@RequestBody FollowTopicRequest request){
        if(!followTopicService.checkIfFollowTopicExistsByIds(request.getUserId(), request.getTopicId())){
            FollowTopic followTopic= new FollowTopic();
            followTopic.setFollower(userService.getById(request.getUserId()));
            followTopic.setTopic(topicService.getById(request.getTopicId()));
            followTopicService.save(followTopic);
            followTopic = addLinksToFollowTopic(followTopic);
            return new ResponseEntity<>(followTopic, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Adds HATEOAS links to FollowTopic entity
     * @param followTopic entity that links are added
     * @return entity with links
     */
    private static FollowTopic addLinksToFollowTopic(FollowTopic followTopic){
        followTopic.add(linkTo(FollowTopicController.class).slash(followTopic.getEntityId()).withSelfRel());
        return followTopic;
    }
}


