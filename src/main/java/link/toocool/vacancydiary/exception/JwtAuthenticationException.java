package link.toocool.vacancydiary.exception;

import org.springframework.http.HttpStatus;

public class JwtAuthenticationException extends HttpStatusException {

    public JwtAuthenticationException(String msg) {
        super(msg);
    }

    public JwtAuthenticationException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
