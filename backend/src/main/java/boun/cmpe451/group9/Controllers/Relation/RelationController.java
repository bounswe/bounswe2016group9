package boun.cmpe451.group9.Controllers.Relation;

import boun.cmpe451.group9.Models.DB.Relation;
import boun.cmpe451.group9.Models.DB.RelationType;
import boun.cmpe451.group9.Models.Meta.SPARQLRelationTypeResponse;
import boun.cmpe451.group9.Service.Relation.RelationService;
import boun.cmpe451.group9.Service.RelationType.RelationTypeService;
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

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping(value = "/relations")
public class RelationController {

    private RelationService relationService;
    private RelationTypeService relationTypeService;

    @Autowired
    public void setRelationService(RelationService relationService){
        this.relationService = relationService;
    }

    @Autowired
    public void setRelationTypeService(RelationTypeService relationTypeService){this.relationTypeService=relationTypeService;}
    /**
     * Retrieves the relation "id"
     * @param id id of the relation
     * @return relation "id"
     */
    @GetMapping("{id}")
    public ResponseEntity<Relation> getRelation(@PathVariable("id") long id){
        if(relationService.checkIfEntityExistsById(id)){
            Relation relation = addLinksToRelation(relationService.getById(id));

            return new ResponseEntity<>(relation, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Adds a new relation
     * @param relation relation we want to add
     * @return relation we just added
     */
    @PostMapping
    public ResponseEntity<Relation> addRelation(@RequestBody Relation relation){
        if(!relationService.checkIfRelationExistsByTopicIds(relation.getFromTopic().getEntityId(), relation.getToTopic().getEntityId())){
            relationService.save(relation);

            relation = addLinksToRelation(relation);

            return new ResponseEntity<>(relation, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Updates the relation
     * @param id id of the relation
     * @param relation the updated relation
     * @return the updated relation
     */
    @PutMapping("{id}")
    public ResponseEntity<Relation> updateRelation(@PathVariable("id") long id, @RequestBody Relation relation){
        if(relationService.checkIfEntityExistsById(id)){
            relation.setEntityId(id);
            relationService.save(relation);

            relation = addLinksToRelation(relation);

            return new ResponseEntity<>(relation, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Relation> deleteRelation(@PathVariable("id") long id){
        if(relationService.checkIfEntityExistsById(id)){
            relationService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Relation>> getAllRelations(){
        List<Relation> relations = relationService.findAll();

        if (!relations.isEmpty()){
            relations.forEach(RelationController::addLinksToRelation);

            return new ResponseEntity<>(relations, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public static Relation addLinksToRelation(Relation relation){
        relation.add(linkTo(RelationController.class).slash(relation.getEntityId()).withSelfRel());
        return relation;
    }

    @GetMapping("/setRelationTypes")
    public ResponseEntity<List<RelationType>> addRelationTypes() throws Exception {
        URL url = new URL(createUrlForSPARQLRelationTypes());
        ObjectMapper mapper = new ObjectMapper();
        SPARQLRelationTypeResponse response = mapper.readValue(url, SPARQLRelationTypeResponse.class);
        List<SPARQLRelationTypeResponse.Results.Labels> labels = response.getResults().getBindings();

        List<RelationType> relationTypes = new LinkedList<>();

        for(SPARQLRelationTypeResponse.Results.Labels label : labels){
            RelationType relationType = new RelationType();
            relationType.setType(label.getLabel().getValue());
            relationTypeService.save(relationType);
            relationTypes.add(relationType);
        }



        return new ResponseEntity<>(relationTypes,HttpStatus.OK);

    }
    /**
     * Creates url for getting relation types from DBPedia
     * @return all relation types existing in dbpedia/ObjectProperty
     * @throws Exception IOException
     */

    private String createUrlForSPARQLRelationTypes() throws Exception{
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
                "select distinct ?label where {\n" +
                "  ?entity a owl:ObjectProperty .\n" +
                "  ?entity rdfs:label ?label .\n" +
                "  filter(langMatches(lang(?label),\"en\"))\n"+
                " }";

        encode = URLEncoder.encode(encode, "UTF-8");
        encode = encode.replace("%26query%3D", "&query=");

        return url+encode+"&output=json";
    }


}
