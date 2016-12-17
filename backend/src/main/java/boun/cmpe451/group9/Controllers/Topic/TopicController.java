package boun.cmpe451.group9.Controllers.Topic;

import boun.cmpe451.group9.Controllers.AutoComplete.AutoCompleteController;
import boun.cmpe451.group9.Controllers.Post.PostController;
import boun.cmpe451.group9.Controllers.Relation.RelationController;
import boun.cmpe451.group9.Controllers.Search.SearchController;
import boun.cmpe451.group9.Controllers.SemanticTag.SemanticTagController;
import boun.cmpe451.group9.Controllers.Tag.TagController;
import boun.cmpe451.group9.Controllers.User.UserController;
import boun.cmpe451.group9.Models.DB.*;
import boun.cmpe451.group9.Models.Meta.SPARQLEntityResponse;
import boun.cmpe451.group9.Models.Meta.SPARQLTypeResponse;
import boun.cmpe451.group9.Models.Meta.TopicTagResponse;
import boun.cmpe451.group9.Service.FollowTopic.FollowTopicService;
import boun.cmpe451.group9.Service.Post.PostService;
import boun.cmpe451.group9.Service.Relation.RelationService;
import boun.cmpe451.group9.Service.STagTopic.STagTopicService;
import boun.cmpe451.group9.Service.SemanticTag.SemanticTagService;
import boun.cmpe451.group9.Service.Tag.TagService;
import boun.cmpe451.group9.Service.TagTopic.TagTopicService;
import boun.cmpe451.group9.Service.Topic.TopicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * The controller for the resource "Topic"
 */
@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping(value = "/topics")
public class TopicController {

