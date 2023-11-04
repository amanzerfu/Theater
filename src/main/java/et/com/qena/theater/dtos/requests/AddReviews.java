package et.com.qena.theater.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddReviews {
    private String movieId;
    private String userId;
    private float rating;
    private String comment;
}
