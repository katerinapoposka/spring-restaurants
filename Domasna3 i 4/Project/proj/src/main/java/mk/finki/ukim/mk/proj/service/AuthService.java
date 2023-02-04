package mk.finki.ukim.mk.proj.service;

import mk.finki.ukim.mk.proj.model.User;

public interface AuthService {
    User login(String username, String password);
}
