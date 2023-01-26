package ch.heig.basket.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TrophyNotFoundException extends RuntimeException {
    public TrophyNotFoundException(Integer id) {
        super("Trophy " + id + " non trouvé");
    }
}
