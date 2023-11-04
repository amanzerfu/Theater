package et.com.qena.theater.dtos.requests;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMovie {
    private String Title;
    private String Year;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Language;
    private String Poster;
    private String Type;

    @Override
    public String toString() {
        return "AddMovie{" +
                "Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", Runtime='" + Runtime + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Director='" + Director + '\'' +
                ", Writer='" + Writer + '\'' +
                ", Actors='" + Actors + '\'' +
                ", Plot='" + Plot + '\'' +
                ", Language='" + Language + '\'' +
                ", Poster='" + Poster + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
