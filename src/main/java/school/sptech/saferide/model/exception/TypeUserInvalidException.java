package school.sptech.saferide.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TypeUserInvalidException extends RuntimeException {
    public TypeUserInvalidException(String campo) {
        super(String.format("O tipo de usuario não pode ser %s", campo));
    }
}
