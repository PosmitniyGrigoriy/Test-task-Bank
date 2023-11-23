package ru.bank.utils;

import ru.bank.entity.CurrencyEntity;

import java.util.List;

import static ru.bank.constants.TestConstants.*;

public class CurrencyUtils {

    private CurrencyUtils() { }

    public static List<CurrencyEntity> createCurrencies(String firstVchCode, String secondVchCode) {
        CurrencyEntity currency = new CurrencyEntity();
        currency.setVunitRate(VUNIT_RATE);
        currency.setVchCode(firstVchCode);
        currency.setDateAt(DATE_FROM);

        CurrencyEntity currency2 = new CurrencyEntity();
        currency2.setVunitRate(VUNIT_RATE);
        currency2.setVchCode(secondVchCode);
        currency.setDateAt(DATE_TO);

        return List.of(currency, currency2);
    }

}
