package et.com.qena.theater.implementations;

import et.com.qena.theater.dtos.requests.AddMovie;
import et.com.qena.theater.dtos.responses.MovieResponse;
import et.com.qena.theater.entities.Movie;
import et.com.qena.theater.repositories.MovieRepository;
import et.com.qena.theater.services.ITheaterService;
import et.com.qena.theater.utils.GenericResponse;
import org.springframework.stereotype.Service;

@Service
public class TheaterServiceImpl implements ITheaterService {
    private final MovieRepository movieRepository;

    public TheaterServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public GenericResponse<MovieResponse> addMovie(AddMovie request) {
        Movie movie = movieRepository.save(new Movie(request));
        if (movie != null) {
            return new GenericResponse<>(200, "saved!", new MovieResponse(movie.getMovieId(), "success"));
        }
        return new GenericResponse<>(400, "oops.. something went wrong contact support", new MovieResponse());
    }
}
