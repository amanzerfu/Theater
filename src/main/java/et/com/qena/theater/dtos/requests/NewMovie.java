package et.com.qena.theater.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewMovie {
    @JsonProperty(value = "Title")
    private String Title;
    @JsonProperty(value = "Year")
    private String Year;
    @JsonProperty(value = "Runtime")
    private String Runtime;
    @JsonProperty(value = "Genre")
    private String Genre;
    @JsonProperty(value = "Director")
    private String Director;
    @JsonProperty(value = "Writer")
    private String Writer;
    @JsonProperty(value = "Actors")
    private String Actors;
    @JsonProperty(value = "Plot")
    private String Plot;
    @JsonProperty(value = "Language")
    private String Language;
    @JsonProperty(value = "Poster")
    private String Poster;
    @JsonProperty(value = "Type")
    private String Type;
}
