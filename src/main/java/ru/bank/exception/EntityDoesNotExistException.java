package ru.bank.exception;

import org.springframework.http.HttpStatus;
import ru.bank.base.BaseException;
import ru.bank.enumeration.CurrencyEnum;

public class EntityDoesNotExistException extends BaseException {

    public EntityDoesNotExistException(Class<?> clazz, CurrencyEnum vchCode) {
        super(HttpStatus.NOT_FOUND, String.format("Not found an object of class %s with vchCode %s", clazz, vchCode));
    }

}
