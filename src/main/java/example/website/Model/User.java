package example.website.Model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @SequenceGenerator(name = "user_id_seq",sequenceName = "user_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_id_seq")
    private Long id;

    private String name;

    public User(){}
    public User(String name){this.name=name;}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
