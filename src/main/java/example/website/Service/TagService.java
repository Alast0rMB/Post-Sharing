package example.website.Service;

import example.website.Exception.ResourceNotFoundException;
import example.website.Model.Post;
import example.website.Model.Tag;
import example.website.Repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository repository;

    //Returns the list of all posts
    public List<Tag> getAll(){
        return repository.findAll();
    }

    //Returns a post by its id
    public Tag getTag(Long id){
        return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found Tag with id = " + id));
    }

    //Adds a new post to the
    public Tag saveTag(Tag tag){
        return repository.save(tag);
    }

    //Updates the new post based on given info
    public Tag updatePost(Long id, Tag _tag){
        Tag tag = getTag(id);
        tag.setName(_tag.getName());
        return saveTag(tag);
    }

    //Delete a post from database
    public void deleteTag(Long id){
        Tag tag = getTag(id);
        repository.delete(tag);
    }
}
