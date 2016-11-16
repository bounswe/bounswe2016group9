package boun.cmpe451.group9.Controllers.Topic;

import boun.cmpe451.group9.Controllers.Relation.RelationController;
import boun.cmpe451.group9.Controllers.Tag.TagController;
import boun.cmpe451.group9.Models.DB.*;
import boun.cmpe451.group9.Models.Meta.*;
import boun.cmpe451.group9.Service.Relation.RelationService;
import boun.cmpe451.group9.Service.STagTopic.STagTopicService;
import boun.cmpe451.group9.Service.SemanticTag.SemanticTagService;
import boun.cmpe451.group9.Service.Tag.TagService;
import boun.cmpe451.group9.Service.TagTopic.TagTopicService;
import boun.cmpe451.group9.Service.Topic.TopicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * The controller for the resource "Topic"
 */
@RestController
@RequestMapping(value = "/topics")
public class TopicController {

    private TopicService topicService;
    private TagService tagService;
    private TagTopicService tagTopicService;
    private SemanticTagService semanticTagService;
    private STagTopicService sTagTopicService;
    private RelationService relationService;

    @Autowired
    public void setTopicService(TopicService topicService){
        this.topicService = topicService;
    }

    @Autowired
    public void setTagService(TagService tagService){
        this.tagService = tagService;
    }

    @Autowired
    public void setTagTopicService(TagTopicService tagTopicService){
        this.tagTopicService = tagTopicService;
    }

    @Autowired
    public void setSemanticTagService(SemanticTagService semanticTagService){
        this.semanticTagService = semanticTagService;
    }

    @Autowired
    public void setsTagTopicService(STagTopicService sTagTopicService){
        this.sTagTopicService = sTagTopicService;
    }

    @Autowired
    public void setRelationService(RelationService relationService){
        this.relationService = relationService;
    }

    private Map<Topic,List<Tag>> map = new HashMap<>();

