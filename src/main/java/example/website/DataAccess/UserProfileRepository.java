package example.website.DataAccess;


import example.website.Common.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
    UserProfile findByUserId(Long id);
}
