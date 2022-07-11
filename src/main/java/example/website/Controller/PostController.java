package example.website.Controller;

import example.website.Model.Post;
import example.website.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService service;

    @GetMapping("/posts")
    //Response Entity is used for REST API, you can simply return the List as a JSON body to.
    public ResponseEntity<List<Post>> all(){
        List<Post> posts = service.getAll();
        if(posts.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> one(@PathVariable(name = "id")  Long id){
        Post post = service.getPost(id);
        return new ResponseEntity<>(post,HttpStatus.OK );
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post post){
        Post _post = service.savePost(post);
        return new ResponseEntity<>(_post,HttpStatus.CREATED);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> one(@PathVariable(name = "id")  Long id,@RequestBody Post post){
        Post _post = service.updatePost(id,post);
        return new ResponseEntity<>(post,HttpStatus.OK );
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id")  Long id){
        service.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
