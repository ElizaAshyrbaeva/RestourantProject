package peaksoft.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSuchElementException extends RuntimeException {

    public NoSuchElementException() {
        super();
    }

    public NoSuchElementException(String message) {
        super(message);
    }
}
