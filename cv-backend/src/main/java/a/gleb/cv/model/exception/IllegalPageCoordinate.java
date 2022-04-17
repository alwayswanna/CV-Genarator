package a.gleb.cv.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Out of pages.")
public class IllegalPageCoordinate extends RuntimeException{
    public IllegalPageCoordinate(String message) {
        super(message);
    }
}
