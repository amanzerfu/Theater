package et.com.qena.theater.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rating {
    @JsonProperty("Source")
    public String source;
    @JsonProperty("Value")
    public String value;
}
