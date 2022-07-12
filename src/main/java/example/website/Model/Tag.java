package example.website.Model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

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

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return id == tag.id || name.equals(tag.name); //They're the same if name or id are the same
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
