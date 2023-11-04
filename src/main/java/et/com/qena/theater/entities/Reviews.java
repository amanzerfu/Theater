package et.com.qena.theater.entities;

import et.com.qena.theater.dtos.requests.AddReviews;
import et.com.qena.theater.utils.DateUtils;
import et.com.qena.theater.utils.MultipleUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_reviews")
public class Reviews implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "review_id")
    private String reviewId;
    @Column(name = "movie_id")
    private String movieId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "rating")
    private Float rating;
    @Column(name = "comment")
    private String comment;
    @Column(name = "created_at")
    private Timestamp createdAt;

    public Reviews(AddReviews request) {
        this.reviewId = MultipleUtils.randomGenerateId();
        this.movieId = request.getMovieId();
        this.userId = request.getUserId();
        this.rating = request.getRating();
        this.comment = request.getComment();
        this.createdAt = DateUtils.getCurrentTimeStamp();
    }
}
