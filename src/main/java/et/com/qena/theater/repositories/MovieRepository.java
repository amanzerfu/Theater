package et.com.qena.theater.repositories;

import et.com.qena.theater.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    Page<Movie> findByTitleAndYear(String title, String year, Pageable pageable);
}
