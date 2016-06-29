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
}
