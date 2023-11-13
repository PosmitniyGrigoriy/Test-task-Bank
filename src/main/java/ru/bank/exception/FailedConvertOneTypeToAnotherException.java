package ru.bank.exception;

import org.springframework.http.HttpStatus;
import ru.bank.base.BaseException;

public class FailedConvertOneTypeToAnotherException extends BaseException {
    public FailedConvertOneTypeToAnotherException(Class<?> firstType, Class<?> secondType, String ex) {
        super(HttpStatus.BAD_REQUEST, String.format("Failed convert one type to another. %s in %s. Exception: %s",
                firstType, secondType, ex));
    }
}
