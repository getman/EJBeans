package getman.ejb3.jpa.relations;

import javax.persistence.*;

/**
 * Created by Parfenov Artem on 28.06.2016.
 */
@Entity
@Table(name="book_isbn")
public class ISBN {
    //------------Fields---------------
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int isbnId;
    @Column(name="number")
    private String isbnNumber;


//    @OneToOne(cascade = {CascadeType.REMOVE})
//    @JoinColumn(name = "isbn_id")
//    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "book_id")
    //bidirectional relation from BookEntity
    @OneToOne(mappedBy = "isbn", cascade = {CascadeType.REMOVE})
    private BookEntity book;

    public ISBN() {}

    public int getIsbnId() {
        return isbnId;
    }

    public void setIsbnId(int isbnId) {
        this.isbnId = isbnId;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }
}
