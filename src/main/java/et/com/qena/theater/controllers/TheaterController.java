package et.com.qena.theater.controllers;

import et.com.qena.theater.dtos.requests.AddMovie;
import et.com.qena.theater.dtos.requests.AddReviews;
import et.com.qena.theater.dtos.requests.AddUser;
import et.com.qena.theater.dtos.requests.NewMovie;
import et.com.qena.theater.dtos.responses.*;
import et.com.qena.theater.services.ITheaterService;
import et.com.qena.theater.utils.GenericResponse;
import et.com.qena.theater.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class TheaterController {
    private final ITheaterService theaterService;


    @Operation(summary = "Add Movie Service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie Added successfully."),
            @ApiResponse(responseCode = "500", description = "There was an error while Adding Movie.")
    })
    @PostMapping("/movies")
    public MovieResponse addMovie(@RequestBody NewMovie movie) {
        return theaterService.addMovie(movie);
    }

    @Operation(summary = "Search Movie using title and year  Service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie search successfully."),
            @ApiResponse(responseCode = "500", description = "There was an error while searching Movie.")
    })
    @GetMapping("/movies")
    public GenericResponse getMovies(@RequestParam("title") String title, @RequestParam("year") String year, @RequestParam("page") int page, @RequestParam("per_page") int per_page) {
        return theaterService.searchMovie(title, year, page, per_page);
    }

    @Operation(summary = "Search Movie using id  Service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie search successfully."),
            @ApiResponse(responseCode = "500", description = "There was an error while searching Movie.")
    })
    @GetMapping("/movies/{id}")
    public MovieDTOResponse getMovie(@PathVariable String id) {
        return theaterService.getMovie(id);
    }

    @Operation(summary = "Search Movie using title and year  Service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie search successfully."),
            @ApiResponse(responseCode = "500", description = "There was an error while searching Movie.")
    })
    @PostMapping("/users")
    public UserResponse addMovie(@RequestBody AddUser request) {
        return theaterService.addUser(request);
    }

    @Operation(summary = "Add reviews   Service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review Added successfully."),
            @ApiResponse(responseCode = "500", description = "There was an error while Adding Review.")
    })
    @PostMapping("/reviews")
    public ReviewsResponse addReviews(@RequestBody AddReviews request) {
        return theaterService.addReview(request);
    }

    @Operation(summary = "get review by user Service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review get successful."),
            @ApiResponse(responseCode = "500", description = "There was an error while getting Review.")
    })
    @GetMapping("/reviews/{userId}")
    public GenericResponse getReviews(@PathVariable String userId, @RequestParam("page") int page, @RequestParam("per_page") int per_page) {
        return theaterService.getReviews(userId, page, per_page);

    }
}
