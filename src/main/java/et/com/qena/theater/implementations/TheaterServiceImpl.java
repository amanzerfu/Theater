package et.com.qena.theater.implementations;

import et.com.qena.theater.dtos.requests.AddMovie;
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
import et.com.qena.theater.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterServiceImpl implements ITheaterService {
    private final MovieRepository movieRepository;
    private final EntityManager entityManager;
    private final IUserRepository userRepository;
    private final IReviewsRepository reviewsRepository;

    @Override
    public MovieResponse addMovie(NewMovie request) {
        try {
            Movie movie = movieRepository.save(new Movie(request));
            return new MovieResponse(movie.getMovieId(), "success");
        } catch (Exception e) {
            return new MovieResponse(null,"oops.. something went wrong contact support");
        }
    }

    @Override
    public MovieSearchResponse searchMovie(String title, String year, int page, int perPage) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Pageable pageable = PageRequest.of(page, perPage).withSort(Sort.by("created_at").ascending());
        Page<Movie> movieList = movieRepository.findByTitleAndYear(title, year, pageable);
        if (!movieList.isEmpty()) {
            return new MovieSearchResponse("success!", movieList.getSize(), page, perPage, movieList.stream().toList());
        }
        return new MovieSearchResponse("nooo", 0, 0, 0, null);
    }

    @Override
    public UserResponse addUser(AddUser request) {
        List<User> users = userRepository.findUserByUserNameOrEmail(request.getUsername(), request.getEmail());
        if (users.isEmpty()) {
            User user = userRepository.save(new User(request.getUsername(), request.getEmail()));
            return new UserResponse(user.getUserId(), "success!");
        }
        return new UserResponse(null, "not saved");
    }

    @Override
    public ReviewsResponse addReview(AddReviews request) {
        User user = userRepository.findByUserId(request.getUserId());
        if (user != null) {
            try {
                Reviews reviews = reviewsRepository.save(new Reviews(request));
                return new ReviewsResponse("success", reviews.getReviewId());
            } catch (Exception e) {
                return new ReviewsResponse("not saved " + e, null);
            }
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
}
