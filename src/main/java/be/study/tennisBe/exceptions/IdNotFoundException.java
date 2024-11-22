package be.study.tennisBe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Id not found")
public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(Long id, String theName) {
        super(MessageFormat.format("Could not find this id {0} for the class {1}", id, theName));
    }
}