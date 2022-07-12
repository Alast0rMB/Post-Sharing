package example.website;

import example.website.Model.Post;
import example.website.Model.Tag;
import example.website.Repository.PostRepository;
import example.website.Repository.TagRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadData {
    @Bean
    public CommandLineRunner initDataBase(PostRepository postRepository, TagRepository tagRepository){
        return args -> {
            //Creating Sample Posts
            Post postOne = new Post("Sample Post 1","This is a new post");
            Post postTwo = new Post("Good Post","A very very good post");
            Tag tagOne = new Tag("Tag_one");
            Tag tagTwo = new Tag("Tag_two");
            //postOne.addTag(tagOne);
            //postTwo.addTag(tagTwo);
            postRepository.save(postOne);
            postRepository.save(postTwo);
            tagRepository.save(tagOne);
            tagRepository.save(tagTwo);
        };
    }
}
