package school.sptech.saferide.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DependenteNotLinkUsuarioException extends RuntimeException {
    public DependenteNotLinkUsuarioException(String dependente, String responsavel) {
        super(String.format("O dependente %s não possui vinculo com o responsável %s", dependente, responsavel));
    }
}
