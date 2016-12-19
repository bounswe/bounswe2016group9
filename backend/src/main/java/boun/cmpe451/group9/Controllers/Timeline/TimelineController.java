package boun.cmpe451.group9.Controllers.Timeline;


import boun.cmpe451.group9.Controllers.Comment.CommentController;
import boun.cmpe451.group9.Controllers.Post.PostController;
import boun.cmpe451.group9.Controllers.Topic.TopicController;
import boun.cmpe451.group9.Models.DB.*;
import boun.cmpe451.group9.Service.Comment.CommentService;
import boun.cmpe451.group9.Service.FollowRel.FollowRelService;
import boun.cmpe451.group9.Service.FollowTopic.FollowTopicService;
import boun.cmpe451.group9.Service.Post.PostService;
import boun.cmpe451.group9.Service.Topic.TopicService;
import boun.cmpe451.group9.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by seha on 17.12.2016.
 */

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping(value = "/timeline")
public class TimelineController {

    private TopicService topicService;
    private PostService postService;
    private FollowTopicService followTopicService;
    private UserService userService;
    private CommentService commentService;
    private FollowRelService followRelService;


    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }
    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }
    @Autowired
    public void setFollowTopicService(FollowTopicService followTopicService) { this.followTopicService = followTopicService;}
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
    @Autowired
    public void setFollowRelService(FollowRelService followRelService){ this.followRelService=followRelService;}

    @GetMapping("{id}/postOfTopics")
    public ResponseEntity<List<Post>> getTimelineFollowingTopicPost(@PathVariable("id") long id){

        List<Topic> followingTopics=followTopicService.getFollowingTopicsById(id);
        List<Long> followingTopicIds= new ArrayList<>();

        for(Topic topic: followingTopics){
            followingTopicIds.add(topic.getEntityId());
        }
        List<Post> posts = postService.getPostsByTopicIdForTimeline(followingTopicIds);


        if(!posts.isEmpty()){
            posts.forEach(PostController::addLinksToPost);

        return new ResponseEntity<>(posts, HttpStatus.OK);

        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}/postOfUsers")
    public ResponseEntity<List<Post>> getTimelineFollowingUserPosts(@PathVariable("id") long id){
        List<User> followingUsers= followRelService.getFollowingByUserId(id);
        List<Long> followingUserIds= new ArrayList<>();

        for(User user: followingUsers){
            followingUserIds.add(user.getEntityId());
        }

        List<Post> posts=postService.getPostsByUserIdForTimeline(followingUserIds);

        if(!posts.isEmpty()){
            posts.forEach(PostController::addLinksToPost);

            return new ResponseEntity<>(posts, HttpStatus.OK);

        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}/topicOfUsers")
    public ResponseEntity<List<Topic>> getTimelineFollowingUserTopics(@PathVariable("id") long id){
        List<User> followingUsers= followRelService.getFollowingByUserId(id);
        List<Long> followingUserIds= new ArrayList<>();

        for(User user: followingUsers){
            followingUserIds.add(user.getEntityId());
        }

        List<Topic> topics=topicService.getTopicsByUserIdForTimeline(followingUserIds);

        if(!topics.isEmpty()){
            topics.forEach(TopicController::addLinkToTopic);

            return new ResponseEntity<>(topics, HttpStatus.OK);

        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Returns a response for the request "GET /timeline/{id}/commentOfUsers"
     * @param id the id of the resource "User"
     * @return OK with resource with the given id if it is found, NOT_FOUND if the resource is not found
     */
    @GetMapping("{id}/commentOfUsers")
    public ResponseEntity<List<Comment>> getTimelineFollowingUserComments(@PathVariable("id") long id){
        List<User> followingUsers= followRelService.getFollowingByUserId(id);
        List<Long> followingUserIds= new ArrayList<>();

        for(User user: followingUsers){
            followingUserIds.add(user.getEntityId());
        }

        List<Comment> comments=commentService.getCommentsByUserIdForTimeline(followingUserIds);

        if(!comments.isEmpty()){
            comments.forEach(CommentController::addLinksToComment);

            return new ResponseEntity<>(comments, HttpStatus.OK);

        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
