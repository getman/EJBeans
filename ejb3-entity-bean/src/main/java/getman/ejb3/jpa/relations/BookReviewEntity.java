package getman.ejb3.jpa.relations;

import javax.persistence.*;

/**
 * Created by Parfenov Artem on 27.06.2016.
 */

@Entity
@Table(name = "book_review")
public class BookReviewEntity {
    //------------Fields---------------
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int reviewId;
    @Column(name="text")
    private String reviewText;

    //many-to-one bidirectional relation with the book - one book may have many reviews
    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    //Mane-to-one bidirectional relation with the author - one author can write many reviews
    @ManyToOne
    @JoinColumn(name = "review_author_id")
    private AuthorEntity author;

    public BookReviewEntity() {}

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }
}
