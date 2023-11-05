package et.com.qena.theater.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRemoteResponse {
        @JsonProperty("Search")
        public List<Search> search;
        public String totalResults;
        @JsonProperty("Response")
        public String response;
}
