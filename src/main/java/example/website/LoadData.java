package example.website;

import example.website.Model.*;
import example.website.Model.Enum.Gender;
import example.website.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadData {
    @Bean
    public CommandLineRunner initDataBase(UserProfileRepository profileRepository, UserRepository userRepository, PostRepository postRepository, TagRepository tagRepository, CommentRepository commentRepository){
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
            User use1 = new User("Arshia");
            User use2 = new User("Iliya");
            UserProfile userProfile1 = new UserProfile("address1", Gender.MALE);
            UserProfile userProfile2 = new UserProfile("address1", Gender.MALE);
            use1.setUserProfile(userProfile1);
            userProfile1.setUser(use1);
            use2.setUserProfile(userProfile2);
            userProfile2.setUser(use2);
            userRepository.save(use1);
            userRepository.save(use2);
            profileRepository.save(userProfile1);
            profileRepository.save(userProfile2);
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
