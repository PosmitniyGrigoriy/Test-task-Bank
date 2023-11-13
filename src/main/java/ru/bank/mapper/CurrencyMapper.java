package ru.bank.mapper;

import org.springframework.stereotype.Component;
import ru.bank.dto.response.CurrencyResponseDto;
import ru.bank.entity.CurrencyEntity;
import ru.bank.integrations.ru.cbr.ValuteCursOnDate;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyMapper {

    public List<CurrencyResponseDto> mapValuteCursOnDateListToDtoList(List<ValuteCursOnDate> valuteCursOnDate) {
        if (valuteCursOnDate == null) {
            return null;
        }
        List<CurrencyResponseDto> list = new ArrayList<>(valuteCursOnDate.size());
        for (ValuteCursOnDate currency : valuteCursOnDate) {
            list.add(dtoToDto(currency));
        }
        return list;
    }

    public CurrencyResponseDto dtoToDto(ValuteCursOnDate valuteCursOnDate) {
        if (valuteCursOnDate == null) {
            return null;
        }
        CurrencyResponseDto currency = new CurrencyResponseDto();
        currency.setVname(valuteCursOnDate.getVname());
        currency.setVnom(valuteCursOnDate.getVnom());
        currency.setVcurs(valuteCursOnDate.getVcurs());
        currency.setVcode(valuteCursOnDate.getVcode());
        currency.setVchCode(valuteCursOnDate.getVchCode());
        currency.setVunitRate(valuteCursOnDate.getVunitRate());
        return currency;
    }

    public List<CurrencyEntity> mapDtoListToEntityList(List<CurrencyResponseDto> currencies) {
        if (currencies == null) {
            return null;
        }
        List<CurrencyEntity> list = new ArrayList<>(currencies.size());
        for (CurrencyResponseDto currency : currencies) {
            list.add(dtoToEntity(currency));
        }
        return list;
    }

    public CurrencyEntity dtoToEntity(CurrencyResponseDto currencyResponseDto) {
        if (currencyResponseDto == null) {
            return null;
        }
        CurrencyEntity currency = new CurrencyEntity();
        currency.setVname(currencyResponseDto.getVname());
        currency.setVnom(currencyResponseDto.getVnom());
        currency.setVcurs(currencyResponseDto.getVcurs());
        currency.setVcode(currencyResponseDto.getVcode());
        currency.setVchCode(currencyResponseDto.getVchCode());
        currency.setVunitRate(currencyResponseDto.getVunitRate());
        return currency;
    }
    
}
