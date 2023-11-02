package et.com.qena.theater.dtos.responses;

import et.com.qena.theater.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MovieSearchResponse {
    private String status;
    private int totalResult;
    private int page;
    private int per_page;
    private List<Movie> movieList;

    public MovieSearchResponse(String status, int totalResult, int page, int per_page,List<Movie> movieList) {
        this.status = status;
        this.totalResult = totalResult;
        this.page = page;
        this.per_page = per_page;
        this.movieList = movieList;
    }
}
