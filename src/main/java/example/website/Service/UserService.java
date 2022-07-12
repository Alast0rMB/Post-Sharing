package example.website.Service;

import example.website.Exception.ResourceNotFoundException;
import example.website.Model.User;
import example.website.Model.UserProfile;
import example.website.Repository.UserProfileRepository;
import example.website.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserProfileRepository profileRepository;

    public boolean existsById(Long id){
        return repository.existsById(id);
    }

    //Returns the list of all posts
    public List<User> getAll(){
        return repository.findAll();
    }

    //Returns a post by its id
    public User getById(Long id){
        return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found User with id = " + id));
    }

    //Adds a new post to the
    public UserProfile save(User user){
        repository.save(user);
        return profileRepository.save(user.getUserProfile());
    }

    //Updates the new post based on given info
    public User update(Long id, User newUser){
        User user = getById(id);
        user.setName(newUser.getName());
        return save(user).getUser();
    }

    //Delete a post from database
    public void delete(Long id){
        User user = getById(id);
        repository.delete(user);
    }

    public UserProfile getProfile(Long userId){
        return profileRepository.findByUserId(userId);
    }

    public UserProfile updateProfile(Long userId,UserProfile profile){
        if(!existsById(userId))
           throw new ResourceNotFoundException("Not found User with id = " + userId);
        UserProfile userProfile = getProfile(userId);
        userProfile.setAddress(profile.getAddress());
        userProfile.setGender(profile.getGender());
        repository.save(userProfile.getUser());
        return profileRepository.save(userProfile); //Also save the user(child)
    }
}
