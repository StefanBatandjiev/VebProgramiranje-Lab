package mk.finki.ukim.mk.wp.lab;

import mk.finki.ukim.mk.wp.lab.model.User;
import mk.finki.ukim.mk.wp.lab.model.exceptions.WrongCredentialsException;
import mk.finki.ukim.mk.wp.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.wp.lab.service.impl.LoginServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private LoginServiceImpl service;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        User user = new User("username","password");
        Mockito.when(this.userRepository.findByUsernameAndPassword(Mockito.matches("username"),
                Mockito.matches("password"))).thenReturn(Optional.of(user));
        this.service = Mockito.spy(new LoginServiceImpl(this.userRepository,this.passwordEncoder));
    }

    @Test
    public void testSuccessLogin(){
        User user = this.service.login("username","password");
        Mockito.verify(this.service).login("username","password");
        Assert.assertNotNull("User is null",user);
        Assert.assertEquals("username do not match","username",user.getUsername());
        Assert.assertEquals("Password do not match","password",user.getPassword());
    }

    @Test
    public void testNullUsername(){
        Assert.assertThrows("WrongCredentialsException expected", WrongCredentialsException.class, () -> this.service.login(null,"password"));
        Mockito.verify(this.service).login(null,"password");
    }

    @Test
    public void testEmptyUsername() {
        Assert.assertThrows("WrongCredentialsException expected", WrongCredentialsException.class, () -> this.service.login("", "password"));
        Mockito.verify(this.service).login("", "password");
    }

    @Test
    public void testNullPassword() {
        Assert.assertThrows("WrongCredentialsException expected", WrongCredentialsException.class, () -> this.service.login("username", null));
        Mockito.verify(this.service).login("username", null);
    }

    @Test
    public void testEmptyPassword() {
        Assert.assertThrows("WrongCredentialsException expected", WrongCredentialsException.class, () -> this.service.login("username", ""));
        Mockito.verify(this.service).login("username", "");
    }
}
