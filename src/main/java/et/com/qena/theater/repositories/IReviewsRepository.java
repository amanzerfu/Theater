package et.com.qena.theater.repositories;

import et.com.qena.theater.entities.Reviews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReviewsRepository extends JpaRepository<Reviews,Long> {

    @Query("SELECT r FROM Reviews r WHERE r.userId = :userId")
    Page<Reviews> findReviewsByUserId(String userId, Pageable pageable);
}
