package link.toocool.vacancydiary.exception;

import org.springframework.http.HttpStatus;

public class RestException extends HttpStatusException {

    public RestException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
