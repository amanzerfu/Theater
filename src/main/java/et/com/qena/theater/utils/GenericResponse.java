package et.com.qena.theater.utils;

import lombok.Data;

@Data
public class GenericResponse<A> {
    private String status;
    private int totalResult;
    private int page;
    private int per_page;
    private A date;

    public GenericResponse(String status, int totalResult, int page, int per_page, A date) {
        this.status = status;
        this.totalResult = totalResult;
        this.page = page;
        this.per_page = per_page;
        this.date = date;
    }

    public GenericResponse(A date) {
        this.date = date;
    }

    public GenericResponse(String status) {
        this.status = status;
    }
}
