package example.website.Common;

import com.fasterxml.jackson.annotation.JsonProperty;
import example.website.Common.Enum.Gender;

import javax.persistence.*;
import java.util.Date;

@Embeddable
public class UserProfile {

    private String address;
    private Gender gender;
    private Date dobDate;

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
}
