package link.toocool.vacancydiary.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class HttpStatusException extends RuntimeException {

    protected HttpStatus httpStatus;

    public HttpStatusException(String msg) {
        super(msg);
    }

    public HttpStatusException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
