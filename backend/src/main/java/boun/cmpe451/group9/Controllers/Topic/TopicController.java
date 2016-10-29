package boun.cmpe451.group9.Controllers.Topic;

import boun.cmpe451.group9.Models.DB.Topic;
import boun.cmpe451.group9.Service.Topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@RestController
@RequestMapping(value = "/topics")
public class TopicController {

    private TopicService topicService;

    @Autowired
    public void setTopicService(TopicService topicService){
        this.topicService = topicService;
    }

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

    @PostMapping
    public ResponseEntity<Topic> addTopic(@RequestBody Topic topic){
        topicService.addTopic(topic);

        Link selfLink = linkTo(Topic.class).slash(0).withSelfRel();
        topic.add(selfLink);

        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

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
