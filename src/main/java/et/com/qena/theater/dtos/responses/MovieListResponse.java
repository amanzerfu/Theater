package et.com.qena.theater.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieListResponse {
    public String movieId;
    public String title;
    public String year;
    public String type;
    public String poster;
}
