package example.website.Model;

import javax.persistence.*;

@Entity//Mapping the object to database
@Table(name = "posts")
public class Post {
    @Id
    @SequenceGenerator(name = "post_id_seq",sequenceName = "post_id_seq")
    @GeneratedValue (strategy = GenerationType.SEQUENCE,generator = "post_id_seq")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 120)
    private String description;

    public Post(){}
    public Post(String title,String description){
        this.description=description;
        this.title=title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
