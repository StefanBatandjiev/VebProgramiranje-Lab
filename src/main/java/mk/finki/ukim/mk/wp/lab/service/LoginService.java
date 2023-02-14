package mk.finki.ukim.mk.wp.lab.service;

import mk.finki.ukim.mk.wp.lab.model.User;

public interface LoginService {
    User login(String username, String password);
}
