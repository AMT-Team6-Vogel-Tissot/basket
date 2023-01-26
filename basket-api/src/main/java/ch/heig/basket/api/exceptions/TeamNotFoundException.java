package ch.heig.basket.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(Integer id) {
        super("Team " + id + " non trouvé");
    }
}
