package school.sptech.saferide.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotRemoveWithRelationshipsException extends RuntimeException {
    public NotRemoveWithRelationshipsException(String entidade, String vinculo) {
        super(String.format("A entidade %s possui vinculo com %s", entidade, vinculo));
    }
}
