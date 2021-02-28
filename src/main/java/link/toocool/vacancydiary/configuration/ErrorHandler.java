package link.toocool.vacancydiary.configuration;

import link.toocool.vacancydiary.exception.HttpStatusException;
import link.toocool.vacancydiary.exception.JwtAuthenticationException;
import link.toocool.vacancydiary.exception.RestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleError500(HttpServletRequest request, Exception e) {
        log.error("internal_server_error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleError404(NoHandlerFoundException e) {
        log.info("no_handler_found_exception", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No adapter for handler.");
    }

    @ExceptionHandler(RestException.class)
    public ResponseEntity<String> generateRestExceptionHandler(
            HttpStatusException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
    }

    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<String> generateJwtAuthenticationExceptionHandler(
            HttpStatusException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> generateUsernameNotFoundException(
            UsernameNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
