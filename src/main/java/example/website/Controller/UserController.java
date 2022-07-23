package example.website.Controller;

import example.website.Exception.ResourceNotFoundException;
import example.website.Entity.Post;
import example.website.Entity.User;
import example.website.Entity.UserProfile;
import example.website.Service.PostService;
import example.website.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private PostService postService;
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll(){
        List<User> users = service.getAll();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable(name = "id")Long id){
        User user = service.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/{id}/profile")
    public ResponseEntity<UserProfile> getByPost(@PathVariable(name="id")Long id){
        if(!service.existsById(id)){
            throw new ResourceNotFoundException("Not found Post with id = " + id);
        }
        User user = service.getById(id);
        return new ResponseEntity<>(user.getUserProfile(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> add(@RequestBody User user){
        UserProfile userProfile = new UserProfile();
        user.setUserProfile(userProfile);
        return new ResponseEntity<>(service.save(user),HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,@RequestBody User user){
        user = service.update(id,user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}/profile")
    public ResponseEntity<UserProfile> update(@PathVariable Long id,@RequestBody UserProfile userprof){
        userprof = service.updateProfile(id,userprof);
        return new ResponseEntity<>(userprof,HttpStatus.CREATED);
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id")Long id){
        List<Post> posts = postService.getByUserId(id);
        for(Post post: posts)
            postService.deletePost(post.getId());
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
