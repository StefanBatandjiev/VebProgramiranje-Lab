package mk.finki.ukim.mk.wp.lab.service.impl;


import mk.finki.ukim.mk.wp.lab.model.User;
import mk.finki.ukim.mk.wp.lab.model.exceptions.WrongCredentialsException;
import mk.finki.ukim.mk.wp.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.wp.lab.service.LoginService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl  implements LoginService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public LoginServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String username, String password) {
        return repository.findByUsernameAndPassword(username,password).orElseThrow(WrongCredentialsException::new);
    }
}