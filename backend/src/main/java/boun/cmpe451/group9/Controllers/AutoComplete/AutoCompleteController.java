package boun.cmpe451.group9.Controllers.AutoComplete;

import boun.cmpe451.group9.Models.DB.Topic;
import boun.cmpe451.group9.Service.Topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/autoComp")
public class AutoCompleteController {

    private TopicService topicService;

    @Autowired
    public void setTopicService(TopicService topicService){ this.topicService = topicService; }

    @GetMapping
    public ResponseEntity<List<Topic>> autoCompTopics(@RequestParam("keyword") String keyword){
        return new ResponseEntity<>(topicService.autoComp(keyword), HttpStatus.OK);
    }
}
