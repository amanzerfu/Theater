package et.com.qena.theater.entities;

import et.com.qena.theater.dtos.requests.AddMovie;
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
    private long id;
    @Column(name = "movie_id")
    private String movieId;
    @Column(name = "title")
    private String title;
    @Column(name = "year")
    private String year;
    @Column(name = "runtime")
    private String runtime;
    @Column(name = "genre")
    private String genre;
    @Column(name = "director")
    private String director;
    @Column(name = "writer")
    private String writer;
    @Column(name = "actors")
    private String actors;
    @Column(name = "plot")
    private String plot;
    @Column(name = "language")
    private String language;
    @Column(name = "poster")
    private String poster;
    @Column(name = "type")
    private String type;
    @Column(name = "created_at")
    private Timestamp createdAt;

    public Movie(AddMovie request) {
        this.movieId = MultipleUtils.randomString();
        this.title = request.getTitle();
        this.year = request.getYear();
        this.runtime = request.getRuntime();
        this.genre = request.getGenre();
        this.director = request.getDirector();
        this.writer = request.getWriter();
        this.actors = request.getActors();
        this.plot = request.getPlot();
        this.language = request.getLanguage();
        this.poster = request.getPoster();
        this.type = request.getType();
        this.createdAt = DateUtils.getCurrentTimeStamp();
    }
}
