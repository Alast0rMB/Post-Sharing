package example.website.Controller;

import example.website.Exception.ResourceNotFoundException;
import example.website.Model.Comment;
import example.website.Model.Post;
import example.website.Service.CommentService;
import example.website.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService service;

    @Autowired
    private PostService postService;

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> get(@PathVariable(name = "id")Long id){
        Comment comment = service.getById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<List<Comment>> getByPost(@PathVariable(name="id")Long id){
        if(!postService.existsById(id)){
            throw new ResourceNotFoundException("Not found Post with id = " + id);
        }
        List<Comment> comments = service.getByPost(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<Comment> add(@PathVariable(name = "id")Long id,@RequestBody Comment comment){
        Post post = postService.getPost(id);
        comment.setPost(post);
        return new ResponseEntity<>(service.save(comment),HttpStatus.CREATED);
    }


    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id")Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
