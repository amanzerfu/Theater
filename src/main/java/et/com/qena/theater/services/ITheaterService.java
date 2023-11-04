package et.com.qena.theater.services;

import et.com.qena.theater.dtos.requests.AddMovie;
import et.com.qena.theater.dtos.requests.AddReviews;
import et.com.qena.theater.dtos.requests.AddUser;
import et.com.qena.theater.dtos.requests.NewMovie;
import et.com.qena.theater.dtos.responses.*;
import et.com.qena.theater.utils.GenericResponse;
import et.com.qena.theater.utils.Response;

public interface  ITheaterService {
    MovieResponse addMovie(NewMovie request);

    MovieSearchResponse searchMovie(String title, String year, int page, int perPage);

    UserResponse addUser(AddUser request);

    ReviewsResponse addReview(AddReviews request);

    GenericResponse getReviews(String userId, int page, int perPage);
}
