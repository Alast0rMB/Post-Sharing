package example.website.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @SequenceGenerator(name = "tag_id_seq",sequenceName = "tag_id_seq")
    @GeneratedValue (strategy = GenerationType.SEQUENCE,generator = "tag_id_seq")
    private long id;

    @NaturalId
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
                cascade = {CascadeType.MERGE,CascadeType.PERSIST},
            mappedBy = "tags")
    @JsonIgnore//ignore the logical property used in serialization and deserialization
    private Set<Post> posts = new HashSet<>();

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tag(){}
    public Tag(String name){this.name=name;}

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
