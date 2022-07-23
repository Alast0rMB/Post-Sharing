package example.website.DataAccess;

import example.website.Common.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);
    List<Tag> findTagsByPostsId(Long postId);

    boolean existsByName(String name);
}
