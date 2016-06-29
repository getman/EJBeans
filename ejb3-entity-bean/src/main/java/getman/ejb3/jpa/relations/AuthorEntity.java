package getman.ejb3.jpa.relations;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Parfenov Artem on 27.06.2016.
 */
@Entity
@Table(name="author")
@SqlResultSetMapping(name="SelectAuthors",
        classes={
                @ConstructorResult(
                        targetClass=AuthorEntity.class,
                        columns={
                                @ColumnResult(name="id"),
                                @ColumnResult(name="name"),
                                @ColumnResult(name="surname")})
        })
public class AuthorEntity {
    //------------Fields---------------
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int authorId;
    @Column(name="name")
    private String authorName;
    @Column(name="surname")
    private String authorSurname;

    public AuthorEntity(){}

    public AuthorEntity(int authorId, String authorName, String authorSurname){
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }
}
