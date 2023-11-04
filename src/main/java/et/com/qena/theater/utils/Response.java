package et.com.qena.theater.utils;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Response<A>{
    private int responseCode;
    private String responseMessage;
    private A data;
    private int totalPages;

    public Response(int responseCode, String responseMessage, A data) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public Response(int responseCode, String responseMessage, A data, int totalPages) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
        this.totalPages = totalPages;
    }
}
