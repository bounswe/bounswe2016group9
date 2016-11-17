package boun.cmpe451.group9.Controllers.SemanticTag;

import boun.cmpe451.group9.Models.DB.SemanticTag;
import boun.cmpe451.group9.Service.SemanticTag.SemanticTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("/semanticTags")
public class SemanticTagController {

    private SemanticTagService semanticTagService;

    @Autowired
    public void setSemanticTagService(SemanticTagService semanticTagService){
        this.semanticTagService = semanticTagService;
    }

    @GetMapping("{id}")
    public ResponseEntity<SemanticTag> getSTag(@PathVariable("id") long id){
        if(semanticTagService.checkIfEntityExistsById(id)){
            SemanticTag semanticTag = addLinksToSTag(semanticTagService.getById(id));

            return new ResponseEntity<>(semanticTag, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<SemanticTag> addSTag(@RequestBody SemanticTag semanticTag){
        if(!semanticTagService.checkExistenceByName(semanticTag.getType())){
            semanticTagService.save(semanticTag);

            semanticTag = addLinksToSTag(semanticTag);

            return new ResponseEntity<>(semanticTag, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<SemanticTag> updateSTag(@PathVariable("id") long id, @RequestBody SemanticTag semanticTag){
        if(semanticTagService.checkIfEntityExistsById(id)){
            semanticTag.setEntityId(id);
            semanticTagService.save(semanticTag);

            return new ResponseEntity<>(semanticTag, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<SemanticTag> deleteSTagById(@PathVariable("id") long id){
        if(semanticTagService.checkIfEntityExistsById(id)){
            semanticTagService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<SemanticTag>> getAllSTags(){
        List<SemanticTag> semanticTags = semanticTagService.findAll();

        if(!semanticTags.isEmpty()){
            semanticTags.forEach(SemanticTagController::addLinksToSTag);

            return new ResponseEntity<>(semanticTags, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public static SemanticTag addLinksToSTag(SemanticTag semanticTag){
        semanticTag.add(linkTo(SemanticTagController.class).slash(semanticTag.getEntityId()).withSelfRel());
        return semanticTag;
    }
}
