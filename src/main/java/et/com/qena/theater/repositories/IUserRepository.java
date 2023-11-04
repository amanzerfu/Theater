package et.com.qena.theater.repositories;

import et.com.qena.theater.dtos.requests.AddUser;
import et.com.qena.theater.entities.Reviews;
import et.com.qena.theater.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username =:username OR u.email = :email")
    List<User> findUserByUserNameOrEmail(String username, String email);

    @Query("SELECT u FROM User u WHERE u.userId =:userId")
    User findByUserId(String userId);


}
