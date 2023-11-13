package ru.bank.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bank.dto.response.CurrencyResponseDto;
import ru.bank.entity.CurrencyEntity;
import ru.bank.enumeration.CurrencyEnum;
import ru.bank.integrations.ru.cbr.ValuteCursOnDate;
import ru.bank.mapper.CurrencyMapper;
import ru.bank.repository.CurrencyRepository;
import ru.bank.utils.SoapUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    public Optional<CurrencyEntity> findByVchCode(CurrencyEnum vchCode) {
        return currencyRepository.findByVchCode(vchCode.name());
    }

    @Transactional
    public List<CurrencyResponseDto> upload() {
        List<ValuteCursOnDate> valuteCursOnDateList = SoapUtils.getCurrencyRatesByDate(LocalDate.now().toString());
        List<CurrencyResponseDto> currenciesResponse = currencyMapper.mapValuteCursOnDateListToDtoList(valuteCursOnDateList);
        currenciesResponse.removeIf(currency -> !currency.getVchCode().equals(CurrencyEnum.USD.name()) &&
                !currency.getVchCode().equals(CurrencyEnum.EUR.name()));
        List<CurrencyEntity> currencies = currencyMapper.mapDtoListToEntityList(currenciesResponse);
        currencies.forEach(currency -> {
            if (currency.getVchCode().equals(CurrencyEnum.USD.name())) { currency.setId(1); }
            if (currency.getVchCode().equals(CurrencyEnum.EUR.name())) { currency.setId(2); }
        });
        currencyRepository.saveAll(currencies);
        log.info("Successfully loaded exchange rates for USD and EUR");
        return currenciesResponse;
    }

}
