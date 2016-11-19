package boun.cmpe451.group9.Controllers.Tag;

import boun.cmpe451.group9.Models.DB.Tag;
import boun.cmpe451.group9.Service.Tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("/tags")
public class TagController {

    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService){
        this.tagService = tagService;
    }

    /**
     * Retrieves tag "id"
     * @param id id of the tag
     * @return tag "id"
     */
    @GetMapping("{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable("id") long id){
        if(tagService.checkIfEntityExistsById(id)){
            Tag tag = addLinksToTag(tagService.getById(id));

            return new ResponseEntity<>(tag, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Adds a new tag
     * @param tag tag we want to add
     * @return tag we just added
     */
    @PostMapping
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag){
        if(!tagService.checkIfTagExistsByName(tag.getName())){
            tagService.save(tag);

            tag = addLinksToTag(tag);

            return new ResponseEntity<>(tag, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Updates the tag "id"
     * @param id id of the tag
     * @param tag the updated tag
     * @return tag we just updated
     */
    @PutMapping("{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable("id") long id, @RequestBody Tag tag){
        if(tagService.checkIfEntityExistsById(id)){
            tag.setEntityId(id);
            tagService.save(tag);

            tag = addLinksToTag(tag);

            return new ResponseEntity<>(tag, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Removes the tag "id"
     * @param id id of the tag
     * @return NO_CONTENT if tag is deleted, NOT_FOUND if it is not found
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Tag> deleteTagById(@PathVariable("id") long id){
        if(tagService.checkIfEntityExistsById(id)){
            tagService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags(){
        List<Tag> tags = tagService.findAll();

        if(tags.size() > 0) {
            tags.forEach(TagController::addLinksToTag);

            return new ResponseEntity<>(tags, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public static Tag addLinksToTag(Tag tag){
        tag.add(linkTo(TagController.class).slash(tag.getEntityId()).withSelfRel());
        return tag;
    }
}
