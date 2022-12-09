package mk.finki.ukim.mk.proj.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RestaurantDoesNotExistException extends RuntimeException {
    public RestaurantDoesNotExistException() {
        super(String.format("Restaurant with name does not exist"));
    }
}
