package example.website.Controller;

import example.website.Exception.ResourceNotFoundException;
import example.website.Model.Comment;
import example.website.Model.Post;
import example.website.Model.User;
import example.website.Model.UserProfile;
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
        UserProfile userProfile = service.getProfile(id);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> add(@RequestBody User user){
        UserProfile userProfile = new UserProfile();
        user.setUserProfile(userProfile);
        userProfile.setUser(user);
        return new ResponseEntity<>(service.save(user).getUser(),HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,@RequestBody User user){
        user = service.update(id,user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}/profile")
    public ResponseEntity<UserProfile> update(@PathVariable Long id,@RequestBody UserProfile user){
        user = service.updateProfile(id,user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id")Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
