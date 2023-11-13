package ru.bank.exception;

import org.springframework.http.HttpStatus;
import ru.bank.base.BaseException;

public class FailedCreateHttpConnectionException extends BaseException {
    public FailedCreateHttpConnectionException(String ex) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Failed create an http connection. Check base link, " +
                "request type, headers. Exception: " + ex);
    }
}
