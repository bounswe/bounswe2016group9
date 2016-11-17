package boun.cmpe451.group9.Controllers.Relation;

import boun.cmpe451.group9.Models.DB.Relation;
import boun.cmpe451.group9.Service.Relation.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping(value = "/relations")
public class RelationController {

    private RelationService relationService;

    @Autowired
    public void setRelationService(RelationService relationService){
        this.relationService = relationService;
    }

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
}
