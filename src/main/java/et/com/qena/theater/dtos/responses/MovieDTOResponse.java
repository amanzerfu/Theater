package et.com.qena.theater.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class MovieDTOResponse {
    @JsonProperty("Title")
    public String title;
    @JsonProperty("Year")
    public String year;;
    @JsonProperty("Type")
    public String type;
    @JsonProperty("Poster")
    public String poster;
}