    /**
     * Returns a response for the request "GET /topics/{id}"
     * @param id the id of the resource "Topic"
     * @return OK with resource with the given id if it is found, NOT_FOUND if the resource is not found
     */
    @GetMapping("{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable("id") long id){
        if(topicService.checkTopicExistsById(id)){
            Topic topic = topicService.getTopicById(id);

            Link selfLink = linkTo(TopicController.class).slash(id).withSelfRel();
            topic.add(selfLink);

            List<Tag> tags = tagService.getTagsByTopicId(id);

            for(Tag tag : tags){
                topic.add(linkTo(TagController.class).slash(tag.getEntityId()).withRel("tag"));
            }

            return new ResponseEntity<>(topic, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<SPARQLEntityResponse> testSparql() throws Exception {
        URL url = new URL(createUrlforSPARQLforMultEntities("Venus"));

        ObjectMapper mapper = new ObjectMapper();

        SPARQLEntityResponse entity = mapper.readValue(url, SPARQLEntityResponse.class);

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping("/request")
    public ResponseEntity<SPARQLEntityResponse> requestAddTopic(@RequestBody RequestTypeResource resource) throws Exception{
        Topic topic = resource.getTopic();
        List<Tag> tags = resource.getTags();

        URL url = new URL(createUrlforSPARQLforMultEntities(topic.getName()));

        ObjectMapper mapper = new ObjectMapper();

        SPARQLEntityResponse response = mapper.readValue(url, SPARQLEntityResponse.class);

        map.put(topic,tags);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*@PostMapping("/request/apply/test")
    public ResponseEntity<SPARQLTypeResponse> testResponse(@RequestBody DBPediaTopicLabel object) throws Exception{
        Topic topic = object.getTopic();
        URL url = new URL(createUrlforSPARQLforMultTypes(object.getLabel()));

        ObjectMapper mapper = new ObjectMapper();

        SPARQLTypeResponse response = mapper.readValue(url, SPARQLTypeResponse.class);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/

    @PostMapping("/request/apply")
    public ResponseEntity<Topic> requestAddTopicAfterSelection(@RequestBody DBPediaTopicLabel object) throws Exception{
        Topic topic = object.getTopic();
        topicService.addTopic(topic);

        URL url = new URL(createUrlforSPARQLforMultTypes(object.getLabel()));

        ObjectMapper mapper = new ObjectMapper();

        SPARQLTypeResponse response = mapper.readValue(url, SPARQLTypeResponse.class);

        List<SPARQLTypeResponse.Results.Types> types = response.getResults().getBindings();

        for(SPARQLTypeResponse.Results.Types type : types){
            SemanticTag tag = new SemanticTag();
            STagTopic sTagTopic = new STagTopic();

            tag.setType(type.getTypes().getValue());

            if(semanticTagService.checkIfSTagExistsByName(tag.getType())){
                sTagTopic.setSemanticTag(semanticTagService.getSTagByName(tag.getType()));
            }else{
                semanticTagService.addSTagWithSave(tag);
                sTagTopic.setSemanticTag(tag);
            }

            sTagTopic.setTopic(topic);
            sTagTopicService.addSTagTopicWithSave(sTagTopic);
        }

        List<Tag> tags = map.get(topic);
        map.remove(topic);

        for(Tag tag : tags){
            if(tagService.checkIfTagExistsByName(tag.getName())){
                tag = tagService.getTagByName(tag.getName());
            }else{
                tagService.addTag(tag);
            }

            TagTopic tagTopic = new TagTopic();
            tagTopic.setTag(tag);
            tagTopic.setTopic(topic);
            tagTopicService.addTagTopicWithSave(tagTopic);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }



    /**
     * Returns a response for the request "POST /topics"
     * @param topicTagResponse a new resource "Topic" and its "tag"s
     * @return CREATED if a new resource is successfully created, CONFLICT if the resource already exists
     */
    @PostMapping
    public ResponseEntity<Topic> addTopic(@RequestBody TopicTagResponse topicTagResponse){
        Topic topic = topicTagResponse.getTopic();
        List<Tag> tags = topicTagResponse.getTags();

        topicService.addTopic(topic);

        Link selfLink = linkTo(TopicController.class).slash(topic.getEntityId()).withSelfRel();
        topic.add(selfLink);

        for(Tag tag : tags){
            long id;

            if(tagService.checkIfTagExistsByName(tag.getName())){
                id = tagService.getTagByName(tag.getName()).getEntityId();
            }else {
                tagService.addTag(tag);
                TagTopic tagTopic = new TagTopic();
                tagTopic.setTag(tag);tagTopic.setTopic(topic);
                tagTopicService.addTagTopicWithSave(tagTopic);
                id =  tag.getEntityId();
            }
            topic.add(linkTo(TagController.class).slash(id).withRel("tag"));
        }

        return new ResponseEntity<>(topic, HttpStatus.CREATED);
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
            topic.setEntityId(id);
            topicService.updateTopic(topic);

            Link selfLink = linkTo(TopicController.class).slash(id).withSelfRel();
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

    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics(){
        List<Topic> topics = topicService.getAllTopics();

        for(Topic topic : topics){
            topic.add(linkTo(TopicController.class).slash(topic.getEntityId()).withSelfRel());
            List<Relation> relations = relationService.getRelationFromTopicByTopicId(topic.getEntityId());

            if(relations.size() > 0){
                topic.add(linkTo(TopicController.class).slash(topic.getEntityId()).slash("relationsFrom").withRel("relationsFrom"));
            }

        }

        return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    @GetMapping("{id}/relationsFrom")
    public ResponseEntity<List<Relation>> getAllRelationsFromTopic(@PathVariable("id") long id){
        if(topicService.checkTopicExistsById(id)){
            List<Relation> relations = relationService.getRelationFromTopicByTopicId(id);

            for(Relation relation : relations){
                relation.add(linkTo(RelationController.class).slash(relation.getEntityId()).withSelfRel());
            }

            return new ResponseEntity<>(relations, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private String createUrlforSPARQLforMultEntities(String entity) throws Exception{
        String url = "http://dbpedia.org/sparql?default-graph-uri=";
        String encode = "http://dbpedia.org&query=PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
                "PREFIX : <http://dbpedia.org/resource/>\n" +
                "PREFIX dbpedia2: <http://dbpedia.org/property/>\n" +
                "PREFIX dbpedia: <http://dbpedia.org/>\n" +
                "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
                "SELECT DISTINCT ?label ?abs ?wikiLink WHERE {\n" +
                "  ?entity rdfs:label ?label .\n" +
                "  ?entity foaf:name \""+entity+"\"@en  .\n" +
                "  ?entity dbo:abstract ?abs .\n" +
                "  ?entity foaf:isPrimaryTopicOf ?wikiLink .\n" +
                "  filter(langMatches(lang(?abs),\"en\")) . \n" +
                "  filter(langMatches(lang(?label),\"en\"))\n" +
                " }";

        encode = URLEncoder.encode(encode, "UTF-8");
        encode = encode.replace("%26query%3D", "&query=");

        return url+encode+"&output=json";
    }

    private String createUrlforSPARQLforMultTypes(String label) throws Exception{
        String url = "http://dbpedia.org/sparql?default-graph-uri=";
        String encode = "http://dbpedia.org&query=PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
                "PREFIX : <http://dbpedia.org/resource/>\n" +
                "PREFIX dbpedia2: <http://dbpedia.org/property/>\n" +
                "PREFIX dbpedia: <http://dbpedia.org/>\n" +
                "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
                "SELECT ?types WHERE {\n" +
                "  ?entity rdf:type ?types .\n" +
                "  ?entity rdfs:label \""+label+"\"@en\n" +
                " }";

        encode = URLEncoder.encode(encode, "UTF-8");
        encode = encode.replace("%26query%3D", "&query=");

        return url+encode+"&output=json";
    }
}
