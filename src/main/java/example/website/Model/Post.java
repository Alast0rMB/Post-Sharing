package example.website.Model;

import javax.persistence.*;

@Entity//Mapping the object to database
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 120)
    private String description;
}
