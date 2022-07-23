package example.website.Service;

import example.website.Exception.ResourceNotFoundException;
import example.website.Entity.Comment;
import example.website.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    public boolean existsById(Long id){
        return repository.existsById(id);
    }

    //Returns the list of all posts
    public List<Comment> getAll(){
        return repository.findAll();
    }

    //Returns a post by its id
    public Comment getById(Long id){
        return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found Comment with id = " + id));
    }

    //Adds a new post to the
    public Comment save(Comment comment){
        return repository.save(comment);
    }

    //Updates the new post based on given info
    public Comment update(Long id, Comment newComment){
        Comment comment = getById(id);
        comment.setContent(newComment.getContent());
        return save(comment);
    }

    //Delete a post from database
    public void delete(Long id){
        Comment comment = getById(id);
        repository.delete(comment);
    }

    public List<Comment> getByPost(Long postId){
        return repository.findByPostId(postId);
    }

}
