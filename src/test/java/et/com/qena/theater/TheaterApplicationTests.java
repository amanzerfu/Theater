package et.com.qena.theater;
import static org.assertj.core.api.Assertions.assertThat;
import et.com.qena.theater.dtos.requests.AddUser;
import et.com.qena.theater.dtos.requests.NewMovie;
import et.com.qena.theater.dtos.responses.MovieResponse;
import et.com.qena.theater.dtos.responses.UserResponse;
import et.com.qena.theater.entities.Movie;
import et.com.qena.theater.implementations.TheaterServiceImpl;
import et.com.qena.theater.repositories.IUserRepository;
import et.com.qena.theater.repositories.MovieRepository;
import et.com.qena.theater.utils.DateUtils;
import et.com.qena.theater.validations.RegistrationValidation;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TheaterApplicationTests {

@Test
void contextLoads() {
}

    @Mock
    private IUserRepository userRepository;

    @Mock
    private RegistrationValidation registrationValidation;
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private TheaterServiceImpl movieService;
    @Before("")
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddMovieSuccessfully() {
        // Create a new movie request
        NewMovie newMovieRequest = new NewMovie("fddfgdfgdg",
                "1977","90 min","Animation, Adventure, Family",
                "Jules Bass, Arthur Rankin Jr."
                ,"J.R.R. Tolkien, Romeo Muller","Orson Bean, John Huston, Theodore Gottlieb",
                "A homebody hobbit in Middle Earth gets talked into joining a quest with a group of dwarves to recover their treasure from a dragon.",
                "English","<File>","movie");

        // Mock the movie repository to save the new movie

        // Add the new movie

        Movie movie = new Movie(1L,"1234","1234","2017","1hr","comedy","matthew","matthew","asaa asas asas","sajhakshdajhajksd","Amharic","sjdfklsjdf.png","movie", DateUtils.getCurrentTimeStamp());
        Movie movie1=movieRepository.save(movie);
        when(movieRepository.save(movie)).thenReturn(movie);

        MovieResponse movieResponse = movieService.addMovie(newMovieRequest);
        movieResponse.setStatus("success");
        // Assert that the movie was added successfully
        assertEquals("success", movieResponse.getStatus());
        assertEquals(movie.getMovieId(), "1234");
    }

}
