package et.com.qena.theater.dtos.responses;

import et.com.qena.theater.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieListResponse {
    public String movieId;
    public String Title;
    public String Year;
    public String Type;
    public String Poster;

    public MovieListResponse(Movie movie) {
        this.movieId = movie.getMovieId();
        Title = movie.getTitle();
        Year = movie.getYear();
        Type = movie.getType();
        Poster = movie.getPoster();
    }
}
