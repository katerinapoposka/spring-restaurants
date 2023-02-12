package mk.finki.ukim.mk.proj.service.impl;

import mk.finki.ukim.mk.proj.model.Restaurants;
import mk.finki.ukim.mk.proj.model.Role;
import mk.finki.ukim.mk.proj.model.User;
import mk.finki.ukim.mk.proj.model.exceptions.*;
import mk.finki.ukim.mk.proj.repository.jpa.RestaurantRepository;
import mk.finki.ukim.mk.proj.repository.jpa.UserRepository;
import mk.finki.ukim.mk.proj.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, RestaurantRepository restaurantRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }


    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role userRole) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username, passwordEncoder.encode(password), name, surname, userRole);
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public User addToFavourites(Long userId, Long restaurantId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Restaurants restaurant = restaurantRepository.findById(restaurantId).orElseThrow(RestaurantDoesNotExistException::new);
        user.getFavorites().add(restaurant);
        return userRepository.save(user);
    }

    @Override
    public User removeFavourites(Long userId, Long restaurantId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Restaurants restaurant = restaurantRepository.findById(restaurantId).orElseThrow(RestaurantDoesNotExistException::new);
        user.getFavorites().remove(restaurant);
        return userRepository.save(user);
    }

}
