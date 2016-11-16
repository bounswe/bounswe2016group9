package boun.cmpe451.group9.Controllers.Tag;

import boun.cmpe451.group9.Models.DB.Tag;
import boun.cmpe451.group9.Service.Tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/tags")
public class TagController {

    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService){
        this.tagService = tagService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable("id") long id){
        if(tagService.checkIfTagExistsById(id)){

            Tag tag = tagService.getTagById(id);
            tag.add(linkTo(TagController.class).slash(id).withSelfRel());

            return new ResponseEntity<>(tag, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag){
        if(!tagService.checkIfTagExistsByName(tag.getName())){
            tagService.addTag(tag);
            tag.add(linkTo(TagController.class).slash(tag.getEntityId()).withSelfRel());

            return new ResponseEntity<>(tag, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable("id") long id, @RequestBody Tag tag){
        if(tagService.checkIfTagExistsById(id)){
            tag.setEntityId(id);
            tagService.updateTag(tag);

            tag.add(linkTo(TagController.class).slash(id).withSelfRel());

            return new ResponseEntity<>(tag, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Tag> deleteTagById(@PathVariable("id") long id){
        if(tagService.checkIfTagExistsById(id)){
            tagService.removeTag(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
