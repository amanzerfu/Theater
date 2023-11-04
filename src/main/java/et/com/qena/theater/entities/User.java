package et.com.qena.theater.entities;

import et.com.qena.theater.utils.DateUtils;
import et.com.qena.theater.utils.MultipleUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_theater_users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "created_at")
    private Timestamp createdAt;

    public User(String username, String email) {
        this.userId = MultipleUtils.randomGenerateId();
        this.username = username;
        this.email = email;
        this.createdAt = DateUtils.getCurrentTimeStamp();
    }
}
