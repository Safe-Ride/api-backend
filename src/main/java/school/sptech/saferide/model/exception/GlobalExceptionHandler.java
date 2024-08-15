package school.sptech.saferide.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ProblemDetail handleDefault(HttpStatus status, Exception e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, e.getLocalizedMessage());
        problemDetail.setDetail(e.getMessage());
        return problemDetail;
    }
    @ExceptionHandler(NotFoundException.class)
    ProblemDetail handleNotFoundException(NotFoundException e) {
        return handleDefault(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(ConflictException.class)
    ProblemDetail handleConflictException(ConflictException e) {
        return handleDefault(HttpStatus.CONFLICT, e);
    }

    @ExceptionHandler(DependenteNotLinkUsuarioException.class)
    ProblemDetail handleDependenteNotLinkUsuarioException(DependenteNotLinkUsuarioException e) {
        return handleDefault(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(NotRemoveWithRelationshipsException.class)
    ProblemDetail handle(NotRemoveWithRelationshipsException e) {
        return handleDefault(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(TypeUserInvalidException.class)
    ProblemDetail handle(TypeUserInvalidException e) {
        return handleDefault(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(DependentBirthBeforeUserException.class)
    ProblemDetail handle(DependentBirthBeforeUserException e) {
        return handleDefault(HttpStatus.BAD_REQUEST, e);
    }
}
