package boun.cmpe451.group9.Controllers.Topic;

import boun.cmpe451.group9.Models.DB.Topic;
import boun.cmpe451.group9.Models.Meta.SPARQLEntityResponse;
import boun.cmpe451.group9.Service.Topic.TopicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.net.URLEncoder;

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

    @GetMapping("/test")
    public ResponseEntity<SPARQLEntityResponse> testSparql() throws Exception {
        URL url = new URL(createUrlforSPARQLforMultEntities("Hannibal"));

        ObjectMapper mapper = new ObjectMapper();

        SPARQLEntityResponse entity = mapper.readValue(url, SPARQLEntityResponse.class);

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    /*@PostMapping
    public ResponseEntity<Topic> requestAddTopic(@RequestBody Topic topic){

    }*/

    /**
     * Returns a response for the request "POST /topics"
     * @param topic a new resource "Topic"
     * @return CREATED if a new resource is successfully created, CONFLICT if the resource already exists
     */
    @PostMapping
    public ResponseEntity<Topic> addTopic(@RequestBody Topic topic){
        if(topicService.checkTopicExistsById(topic.getEntityId())) {
            topicService.addTopic(topic);

            Link selfLink = linkTo(Topic.class).slash(topic.getEntityId()).withSelfRel();
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
            topic.setEntityId(id);
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
        String url = "http://dbpedia.org/";
        String encode = "sparql?default-graph-uri=http://dbpedia.org&query=PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
                "PREFIX : <http://dbpedia.org/resource/>\n" +
                "PREFIX dbpedia2: <http://dbpedia.org/property/>\n" +
                "PREFIX dbpedia: <http://dbpedia.org/>\n" +
                "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
                "SELECT DISTINCT ?type WHERE {\n" +
                "  ?entity rdf:type ?type .\n" +
                "  ?entity rdfs:label \""+label+"\"@en\n" +
                " }";

        encode = URLEncoder.encode(encode, "UTF-8");
        encode = encode.replace("%26query%3D", "&query=");

        return url+encode+"&output=json";
    }
}
