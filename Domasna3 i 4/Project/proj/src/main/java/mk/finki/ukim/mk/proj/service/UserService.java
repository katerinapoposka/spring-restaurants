package mk.finki.ukim.mk.proj.service;

import mk.finki.ukim.mk.proj.model.Role;
import mk.finki.ukim.mk.proj.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User findById(Long id);

    User findByUsername(String username);

    User addToFavourites(Long userId, Long restaurantId);

    User removeFavourites(Long userId, Long restaurantId);
}
