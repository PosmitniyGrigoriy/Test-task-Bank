package ru.bank.exception;

import org.springframework.http.HttpStatus;
import ru.bank.base.BaseException;
import ru.bank.enumeration.CurrencyEnum;

import java.time.LocalDate;

public class EntityDoesNotExistException extends BaseException {

    public EntityDoesNotExistException(LocalDate dateFrom,
                                       LocalDate dateTo,
                                       CurrencyEnum vchCode) {
        super(HttpStatus.NOT_FOUND, String.format("No found %s exchange rates from date %s to date %s",
                vchCode, dateFrom, dateTo));
    }

}
