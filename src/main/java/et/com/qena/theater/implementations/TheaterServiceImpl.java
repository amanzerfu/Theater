package et.com.qena.theater.implementations;

import et.com.qena.theater.configs.TheaterConfig;
import et.com.qena.theater.dtos.requests.AddReviews;
import et.com.qena.theater.dtos.requests.AddUser;
import et.com.qena.theater.dtos.requests.NewMovie;
import et.com.qena.theater.dtos.responses.*;
import et.com.qena.theater.entities.Movie;

import et.com.qena.theater.entities.Reviews;
import et.com.qena.theater.entities.User;
import et.com.qena.theater.repositories.IReviewsRepository;
import et.com.qena.theater.repositories.IUserRepository;
import et.com.qena.theater.repositories.MovieRepository;
import et.com.qena.theater.services.ITheaterService;
import et.com.qena.theater.utils.GenericResponse;

import et.com.qena.theater.validations.RegistrationValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TheaterServiceImpl implements ITheaterService {
    private final MovieRepository movieRepository;
    private final EntityManager entityManager;
    private final TheaterConfig theaterConfig;
    private final IUserRepository userRepository;
    private final IReviewsRepository reviewsRepository;

    /**
     * create new movie service
     * @param request gets request from the user
     * @return and return movie id and success status if complete adding or throw error if not.
     */
    @Override
    public MovieResponse addMovie(NewMovie request) {
        try {
            log.info("Adding movie, {}", request);
            Movie movie = movieRepository.save(new Movie(request));
            return new MovieResponse(movie.getMovieId(), "success");
        } catch (Exception e) {
            return new MovieResponse(null, "oops.. something went wrong contact support");
        }
    }

    /**
     *  get Movie using title and year from local database and remote db concatinate and return paginated result
     * @param title title of movie
     * @param year year of move
     * @param page starting page example 0,1
     * @param perPage number of elements per page example 2 or 10
     * @return return results from both db
     */
    @Override
    public GenericResponse searchMovie(String title, String year, int page, int perPage) {
        RestTemplate restTemplate = new RestTemplate();
        List<MovieDTOResponse> combined = new ArrayList<>();
        MovieDTOResponse movieDTOResponse = null;
        ResponseEntity<MovieRemoteResponse> response = restTemplate.getForEntity(
                theaterConfig.getOmdb_api_url() + "?apikey="
                        + theaterConfig.getOmdb_api_key() + "&s="
                        + title + "&y="
                        + year + "&page=1",
                MovieRemoteResponse.class);
        log.info("values  {}", response.getBody());
        List<Search> searchList = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, perPage);
        Page<Movie> movieList = movieRepository.findByTitleAndYear(title, year, pageable);

        if (!movieList.isEmpty()) {
            for (Movie movie : movieList) {
                movieDTOResponse = new MovieDto("success", movie.getMovieId());
                movieDTOResponse.title = movie.getTitle();
                movieDTOResponse.year = movie.getYear();
                movieDTOResponse.poster = movie.getPoster();
                movieDTOResponse.type = movie.getType();
                combined.add(movieDTOResponse);
            }
        }
        if (response.getBody().response.equalsIgnoreCase("True")) {
            searchList = response.getBody().search;
            for (Search movie : searchList) {
                movieDTOResponse = new SearchDto(movie.imdbID);
                movieDTOResponse.title = movie.title;
                movieDTOResponse.year = movie.year;
                movieDTOResponse.poster = movie.poster;
                movieDTOResponse.type = movie.type;
                combined.add(movieDTOResponse);
            }
        }
        if (!combined.isEmpty()) {
            return new GenericResponse("success!", movieList.getSize(), page, perPage, combined);
        }
        return new GenericResponse("Not found", 0, 0, 0, null);
    }

    /**
     * Add user service
     * @param request valid username and email from user
     * @return return success message
     */
    @Override
    public UserResponse addUser(AddUser request) {
        if (RegistrationValidation.isValidLength(request.getUsername(), 6, 30)) {
            if (RegistrationValidation.isValidUsername(request.getUsername())) {
                if (RegistrationValidation.isValidEmail(request.getEmail())) {
                    List<User> users = userRepository.findUserByUserNameOrEmail(request.getUsername(), request.getEmail());
                    if (users.isEmpty()) {
                        log.info("Adding user, {}", request);
                        User user = userRepository.save(new User(request.getUsername(), request.getEmail()));
                        return new UserResponse(user.getUserId(), "success!");
                    }
                    return new UserResponse(null, "user already exists!");
                }
                return new UserResponse(null, "invalid email ...please use valid email format");
            }
            return new UserResponse(null, "invalid username ...username can only contain alphanumeric characters and underscores (_). \n The first character of the username must be an alphabetic character.");
        }
        return new UserResponse(null, "invalid username ...use min 6 or max 30 character for username");
    }

    /**
     * Add review service
     * @param request get valid request
     * @return return success message if created
     */

    @Override
    public ReviewsResponse addReview(AddReviews request) {
        User user = userRepository.findByUserId(request.getUserId());
        if (user != null) {
            if (request.getRating() > 0) {
                if (request.getRating() < 0 || request.getRating() > 10) {
                    try {
                        log.info("Adding review, {}", request);
                        Reviews reviews = reviewsRepository.save(new Reviews(request));
                        return new ReviewsResponse("success", reviews.getReviewId());
                    } catch (Exception e) {
                        return new ReviewsResponse("not saved " + e, null);
                    }
                }
                return new ReviewsResponse("Invalid Rating!", null);
            }
            return new ReviewsResponse("Rating is mandatory field", null);
        }
        return new ReviewsResponse("user not found", null);
    }

    /**
     * get reviews service filtered by user id
     * @param userId user id to be filtered
     * @param page start page number
     * @param perPage item per page
     * @return return filtered values
     */
    @Override
    public GenericResponse getReviews(String userId, int page, int perPage) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            try {
                Pageable pageable = PageRequest.of(page, perPage).withSort(Sort.by("createdAt").ascending());
                Page<Reviews> reviews = reviewsRepository.findReviewsByUserId(userId, pageable);
                if (reviews != null) {
                    List<ReviewResponse> reviewResponses = new ArrayList<>();
                    for (Reviews reviewModel : reviews) {
                        reviewResponses.add(new ReviewResponse(reviewModel));
                    }
                    return new GenericResponse<>("success", reviews.getNumberOfElements(), page, reviews.getTotalPages(), reviewResponses);
                }
                return new GenericResponse<>("no review yet", 0, 0, 0, null);
            } catch (Exception e) {
                return new GenericResponse<>("something went wrong contact support", 0, 0, 0, null);
            }
        }
        return new GenericResponse("user not found", 0, 0, 0, null);
    }

    /**
     * get movies from both local and remote db using movie id
     * @param id this is movie ID
     * @return return movie if available
     */
    @Override
    public MovieDTOResponse getMovie(String id) {
        RestTemplate restTemplate = new RestTemplate();
        Object tobeReturned = new Object();
        MovieDTOResponse movieDTOResponse = null;
        ResponseEntity<MovieGetRemoteResponse> response = restTemplate.getForEntity(
                theaterConfig.getOmdb_api_url() + "?apikey="
                        + theaterConfig.getOmdb_api_key() + "&i="
                        + id,
                MovieGetRemoteResponse.class);
        log.info("values  {}", response.getBody());

        Movie movieList = movieRepository.findByMovieId(id);
        if (movieList != null) {
            movieDTOResponse = new MovieDto("success", movieList.getMovieId());
            movieDTOResponse.title = movieList.getTitle();
            movieDTOResponse.year = movieList.getYear();
            movieDTOResponse.poster = movieList.getPoster();
            movieDTOResponse.type = movieList.getType();
            return movieDTOResponse;
        }
        if (movieList == null && response.getBody().response.equalsIgnoreCase("True")) {
            movieDTOResponse = new SearchDto(response.getBody().imdbID);
            movieDTOResponse.title = response.getBody().title;
            movieDTOResponse.year = response.getBody().year;
            movieDTOResponse.poster = response.getBody().poster;
            movieDTOResponse.type = response.getBody().type;

        }
        if (movieDTOResponse != null) {
            return movieDTOResponse;
        }
        return new MovieDto("Movie not found using this Id", null);
    }
}
