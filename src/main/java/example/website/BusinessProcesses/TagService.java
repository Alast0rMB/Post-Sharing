package example.website.BusinessProcesses;

import example.website.Exception.ResourceNotFoundException;
import example.website.Common.Post;
import example.website.Common.Tag;
import example.website.DataAccess.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository repository;

    @Autowired
    private PostService postService;

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

    public Tag getTagByName(String name){
        Tag tag = repository.findByName(name);
        if(tag == null) {
            throw new ResourceNotFoundException("Not found Tag with name:"+name);
        }
        return tag;
    }


    public Post getPost(Long postId){
        return postService.getPost(postId);
    }

    public Post savePost(Post post){
        return postService.savePost(post);
    }
    public Tag addTagToPost(Long postId,Tag _tag){
        Post post = getPost(postId);
        //Check if the tag exists
        if(repository.existsByName(_tag.getName())){
            _tag = getTagByName(_tag.getName());
            post.addTag(_tag);
            postService.savePost(post);
        //Must create new tag
        }else{
            post.addTag(_tag);
            repository.save(_tag);
        }
        return _tag;
    }

    public List<Tag> getTagByPost(Long postId){
        if(!postService.existsById(postId)){
            throw new ResourceNotFoundException("Not found Post with id:"+postId);
        }else{
            return repository.findTagsByPostsId(postId);
        }
    }

    public List<Post> getPostByTag(Long tagId){
        if(!repository.existsById(tagId)){
            throw new ResourceNotFoundException("Not found Tag with id:"+tagId);
        }else{
            return postService.getPostByTag(tagId);
        }
    }

    //Delete a post from database
    public void deleteTag(Long id){
        Tag tag = getTag(id);
        repository.delete(tag);
    }
}
