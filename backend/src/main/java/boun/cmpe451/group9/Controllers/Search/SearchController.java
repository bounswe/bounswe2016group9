package boun.cmpe451.group9.Controllers.Search;

import boun.cmpe451.group9.Service.Search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

    private SearchService searchService;

    @Autowired
    public void setSearchService(SearchService searchService){ this.searchService = searchService; }

    @GetMapping
    public ResponseEntity searchEntity(@RequestParam("keywords") String keywords, @RequestParam("type") String type){
        switch (type) {
            case "topic":
                return new ResponseEntity<>(searchService.searchTopic(keywords), HttpStatus.OK);
            case "post":
                return new ResponseEntity<>(searchService.searchPost(keywords), HttpStatus.OK);
            default:
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
