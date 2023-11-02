package et.com.qena.theater.services;

import et.com.qena.theater.dtos.requests.AddMovie;
import et.com.qena.theater.dtos.responses.MovieResponse;
import et.com.qena.theater.dtos.responses.MovieSearchResponse;
import et.com.qena.theater.utils.GenericResponse;

public interface  ITheaterService {
    GenericResponse<MovieResponse> addMovie(AddMovie request);

    MovieSearchResponse searchMovie(String title, String year, int page, int perPage);
}
