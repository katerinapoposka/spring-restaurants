package mk.finki.ukim.mk.proj.service;

import mk.finki.ukim.mk.proj.model.User;

public interface UserService {
    User findById(Long id);
}
