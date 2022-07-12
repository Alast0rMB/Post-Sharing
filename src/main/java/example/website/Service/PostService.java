package example.website.Service;

import example.website.Exception.ResourceNotFoundException;
import example.website.Model.Post;
import example.website.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public boolean existsById(Long id){
        return repository.existsById(id);
    }

    //Returns the list of all posts
    public List<Post> getAll(){
        return repository.findAll();
    }

    //Returns a post by its id
    public Post getPost(Long id){
        return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found Post with id = " + id));
    }

    //Adds a new post to the
    public Post savePost(Post post){
        return repository.save(post);
    }

    //Updates the new post based on given info
    public Post updatePost(Long id, Post newPost){
        Post post = getPost(id);
        post.setDescription(newPost.getDescription());
        post.setTitle(newPost.getTitle());
        return savePost(post);
    }

    //Delete a post from database
    public void deletePost(Long id){
        Post post = getPost(id);
        repository.delete(post);
    }

    public List<Post> getPostByTag(Long tagId){
        return repository.findPostsByTagsId(tagId);
    }
}
