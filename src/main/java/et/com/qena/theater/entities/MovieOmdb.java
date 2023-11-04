package et.com.qena.theater.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieOmdb {
    @JsonProperty("Title")
    public String Title;
    @JsonProperty("Year")
    public String Year;
    @JsonProperty("Rated")
    public String Rated;
    @JsonProperty("Released")
    public String released;
    @JsonProperty("Runtime")
    public String Runtime;
    @JsonProperty("Genre")
    public String Genre;
    @JsonProperty("Director")
    public String Director;
    @JsonProperty("Writer")
    public String Writer;
    @JsonProperty("Actors")
    public String Actors;
    @JsonProperty("Plot")
    public String Plot;
    @JsonProperty("Language")
    public String Language;
    @JsonProperty("Country")
    public String Country;
    @JsonProperty("Awards")
    public String Awards;
    @JsonProperty("Poster")
    public String Poster;
    @JsonProperty("Ratings")
    public ArrayList<Rating> Ratings;
    @JsonProperty("Metascore")
    public String Metascore;
    public String imdbRating;
    public String imdbVotes;
    public String imdbID;
    @JsonProperty("Type")
    public String Type;
    @JsonProperty("DVD")
    public String DVD;
    @JsonProperty("BoxOffice")
    public String BoxOffice;
    @JsonProperty("Production")
    public String Production;
    @JsonProperty("Website")
    public String Website;
    @JsonProperty("Response")
    public String Response;
}
