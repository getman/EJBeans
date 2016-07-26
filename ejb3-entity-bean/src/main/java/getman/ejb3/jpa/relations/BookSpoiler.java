package getman.ejb3.jpa.relations;

import javax.persistence.*;

/**
 * Created by Parfenov Artem on 29.06.2016.
 */
@Entity
@Table(name = "book_spoiler")
public class BookSpoiler {
    //------------Fields---------------
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="text")
    private String text;

    //unidirectional relation to BookEntity
    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "book_id")
    private BookEntity book;


    public BookSpoiler() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }
}
