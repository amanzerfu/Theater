package et.com.qena.theater.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRemoteResponse {
    public String title;
    public String year;
    public String imdbID;
    public String type;
    public String poster;
}
