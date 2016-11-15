package boun.cmpe451.group9.Controllers.Relation;

import boun.cmpe451.group9.Models.DB.Relation;
import boun.cmpe451.group9.Service.Relation.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/relations")
public class RelationController {

    private RelationService relationService;

    @Autowired
    public void setRelationService(RelationService relationService){
        this.relationService = relationService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Relation> getRelation(@PathVariable("id") long id){
        if(relationService.checkIfRelationExistsById(id)){
            Relation relation = relationService.getRelationById(id);

            Link selfLink = linkTo(Relation.class).slash(id).withSelfRel();
            relation.add(selfLink);

            return new ResponseEntity<>(relation, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Relation> addRelation(@RequestBody Relation relation){
        if(relationService.checkIfRelationExistsByTopicIds(relation.getFromTopic().getEntityId(), relation.getToTopic().getEntityId())){
            relationService.addRelation(relation);

            Link selfLink = linkTo(Relation.class).slash(relation.getEntityId()).withSelfRel();
            relation.add(selfLink);

            return new ResponseEntity<>(relation, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Relation> updateRelation(@PathVariable("id") long id, @RequestBody Relation relation){
        if(relationService.checkIfRelationExistsById(id)){
            relation.setEntityId(id);
            relationService.updateRelation(relation);

            Link selfLink = linkTo(Relation.class).slash(id).withSelfRel();
            relation.add(selfLink);

            return new ResponseEntity<>(relation, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Relation> deleteRelation(@PathVariable("id") long id){
        if(relationService.checkIfRelationExistsById(id)){
            relationService.removeRelation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
