package be.study.tennisBe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason ="No such User")
public class UserTennisNotFoundException extends RuntimeException{
    public UserTennisNotFoundException(String message) {
        super( MessageFormat.format("could not find user with username {0}", message));
    }
}
