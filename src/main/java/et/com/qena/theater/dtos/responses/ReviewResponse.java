package et.com.qena.theater.dtos.responses;

import et.com.qena.theater.entities.Reviews;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private String movieId;
    private String userId;
    private float rating;
    private String comment;

    public ReviewResponse(Reviews reviews) {
        this.movieId = reviews.getMovieId();
        this.userId = reviews.getUserId();
        this.rating = reviews.getRating();
        this.comment = reviews.getComment();
    }
}
