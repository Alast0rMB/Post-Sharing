package example.website.Common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import example.website.Common.Enum.Gender;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @SequenceGenerator(name = "userProf_id_seq",sequenceName = "userProf_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userProf_id_seq")
    private Long id;

    private String address;
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dobDate;

    @Transient
    @JsonProperty
    private Integer age;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserProfile(){this.gender=Gender.UNDEFINDE;this.age=getAge();}
    public UserProfile(String address,Gender gender,Date dob){
        this.address = address;
        this.gender = gender;
        this.dobDate = dob;
        this.age = getAge();
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

    public Date getDobDate() {
        return dobDate;
    }

    public void setDobDate(Date dobDate) {
        this.dobDate = dobDate;
    }

    protected int getAge(){
        if(dobDate == null)
           return  0;
        else
            return Period.between(dobDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),LocalDate.now()).getYears();
    }

    protected void setAge(){
        this.age = getAge();
    }
}
