package example.website.Repository;

import example.website.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findPostsByTagsId(Long tagId);
    List<Post> findByUserId(Long userId);

}
