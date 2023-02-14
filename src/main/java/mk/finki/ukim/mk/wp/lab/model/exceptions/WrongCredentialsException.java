package mk.finki.ukim.mk.wp.lab.model.exceptions;

public class WrongCredentialsException extends RuntimeException{
    public WrongCredentialsException() {
        super("Invalid user credentials");
    }
}
