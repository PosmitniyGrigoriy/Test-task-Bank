package ru.bank.exception;

import org.springframework.http.HttpStatus;
import ru.bank.base.BaseException;

public class FailedGetXmlException extends BaseException {
    public FailedGetXmlException(String ex) {
        super(HttpStatus.NOT_FOUND, "Could not get xml. Exception: " + ex);
    }
}
