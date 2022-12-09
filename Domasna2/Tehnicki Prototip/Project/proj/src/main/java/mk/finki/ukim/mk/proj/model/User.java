package mk.finki.ukim.mk.proj.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name="cit_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String password;
    @OneToMany(fetch=FetchType.EAGER)
    private List<Restaurants> favorites;

    public User() {
    }
}
