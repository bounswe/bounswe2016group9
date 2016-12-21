package boun.cmpe451.group9.Controllers.AutoComplete;

import boun.cmpe451.group9.Models.DB.RelationType;
import boun.cmpe451.group9.Models.DB.Topic;
import boun.cmpe451.group9.Service.RelationType.RelationTypeService;
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

    private RelationTypeService relationTypeService;

    @Autowired
    public void setTopicService(TopicService topicService){ this.topicService = topicService; }

    @Autowired
    public void setRelationTypeService(RelationTypeService relationTypeService){ this.relationTypeService = relationTypeService; }

    /**
     * Auto completes topic names
     * @param keyword first part of the topic name
     * @return topics that starts with "keyword"
     */
    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> autoCompTopics(@RequestParam("keyword") String keyword){
        return new ResponseEntity<>(topicService.autoComp(keyword), HttpStatus.OK);
    }

    /**
     * Auto completes relation types
     * @param keyword first part of the relation name
     * @return relations that starts with "keyword"
     */
    @GetMapping("/relationTypes")
    public ResponseEntity<List<RelationType>> autoCompRelTypes(@RequestParam("keyword") String keyword){
        return new ResponseEntity<>(relationTypeService.autoComp(keyword), HttpStatus.OK);
    }
}
