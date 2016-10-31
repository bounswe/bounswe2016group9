package boun.cmpe451.group9.Controllers.Topic;

import boun.cmpe451.group9.Models.DB.Topic;
import boun.cmpe451.group9.Service.Topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * The controller for the resource "Topic"
 */
@RestController
@RequestMapping(value = "/topics")
public class TopicController {

    private TopicService topicService;

    @Autowired
    public void setTopicService(TopicService topicService){
        this.topicService = topicService;
    }

    /**
     * Returns a response for the request "GET /topics/{id}"
     * @param id the id of the resource "Topic"
     * @return OK with resource with the given id if it is found, NOT_FOUND if the resource is not found
     */
    @GetMapping("{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable("id") long id){
        if(topicService.checkTopicExistsById(id)){
            Topic topic = topicService.getTopicById(id);

            Link selfLink = linkTo(Topic.class).slash(id).withSelfRel();
            topic.add(selfLink);

            return new ResponseEntity<>(topic, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Returns a response for the request "POST /topics"
     * @param topic a new resource "Topic"
     * @return CREATED if a new resource is successfully created, CONFLICT if the resource already exists
     */
    @PostMapping
    public ResponseEntity<Topic> addTopic(@RequestBody Topic topic){
        if(topicService.checkTopicExistsById(topic.getEntityId())) {
            topicService.addTopic(topic);

            Link selfLink = linkTo(Topic.class).slash(0).withSelfRel();
            topic.add(selfLink);

            return new ResponseEntity<>(topic, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Returns a response for the request "PUT /topics/{id}"
     * @param id the id of the updated resource "Topic"
     * @param topic the updated resource "Topic"
     * @return OK with the updated resource if the update is successful, NOT_FOUND if the resource is not found
     */
    @PutMapping("{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable("id") long id, @RequestBody Topic topic){
        if(topicService.checkTopicExistsById(id)){
            topicService.updateTopic(topic);

            Link selfLink = linkTo(Topic.class).slash(id).withSelfRel();
            topic.add(selfLink);

            return new ResponseEntity<>(topic, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Returns a response for the request "DELETE /topics/{id}"
     * @param id the id of the deleted resource "Topic"
     * @return NO_CONTENT if the deletion is successful, NOT_FOUND if the resource is not found
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Topic> deleteTopic(@PathVariable("id") long id){
        if(topicService.checkTopicExistsById(id)) {
            topicService.removeTopic(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
