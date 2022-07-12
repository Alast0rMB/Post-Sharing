package example.website.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity//Mapping the object to database
@Table(name = "posts")
public class Post extends AuditModel {
    @Id
    @SequenceGenerator(name = "post_id_seq",sequenceName = "post_id_seq")
    @GeneratedValue (strategy = GenerationType.SEQUENCE,generator = "post_id_seq")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 120)
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany(fetch = FetchType.LAZY,
                cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "post_tag",//The owner of the relationship
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    //Since we are the owner of the relation, if we update then we have to update owr tag
    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getPosts().add(this);
    }

    public void removeTag(long tagId) {
        Tag tag = this.tags.stream().filter(t -> t.getId() == tagId).findFirst().orElse(null);
        if (tag != null) {
            this.tags.remove(tag);
            tag.getPosts().remove(this);
        }
    }

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
