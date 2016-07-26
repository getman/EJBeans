package getman.ejb3.jpa.relations;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Parfenov Artem on 27.06.2016.
 */

@Entity
@Table(name="books")
public class BookEntity {
    //------------Fields---------------
    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int bookId;
    @Column(name="name")
    private String bookName;
    @Column(name="type")
    private String bookType;

//    @PrimaryKeyJoinColumn(name = "book_id", referencedColumnName = "id")

    //bidirectional one-to-one relation to ISBN
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "isbn_id")
    private ISBN isbn;

    //unidirectional one-to-one relation from BookSpoiler
    @OneToOne(mappedBy = "book", cascade = {CascadeType.REMOVE})
    private BookSpoiler spoiler;

    //bidirectional one-to-many relation with - one book may have few reviews
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    private Collection<BookReviewEntity> bookReviews;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="author_book",
            joinColumns=@JoinColumn(name="book_id", referencedColumnName="book_id"),
            inverseJoinColumns=@JoinColumn(name="author_id", referencedColumnName="id"))
    private Set<AuthorEntity> authorList;

    public BookEntity() {
        bookReviews = new ArrayList<BookReviewEntity>();
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public void setIsbn(ISBN isbn) {
        this.isbn = isbn;
    }

    public BookSpoiler getSpoiler() {
        return spoiler;
    }

    public void setSpoiler(BookSpoiler spoiler) {
        this.spoiler = spoiler;
    }

    public Collection<BookReviewEntity> getBookReviews() {
        return bookReviews;
    }

    public void setBookReviews(Collection<BookReviewEntity> bookReviews) {
        this.bookReviews = bookReviews;
    }

    public Set<AuthorEntity> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(Set<AuthorEntity> authorList) {
        this.authorList = authorList;
    }
}
