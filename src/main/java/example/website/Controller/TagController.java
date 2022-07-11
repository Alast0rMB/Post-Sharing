package example.website.Controller;

import example.website.Model.Post;
import example.website.Model.Tag;
import example.website.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TagController {
    @Autowired
    private TagService service;

    @GetMapping("/tags")
    //Response Entity is used for REST API, you can simply return the List as a JSON body to.
    public ResponseEntity<List<Tag>> all(){
        List<Tag> tags = service.getAll();
        if(tags.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(tags,HttpStatus.OK);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> one(@PathVariable(name = "id")  Long id){
        Tag tag = service.getTag(id);
        return new ResponseEntity<>(tag,HttpStatus.OK );
    }

    @PostMapping("/tags")
    public ResponseEntity<Tag> create(@RequestBody Tag tag){
        Tag _tag = service.saveTag(tag);
        return new ResponseEntity<>(_tag,HttpStatus.CREATED);
    }

    @PutMapping("/tags/{id}")
    public ResponseEntity<Tag> one(@PathVariable(name = "id")  Long id,@RequestBody Tag tag){
        Tag _tag = service.updatePost(id,tag);
        return new ResponseEntity<>(tag,HttpStatus.OK );
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id")  Long id){
        service.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
