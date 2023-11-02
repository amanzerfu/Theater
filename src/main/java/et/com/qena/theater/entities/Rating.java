package et.com.qena.theater.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @JsonProperty("Source")
    public String source;
    @JsonProperty("Value")
    public String value;
}
