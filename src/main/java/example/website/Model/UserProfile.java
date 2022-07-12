package example.website.Model;

import example.website.Model.Enum.Gender;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @SequenceGenerator(name = "userProf_id_seq",sequenceName = "userpProf_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userProf_id_seq")
    private Long id;

    private String address;
    private Gender gender;

    public UserProfile(){this.gender=Gender.UNDEFINDE;}
    public UserProfile(String address,Gender gender){
        this.address = address;
        this.gender = gender;
    }

    public Long getId(){
        return this.id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}