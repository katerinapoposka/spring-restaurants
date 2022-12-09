package mk.finki.ukim.mk.proj.repository.jpa;

import mk.finki.ukim.mk.proj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
