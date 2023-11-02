package et.com.qena.theater.controllers;

import et.com.qena.theater.dtos.requests.AddMovie;
import et.com.qena.theater.dtos.responses.MovieResponse;
import et.com.qena.theater.services.ITheaterService;
import et.com.qena.theater.utils.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TheaterController {
    private final ITheaterService theaterService;

    public TheaterController(ITheaterService theaterService) {
        this.theaterService = theaterService;
    }
    @Operation(summary = "Add Movie Service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Movie Added successfully."),
            @ApiResponse(responseCode = "400",description = "There was an error while Adding Movie.")
    })
    @PostMapping("/movies")
    public GenericResponse<MovieResponse> addMovie(@RequestBody AddMovie request)
    {
        return theaterService.addMovie(request);
    }
}
