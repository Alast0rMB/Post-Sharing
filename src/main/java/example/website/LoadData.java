package example.website;

import example.website.Model.Comment;
import example.website.Model.Post;
import example.website.Model.Tag;
import example.website.Repository.CommentRepository;
import example.website.Repository.PostRepository;
import example.website.Repository.TagRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadData {
    @Bean
    public CommandLineRunner initDataBase(PostRepository postRepository, TagRepository tagRepository, CommentRepository commentRepository){
        return args -> {
            //Creating Sample Posts
            Post postOne = new Post("Sample Post 1","This is a new post");
            Post postTwo = new Post("Good Post","A very very good post");
            Tag tagOne = new Tag("Tag_one");
            Tag tagTwo = new Tag("Tag_two");
            postOne.addTag(tagOne);
            postTwo.addTag(tagOne);
            postTwo.addTag(tagTwo);
            Comment c1 = new Comment("Comment for first");
            Comment c2 = new Comment("Comment for Second");
            Comment c11 = new Comment("Another comment for first");
            Comment c22 = new Comment("Comment for second2");
            c1.setPost(postOne);
            c11.setPost(postOne);
            c2.setPost(postTwo);
            c22.setPost(postTwo);
            postRepository.save(postOne);
            postRepository.save(postTwo);
            tagRepository.save(tagOne);
            tagRepository.save(tagTwo);
            commentRepository.save(c1);
            commentRepository.save(c11);
            commentRepository.save(c2);
            commentRepository.save(c22);
        };
    }
}
