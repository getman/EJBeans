package getman.ejb3.jpa.relations;

import javax.persistence.*;

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

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    @JoinColumn(name = "isbn_id")
    @PrimaryKeyJoinColumn(name = "book_id", referencedColumnName = "id")
    private ISBN isbn;

    public BookEntity() {}

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
}
