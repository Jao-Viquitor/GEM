package gem.api.common.exception;

import gem.api.common.enums.HttpCode;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.sql.SQLException;
import org.springframework.security.core.AuthenticationException;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> notFound() {
        return ResponseEntity.status(HttpCode.NOT_FOUND.getValue()).body(HttpCode.NOT_FOUND.getReasonPhrase());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> unauthorized() {
        return ResponseEntity.status(HttpCode.UNAUTHORIZED.getValue()).body(HttpCode.UNAUTHORIZED.getReasonPhrase());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> forbidden() {
        return ResponseEntity.status(HttpCode.FORBIDDEN.getValue()).body(HttpCode.FORBIDDEN.getReasonPhrase());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> badRequest(MethodArgumentNotValidException handler){
        var erros = handler.getFieldErrors();
        return ResponseEntity.status(HttpCode.BAD_REQUEST.getValue()).body(erros.stream().map(errorDate::new).toList());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> methodNotAllowed() {
        return ResponseEntity.status(HttpCode.METHOD_NOT_ALLOWED.getValue()).body(HttpCode.METHOD_NOT_ALLOWED.getReasonPhrase());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> unprocessableEntity() {
        return ResponseEntity.status(HttpCode.UNPROCESSABLE_ENTITY.getValue()).body(HttpCode.UNPROCESSABLE_ENTITY.getReasonPhrase());
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> internalServerError() {
        return ResponseEntity.status(HttpCode.INTERNAL_SERVER_ERROR.getValue()).body(HttpCode.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> badRequest() {
        return ResponseEntity.status(HttpCode.BAD_REQUEST.getValue()).body(HttpCode.BAD_REQUEST.getReasonPhrase());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> internalServerError(Exception e) {
        return ResponseEntity.status(HttpCode.INTERNAL_SERVER_ERROR.getValue()).body(HttpCode.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    private record errorDate(String value, String message){
        public errorDate(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
