package school.sptech.saferide.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SolicitacaoAlreadyApproveException extends RuntimeException {
    public SolicitacaoAlreadyApproveException(String campo) {
        super(String.format("Solicitacao %s já aprovada", campo));
    }
}