    private TopicService topicService;
    private TagService tagService;
    private TagTopicService tagTopicService;
    private SemanticTagService semanticTagService;
    private STagTopicService sTagTopicService;
    private RelationService relationService;
    private PostService postService;
    private FollowTopicService followTopicService;

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
    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }
    @Autowired
    public void setFollowTopicService(FollowTopicService followTopicService){this.followTopicService=followTopicService;}
    /**
     * Returns a response for the request "GET /topics/{id}"
     * @param id the id of the resource "Topic"
     * @return OK with resource with the given id if it is found, NOT_FOUND if the resource is not found
     */
    @GetMapping("{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable("id") long id){
        if(topicService.checkIfEntityExistsById(id)){
            Topic topic = addLinkToTopic(topicService.getById(id));

            return new ResponseEntity<>(topic, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Topic(),HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Test controller for checking DBPedia connection
     * @return json; semantic tags about "Venus"
     * @throws Exception IOException
     */
    @GetMapping("/test")
    public ResponseEntity<SPARQLEntityResponse> testSPARQL() throws Exception {
        URL url = new URL(createUrlForSPARQLMultiEntities("Juno"));

        ObjectMapper mapper = new ObjectMapper();

        SPARQLEntityResponse entity = mapper.readValue(url, SPARQLEntityResponse.class);

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    /**
     * Pulls semantic tags from DBPedia by using topic name for user to pick among them
     * @param topic topic name user wants to add
     * @return json; semantic tags about "topic"
     * @throws Exception IOException
     */
    @GetMapping("/semantic")
    public ResponseEntity<SPARQLEntityResponse> requestSemanticForAddTopic(@RequestParam("topic") String topic) throws Exception{
        URL url = new URL(createUrlForSPARQLMultiEntities(topic));

        ObjectMapper mapper = new ObjectMapper();

        SPARQLEntityResponse response = mapper.readValue(url, SPARQLEntityResponse.class);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Add selected semantic tags and given topic with tags to DB
     * @param topicTagResponse topic+tag+s.tags
     * @return topic just created
     * @throws Exception IOException
     */
    @PostMapping("/semantic")
    public ResponseEntity<Topic> requestAddTopicAfterSemantic(@RequestBody TopicTagResponse topicTagResponse) throws Exception{
        Topic topic = topicTagResponse.getTopic();
        List<Tag> tags = topicTagResponse.getTags();

        topicService.save(topic);

        URL url = new URL(createUrlForSPARQLMultiTypes(topicTagResponse.getLabel()));
        ObjectMapper mapper = new ObjectMapper();
        SPARQLTypeResponse response = mapper.readValue(url, SPARQLTypeResponse.class);
        List<SPARQLTypeResponse.Results.Types> types = response.getResults().getBindings();

        for(SPARQLTypeResponse.Results.Types type : types){
            SemanticTag tag = new SemanticTag();
            STagTopic sTagTopic = new STagTopic();

            tag.setType(type.getTypes().getValue());

            if(semanticTagService.checkExistenceByName(tag.getType())){
                sTagTopic.setSemanticTag(semanticTagService.getSTagByName(tag.getType()));
            }else{
                semanticTagService.save(tag);
                sTagTopic.setSemanticTag(tag);
            }

            sTagTopic.setTopic(topic);
            sTagTopicService.save(sTagTopic);
        }

        for(Tag tag : tags){
            if(tagService.checkIfTagExistsByName(tag.getName())){
                tag = tagService.getTagByName(tag.getName());
            }else{
                tagService.save(tag);
            }

            TagTopic tagTopic = new TagTopic();
            tagTopic.setTag(tag);
            tagTopic.setTopic(topic);
            tagTopicService.save(tagTopic);
        }

        topic = addLinkToTopic(topic);
        return new ResponseEntity<>(topic, HttpStatus.OK);
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

        topicService.save(topic);

        for(Tag tag : tags){
            if(!tagService.checkIfTagExistsByName(tag.getName())){
                tagService.save(tag);
            }

            TagTopic tagTopic = new TagTopic();
            tagTopic.setTag(tag);tagTopic.setTopic(topic);
            tagTopicService.save(tagTopic);
        }

        topic = addLinkToTopic(topic);
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
        if(topicService.checkIfEntityExistsById(id)){
            topic.setEntityId(id);
            topicService.save(topic);

            topic = addLinkToTopic(topic);

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
        if(topicService.checkIfEntityExistsById(id)) {
            topicService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Returns all topics
     * @return list of all topics
     */
    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics(){
        List<Topic> topics = topicService.findAll();

        if(!topics.isEmpty()){
            topics.forEach(TopicController::addLinkToTopic);

            return new ResponseEntity<>(topics, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all relations that come from the topic
     * @param id topic id relations came from
     * @return list of relations
     */
    @GetMapping("{id}/relationsFrom")
    public ResponseEntity<List<Relation>> getAllRelationsFromTopic(@PathVariable("id") long id){
        if(topicService.checkIfEntityExistsById(id)){
            List<Relation> relations = relationService.getRelationFromTopicByTopicId(id);

            if(!relations.isEmpty()){
                relations.forEach(RelationController::addLinksToRelation);

                return new ResponseEntity<>(relations, HttpStatus.OK);
            }
        }
        // TODO backend, reported by frontend
        // Please return empty if topic has not a relation
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     Get all relations that end at the topic
     * @param id topic id relations ended
     * @return list of relations
     */
    @GetMapping("{id}/relationsTo")
    public ResponseEntity<List<Relation>> getAllRelationsToTopic(@PathVariable("id") long id){
        if(topicService.checkIfEntityExistsById(id)){
            List<Relation> relations = relationService.getAllRelationsToTopicByTopicId(id);

            if(!relations.isEmpty()){
                relations.forEach(RelationController::addLinksToRelation);

                return new ResponseEntity<>(relations, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("{id}/tags")
    public ResponseEntity<List<Tag>> getAllTagsByTopicId(@PathVariable("id") long id){
        if(topicService.checkIfEntityExistsById(id)){
            List<Tag> tags = tagService.getTagsByTopicId(id);

            if(!tags.isEmpty()){
                tags.forEach(TagController::addLinksToTag);

                return new ResponseEntity<>(tags, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}/semanticTags")
    public ResponseEntity<List<SemanticTag>> getAllSTagsByTopicId(@PathVariable("id") long id){
        if(topicService.checkIfEntityExistsById(id)){
            List<SemanticTag> semanticTags = sTagTopicService.getSTagsByTopicId(id);

            if(!semanticTags.isEmpty()){
                semanticTags.forEach(SemanticTagController::addLinksToSTag);

                return new ResponseEntity<>(semanticTags, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("{id}/posts")
    public ResponseEntity<List<Post>> getAllPostsByTopicId(@PathVariable("id") long id){
        if(topicService.checkIfEntityExistsById(id)) {

            List<Post> posts = postService.getPostByTopicId(id);
            if (!posts.isEmpty()) {
                posts.forEach(PostController::addLinksToPost);


                return new ResponseEntity<>(posts, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}/followers")
    public ResponseEntity<List<User>> getFollowerUsersById(@PathVariable("id") long id){
        if(topicService.checkIfEntityExistsById(id)){
            List<User> followers= followTopicService.getFollowerUsersById(id);
            if(!followers.isEmpty()){
                followers.forEach(UserController::addLinkToUser);
                return  new ResponseEntity<>(followers,HttpStatus.OK);
            }
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @GetMapping("/grappi")
    public ResponseEntity<List<Topic>> getGrappi(){
        List<Topic> grappiTopics = topicService.getGrappi();

        if(!grappiTopics.isEmpty()){
            grappiTopics.forEach(TopicController::addLinkToTopic);

            return new ResponseEntity<>(grappiTopics, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}/related")
    public ResponseEntity<List<Topic>> getMostRelatedTopics(long id){
        List<Topic> related = topicService.getMostRelatedTopics(id);

        if(!related.isEmpty()){
            related.forEach(TopicController::addLinkToTopic);
            return new ResponseEntity<>(related, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public static Topic addLinkToTopic(Topic topic){
        topic.add(linkTo(TopicController.class).slash(topic.getEntityId()).withSelfRel());
        topic.add(linkTo(TopicController.class).slash(topic.getEntityId()).slash("tags").withRel("tags"));
        topic.add(linkTo(TopicController.class).slash(topic.getEntityId()).slash("semanticTags").withRel("semanticTags"));
        topic.add(linkTo(TopicController.class).slash(topic.getEntityId()).slash("fromTopics").withRel("relationsFromTopics"));
        topic.add(linkTo(TopicController.class).slash(topic.getEntityId()).slash("toTopics").withRel("relationsToTopics"));
        topic.add(linkTo(TopicController.class).slash(topic.getEntityId()).slash("posts").withRel("posts"));
        topic.add(linkTo(SearchController.class).withRel("search"));
        topic.add(linkTo(AutoCompleteController.class).withRel("autoComplete"));
        return topic;
    }

    /**
     * Creates url for getting entities from DBPedia
     * @param entity topic name whose DBPedia entities we want to get
     * @return all DBPedia entities for given topic
     * @throws Exception IOException
     */
    private String createUrlForSPARQLMultiEntities(String entity) throws Exception{
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

    /**
     * Creates url for getting s.tags from DBPedia
     * @param label the name of entity whose s.tags we want
     * @return all s.tags for "entity"
     * @throws Exception IOException
     */
    private String createUrlForSPARQLMultiTypes(String label) throws Exception{
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
