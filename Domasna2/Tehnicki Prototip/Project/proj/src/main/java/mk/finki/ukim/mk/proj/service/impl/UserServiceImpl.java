package mk.finki.ukim.mk.proj.service.impl;

import mk.finki.ukim.mk.proj.model.User;
import mk.finki.ukim.mk.proj.model.exceptions.UserNotFoundException;
import mk.finki.ukim.mk.proj.repository.jpa.UserRepository;
import mk.finki.ukim.mk.proj.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
