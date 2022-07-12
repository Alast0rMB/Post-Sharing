package example.website.Controller;

import example.website.Model.Post;
import example.website.Model.User;
import example.website.Service.PostService;
import example.website.Service.UserService;
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
    @Autowired
    private UserService userService;
    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> getByUser(@PathVariable Long id){
        List<Post> posts = service.getByUserId(id);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

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
    public ResponseEntity<Post> create(@RequestBody Post post,@RequestParam(name = "user",defaultValue = "1")Long userId){
        User user = userService.getById(userId);
        post.setUser(user);
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
