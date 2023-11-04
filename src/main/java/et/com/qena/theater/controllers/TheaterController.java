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
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TheaterController {
    private final ITheaterService theaterService;

    public TheaterController(ITheaterService theaterService) {
        this.theaterService = theaterService;
    }
//    @Operation(summary = "Add Movie Service")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",description = "Movie Added successfully."),
//            @ApiResponse(responseCode = "400",description = "There was an error while Adding Movie.")
//    })
    @PostMapping("/movies")
    public MovieResponse addMovie(@RequestBody NewMovie request)
    {
        return theaterService.addMovie(request);
    }
    @GetMapping("/movies")
    public MovieSearchResponse getMovies(@RequestParam("title") String title,@RequestParam("year") String year,@RequestParam("page") int page,@RequestParam("per_page") int per_page)
    {
        return theaterService.searchMovie(title,year,page,per_page);
    }

    @PostMapping("/users")
    public UserResponse addMovie(@RequestBody AddUser request)
    {
        return theaterService.addUser(request);
    }
    @PostMapping("/reviews")
    public ReviewsResponse addReviews(@RequestBody AddReviews request)
    {
        return theaterService.addReview(request);
    }
    @GetMapping("/reviews/{userId}")
    public GenericResponse getReviews(@PathVariable String userId, @RequestParam("page") int page, @RequestParam("per_page") int per_page)
    {
        return theaterService.getReviews(userId,page,per_page);

    }
}
