package et.com.qena.theater.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieGetResponse {
    private String status;
    private String movieId;
    private String Title;
    private String Year;
    private String Type;
    private String Poster;
}
