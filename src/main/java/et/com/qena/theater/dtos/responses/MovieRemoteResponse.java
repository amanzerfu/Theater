package et.com.qena.theater.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRemoteResponse {
    public String Title;
    public String Year;
    public String imdbID;
    public String Type;
    public String Poster;
}
