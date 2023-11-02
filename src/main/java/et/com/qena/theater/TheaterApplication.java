package et.com.qena.theater;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(info = @Info(
		title = "Theater Web App service",
		version = "1.0",
		contact = @Contact(
				name = "Theater",
				url = "---",
				email = "theaterinfo@gmail.com"),
		description= "Welcome to the Theater. It's a movie review app. Using this application, users can search for movies (cinema movies, TV shows, etc.)."
))
@SpringBootApplication
public class TheaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheaterApplication.class, args);
	}

}
