package et.com.qena.theater.implementations;

import et.com.qena.theater.configs.TheaterConfig;
import et.com.qena.theater.dtos.requests.AddMovie;
import et.com.qena.theater.dtos.requests.AddReviews;
import et.com.qena.theater.dtos.requests.AddUser;
import et.com.qena.theater.dtos.requests.NewMovie;
import et.com.qena.theater.dtos.responses.*;
import et.com.qena.theater.entities.Movie;
import et.com.qena.theater.entities.MovieOmdb;
import et.com.qena.theater.entities.Reviews;
import et.com.qena.theater.entities.User;
import et.com.qena.theater.repositories.IReviewsRepository;
import et.com.qena.theater.repositories.IUserRepository;
import et.com.qena.theater.repositories.MovieRepository;
import et.com.qena.theater.services.ITheaterService;
import et.com.qena.theater.utils.GenericResponse;
import et.com.qena.theater.utils.Response;
import et.com.qena.theater.validations.RegistrationValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
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

    @Override
    public MovieResponse addMovie(NewMovie request) {
        try {
            Movie movie = movieRepository.save(new Movie(request));
            return new MovieResponse(movie.getMovieId(), "success");
        } catch (Exception e) {
            return new MovieResponse(null, "oops.. something went wrong contact support");
        }
    }

    @Override
    public GenericResponse searchMovie(String title, String year, int page, int perPage) {
        RestTemplate restTemplate = new RestTemplate();
        List<Object> combined = new ArrayList<>();
        ResponseEntity<MovieRemoteResponse> response = restTemplate.getForEntity(
                theaterConfig.getOmdb_api_url() + "?apikey="
                        + theaterConfig.getOmdb_api_key() + "&s="
                        + title + "&y="
                        + year + "&page=1",
                MovieRemoteResponse.class);
        log.info("values  {}", response.getBody());
        List<Search> searchList = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, perPage);
//        Page<Movie> movieList = movieRepository.findByTitleAndYear("Guardians of the Galaxy Vol. 2", "2017", pageable);
        Page<Movie> movieList = movieRepository.findAll(pageable);
        if (!movieList.isEmpty()) {
            combined.addAll(movieList.stream().toList());
            log.info("Model {}", movieList.stream().toList());
        }
//        if (response.getBody().response.equalsIgnoreCase("True")) {
//            searchList = response.getBody().search;
//            combined.addAll(searchList);
//        }
        if (!combined.isEmpty()) {
            return new GenericResponse("success!", movieList.getSize(), page, perPage, combined);
        }
        return new GenericResponse("nooo", 0, 0, 0, null);
    }

    @Override
    public UserResponse addUser(AddUser request) {
        if (RegistrationValidation.isValidLength(request.getUsername(), 6, 30)) {
            if (RegistrationValidation.isValidUsername(request.getUsername())) {
                if (RegistrationValidation.isValidEmail(request.getEmail())) {
                    List<User> users = userRepository.findUserByUserNameOrEmail(request.getUsername(), request.getEmail());
                    if (users.isEmpty()) {
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

    @Override
    public ReviewsResponse addReview(AddReviews request) {
        User user = userRepository.findByUserId(request.getUserId());
        if (user != null) {
            if (request.getRating() > 0) {
                if (request.getRating() < 0 || request.getRating() > 10) {
                    try {
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

    @Override
    public MovieGetResponse getMovie(String id) {
//        RestTemplate restTemplate = new RestTemplate();
//        List<Object> combined = new ArrayList<>();
//        ResponseEntity<MovieRemoteResponse> response = restTemplate.getForEntity(
//                theaterConfig.getOmdb_api_url() + "?apikey="
//                        + theaterConfig.getOmdb_api_key() + "&i="
//                        + id,
//                MovieRemoteResponse.class);
//        log.info("values  {}", response.getBody());
//        List<Search> searchList = new ArrayList<>();
//
//        Movie movieList = movieRepository.findByMovieId(id);
//        if (movieList !=null) {
//            return new MovieGetResponse("success!",)
//        }
////        if (response.getBody().response.equalsIgnoreCase("True")) {
////            searchList = response.getBody().search;
////            combined.addAll(searchList);
////        }
//        if (!combined.isEmpty()) {
//            return new MovieGetResponse("success!", movieList.getSize(), page, perPage, combined);
//        }
        //working on it
        return new MovieGetResponse("nooo","", "0", "0", "0", "null");

    }
}
