package example.website.Controller;

import example.website.Entity.Post;
import example.website.Entity.Tag;
import example.website.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TagController {
    @Autowired
    private TagService service;

    @GetMapping("/tags")
    //Response Entity is used for REST API, you can simply return the List as a JSON body to.
    public ResponseEntity<List<Tag>> all(@RequestParam(value = "name",required = false,defaultValue = "null")String name){
        List<Tag> tags = new ArrayList<>(); //Our return list
        if(name != null && !name.equals("null")){ //If there is a name given then get using name
            tags.add(service.getTagByName(name));
        }
        else { //Otherwise get all the tags
            tags = service.getAll();
        }
        if(tags.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(tags,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/tags")
    public ResponseEntity<List<Tag>> tagsByPost(@PathVariable (name = "postId")Long postId){
        List<Tag> tags = service.getTagByPost(postId);
        return new ResponseEntity<>(tags,HttpStatus.OK);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> one(@PathVariable(name = "id")  Long id){
        Tag tag = service.getTag(id);
        return new ResponseEntity<>(tag,HttpStatus.OK );
    }

    @GetMapping("/tags/{id}/posts")
    public ResponseEntity<List<Post>> postByTag(@PathVariable (name = "id")Long id){
        List<Post> post = service.getPostByTag(id);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @PostMapping("/tags")
    public ResponseEntity<Tag> create(@RequestBody Tag tag){
        Tag _tag = service.saveTag(tag);
        return new ResponseEntity<>(_tag,HttpStatus.CREATED);
    }

    @PostMapping("/posts/{id}/tags")
    public ResponseEntity<Tag> addTagToPost(@PathVariable(name = "id")Long postId,@RequestBody Tag _tag){
        Tag tag = service.addTagToPost(postId,_tag);
        return new ResponseEntity<>(tag,HttpStatus.CREATED);
    }

    @PutMapping("/tags/{id}")
    public ResponseEntity<Tag> one(@PathVariable(name = "id")  Long id,@RequestBody Tag tag){
        Tag _tag = service.updatePost(id,tag);
        return new ResponseEntity<>(tag,HttpStatus.OK );
    }

    //When deleting a child entity, we must remove all of its association with other objects
    //Otherwise you'll get a query error
    @DeleteMapping("/tags/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id")  Long id){
        List<Post> posts = service.getPostByTag(id);
        for(Post post : posts){
            post.removeTag(id);
            service.savePost(post);
        }
        service.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/posts/{postId}/tags/{tagId}")
    public ResponseEntity<HttpStatus> deleteTagPost(@PathVariable(name = "postId")Long postId,@PathVariable(name = "tagId")Long tagId){
        Post post = service.getPost(postId);
        post.removeTag(tagId);
        service.savePost(post);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
