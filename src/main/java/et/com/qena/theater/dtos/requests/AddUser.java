package et.com.qena.theater.dtos.requests;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUser {
    @Min(value = 6,message = "invalid username")
    @Max(value = 30,message = "invalid username")
private String username;
private String email;
}
