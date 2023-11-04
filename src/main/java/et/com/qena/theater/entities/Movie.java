package et.com.qena.theater.entities;

import et.com.qena.theater.dtos.requests.AddMovie;
import et.com.qena.theater.dtos.requests.NewMovie;
import et.com.qena.theater.utils.DateUtils;
import et.com.qena.theater.utils.MultipleUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "movie_id")
    private String MovieId;
    @Column(name = "title")
    private String Title;
    @Column(name = "year")
    private String Year;
    @Column(name = "runtime")
    private String Runtime;
    @Column(name = "genre")
    private String Genre;
    @Column(name = "director")
    private String Director;
    @Column(name = "writer")
    private String Writer;
    @Column(name = "actors")
    private String Actors;
    @Column(name = "plot")
    private String Plot;
    @Column(name = "language")
    private String Language;
    @Column(name = "poster")
    private String Poster;
    @Column(name = "type")
    private String Type;
    @Column(name = "created_at")
    private Timestamp CreatedAt;

    public Movie(NewMovie request) {
        this.MovieId = MultipleUtils.randomGenerateId();
        this.Title = request.getTitle();
        this.Year = request.getYear();
        this.Runtime = request.getRuntime();
        this.Genre = request.getGenre();
        this.Director = request.getDirector();
        this.Writer = request.getWriter();
        this.Actors = request.getActors();
        this.Plot = request.getPlot();
        this.Language = request.getLanguage();
        this.Poster = request.getPoster();
        this.Type = request.getType();
        this.CreatedAt = DateUtils.getCurrentTimeStamp();
    }

}
