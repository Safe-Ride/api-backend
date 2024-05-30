package saferide.sptech.apibackend.entity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    public ConflictException(String campo) {
        super(String.format("O campo '%s' já existe!", campo));
    }

}
