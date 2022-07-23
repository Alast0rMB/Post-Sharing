package example.website.BusinessProcesses;

import example.website.Exception.ResourceNotFoundException;
import example.website.Common.User;
import example.website.Common.UserProfile;
import example.website.DataAccess.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
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
    public User save(User user){
        user.setUserProfile(new UserProfile());
        return repository.save(user);
    }

    //Updates the new post based on given info
    public User update(Long id, User newUser){
        User user = getById(id);
        user.setName(newUser.getName());
        return save(user);
    }

    //Delete a post from database
    public void delete(Long id){
        User user = getById(id);
        repository.delete(user);
    }

    public UserProfile getProfile(Long userId){
        return getById(userId).getUserProfile();
    }

    public UserProfile updateProfile(Long userId,UserProfile profile){
        if(!existsById(userId))
           throw new ResourceNotFoundException("Not found User with id = " + userId);
        User user = getById(userId);
        UserProfile userProfile = getProfile(userId);
        userProfile.setAddress(profile.getAddress());
        userProfile.setGender(profile.getGender());
        return repository.save(user).getUserProfile(); //Also save the user(child)
    }
}
