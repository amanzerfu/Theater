package et.com.qena.theater.implementations;

import et.com.qena.theater.dtos.requests.AddMovie;
import et.com.qena.theater.dtos.responses.MovieResponse;
import et.com.qena.theater.dtos.responses.MovieSearchResponse;
import et.com.qena.theater.entities.Movie;
import et.com.qena.theater.repositories.MovieRepository;
import et.com.qena.theater.services.ITheaterService;
import et.com.qena.theater.utils.GenericResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Service
public class TheaterServiceImpl implements ITheaterService {
    private final MovieRepository movieRepository;
    private final EntityManager entityManager;

    public TheaterServiceImpl(MovieRepository movieRepository, EntityManager entityManager) {
        this.movieRepository = movieRepository;
        this.entityManager = entityManager;
    }

    @Override
    public GenericResponse<MovieResponse> addMovie(AddMovie request) {
        Movie movie = movieRepository.save(new Movie(request));
        if (movie != null) {
            return new GenericResponse<>(200, "saved!", new MovieResponse(movie.getMovieId(), "success"));
        }
        return new GenericResponse<>(400, "oops.. something went wrong contact support", new MovieResponse());
    }

    @Override
    public MovieSearchResponse searchMovie(String title, String year, int page, int perPage) {

        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        Pageable pageable = PageRequest.of(page,perPage).withSort(Sort.by("createdAt").ascending());
        Page<Movie> movieList=movieRepository.findByTitleAndYear(title,year,pageable);
        if(!movieList.isEmpty())
        {
          return new MovieSearchResponse("success!",movieList.getSize(),page,perPage,movieList.stream().toList());
        }
        return new MovieSearchResponse("nooo",0,0,0,null);
    }
}
