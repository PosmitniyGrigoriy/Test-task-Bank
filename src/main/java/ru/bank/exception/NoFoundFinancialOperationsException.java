package ru.bank.exception;

import org.springframework.http.HttpStatus;
import ru.bank.base.BaseException;

import java.time.LocalDate;

public class NoFoundFinancialOperationsException extends BaseException {
    public NoFoundFinancialOperationsException(LocalDate dateFrom, LocalDate dateTo) {
        super(HttpStatus.NOT_FOUND, String.format("No found financial operations from date %s to date %s.",
                dateFrom, dateTo));
    }
}
