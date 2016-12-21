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

    /**
     * Gets semantic tag with the given id
     * @param id id of the semantic tag
     * @return semantic tag with the given id
     */
    @GetMapping("{id}")
    public ResponseEntity<SemanticTag> getSTag(@PathVariable("id") long id){
        if(semanticTagService.checkIfEntityExistsById(id)){
            SemanticTag semanticTag = addLinksToSTag(semanticTagService.getById(id));

            return new ResponseEntity<>(semanticTag, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Adds semantic tag
     * @param semanticTag semantic tag to be added
     * @return response for the add request
     */
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

    /**
     * Updates semantic tag with the given id
     * @param id semantic tag to be updated
     * @param semanticTag semantic tag with the new values
     * @return response for the update request
     */
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

    /**
     * Deletes semantic tag with the given id
     * @param id id of the deleted semantic tag
     * @return response for the removal request
     */
    @DeleteMapping("{id}")
    public ResponseEntity<SemanticTag> deleteSTagById(@PathVariable("id") long id){
        if(semanticTagService.checkIfEntityExistsById(id)){
            semanticTagService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves all tags
     * @return all tags available
     */
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

    /**
     * Adds HATEOAS links to SemanticTag entity
     * @param semanticTag entity that links are added
     * @return entity with links
     */
    public static SemanticTag addLinksToSTag(SemanticTag semanticTag){
        semanticTag.add(linkTo(SemanticTagController.class).slash(semanticTag.getEntityId()).withSelfRel());
        return semanticTag;
    }
}
