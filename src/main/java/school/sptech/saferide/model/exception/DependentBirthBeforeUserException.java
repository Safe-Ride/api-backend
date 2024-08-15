package school.sptech.saferide.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DependentBirthBeforeUserException extends RuntimeException {
    public DependentBirthBeforeUserException() {
        super("A data de nascimento do Dependente não pode ser depois da data de nascimento do responsável");
    }
}
