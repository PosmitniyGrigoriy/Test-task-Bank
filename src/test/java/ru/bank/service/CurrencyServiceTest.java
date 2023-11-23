package ru.bank.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.bank.dto.response.CurrencyResponseDto;
import ru.bank.entity.CurrencyEntity;
import ru.bank.enumeration.CurrencyEnum;
import ru.bank.integrations.ru.cbr.ValuteCursOnDate;
import ru.bank.mapper.CurrencyMapper;
import ru.bank.repository.CurrencyRepository;
import ru.bank.utils.CurrencyUtils;
import ru.bank.utils.HttpUtils;
import ru.bank.utils.SoapUtils;

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static ru.bank.constants.TestConstants.*;

public class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private CurrencyMapper currencyMapper;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByVchCodeByUSD() {
        // Preparation
        List<CurrencyEntity> currencies = CurrencyUtils.createCurrencies(CurrencyEnum.USD.name(), CurrencyEnum.USD.name());
        when(currencyRepository.findByDateRangeAndVchCode(DATE_FROM, DATE_TO, CurrencyEnum.USD.name()))
                .thenReturn(Optional.of(currencies));

        // Method Call
        Optional<List<CurrencyEntity>> result =
                currencyService.findByDateRangeAndVchCode(DATE_FROM, DATE_TO, CurrencyEnum.USD);

        // Checks
        assertTrue(result.isPresent());
        assertEquals(currencies, result.get());
        assertEquals(2, result.get().size());
    }

    @Test
    public void testFindByVchCodeByEUR() {
        // Preparation
        List<CurrencyEntity> currencies = CurrencyUtils.createCurrencies(CurrencyEnum.EUR.name(), CurrencyEnum.EUR.name());
        when(currencyRepository.findByDateRangeAndVchCode(DATE_FROM, DATE_TO, CurrencyEnum.EUR.name()))
                .thenReturn(Optional.of(currencies));

        // Method Call
        Optional<List<CurrencyEntity>> result =
                currencyService.findByDateRangeAndVchCode(DATE_FROM, DATE_TO, CurrencyEnum.EUR);

        // Checks
        assertTrue(result.isPresent());
        assertEquals(currencies, result.get());
        assertEquals(2, result.get().size());
    }

    @Disabled
    @Test
    public void testUpload() {
        // Preparation
        List<ValuteCursOnDate> valuteCursOnDateList = new ArrayList<>();
        valuteCursOnDateList.add(new ValuteCursOnDate(AUSTRALIAN_DOLLAR,      BigDecimal.valueOf(ONE),     BigDecimal.valueOf(VUNIT_RATE_AUSTRALIAN_DOLLAR),      VCODE_AUSTRALIAN_DOLLAR,      VCH_CODE_AUD, VUNIT_RATE_AUSTRALIAN_DOLLAR));
        valuteCursOnDateList.add(new ValuteCursOnDate(AZERBAIJANI_MANAT,      BigDecimal.valueOf(ONE),     BigDecimal.valueOf(VUNIT_RATE_AZERBAIJANI_MANAT),      VCODE_AZERBAIJANI_MANAT,      VCH_CODE_AZN, VUNIT_RATE_AZERBAIJANI_MANAT));
        valuteCursOnDateList.add(new ValuteCursOnDate(BRITISH_POUND_STERLING, BigDecimal.valueOf(ONE),     BigDecimal.valueOf(VUNIT_RATE_BRITISH_POUND_STERLING), VCODE_BRITISH_POUND_STERLING, VCH_CODE_GBP, VUNIT_RATE_BRITISH_POUND_STERLING));
        valuteCursOnDateList.add(new ValuteCursOnDate(ARMENIAN_DRAM,          BigDecimal.valueOf(HUNDRED), BigDecimal.valueOf(VUNIT_RATE_ARMENIAN_DRAM),          VCODE_ARMENIAN_DRAM,          VCH_CODE_AMD, VUNIT_RATE_ARMENIAN_DRAM));
        valuteCursOnDateList.add(new ValuteCursOnDate(US_DOLLAR,              BigDecimal.valueOf(ONE),     BigDecimal.valueOf(VUNIT_RATE_US_DOLLAR),              VCODE_US_DOLLAR,              VCH_CODE_USD, VUNIT_RATE_US_DOLLAR));
        valuteCursOnDateList.add(new ValuteCursOnDate(EURO,                   BigDecimal.valueOf(ONE),     BigDecimal.valueOf(VUNIT_RATE_US_EURO),                VCODE_US_EURO,                VCH_CODE_EUR, VUNIT_RATE_US_EURO));

        List<CurrencyResponseDto> currenciesResponse = new ArrayList<>();
        currenciesResponse.add(new CurrencyResponseDto(AUSTRALIAN_DOLLAR,      BigDecimal.valueOf(ONE),     BigDecimal.valueOf(VUNIT_RATE_AUSTRALIAN_DOLLAR),      VCODE_AUSTRALIAN_DOLLAR,      VCH_CODE_AUD, VUNIT_RATE_AUSTRALIAN_DOLLAR));
        currenciesResponse.add(new CurrencyResponseDto(AZERBAIJANI_MANAT,      BigDecimal.valueOf(ONE),     BigDecimal.valueOf(VUNIT_RATE_AZERBAIJANI_MANAT),      VCODE_AZERBAIJANI_MANAT,      VCH_CODE_AZN, VUNIT_RATE_AZERBAIJANI_MANAT));
        currenciesResponse.add(new CurrencyResponseDto(BRITISH_POUND_STERLING, BigDecimal.valueOf(ONE),     BigDecimal.valueOf(VUNIT_RATE_BRITISH_POUND_STERLING), VCODE_BRITISH_POUND_STERLING, VCH_CODE_GBP, VUNIT_RATE_BRITISH_POUND_STERLING));
        currenciesResponse.add(new CurrencyResponseDto(ARMENIAN_DRAM,          BigDecimal.valueOf(HUNDRED), BigDecimal.valueOf(VUNIT_RATE_ARMENIAN_DRAM),          VCODE_ARMENIAN_DRAM,          VCH_CODE_AMD, VUNIT_RATE_ARMENIAN_DRAM));
        currenciesResponse.add(new CurrencyResponseDto(US_DOLLAR,              BigDecimal.valueOf(ONE),     BigDecimal.valueOf(VUNIT_RATE_US_DOLLAR),              VCODE_US_DOLLAR,              VCH_CODE_USD, VUNIT_RATE_US_DOLLAR));
        currenciesResponse.add(new CurrencyResponseDto(EURO,                   BigDecimal.valueOf(ONE),     BigDecimal.valueOf(VUNIT_RATE_US_EURO),                VCODE_US_EURO,                VCH_CODE_EUR, VUNIT_RATE_US_EURO));

        List<CurrencyEntity> currencies = List.of(
                new CurrencyEntity(US_DOLLAR, BigDecimal.valueOf(ONE), BigDecimal.valueOf(VUNIT_RATE_US_DOLLAR), VCODE_US_DOLLAR, VCH_CODE_USD, VUNIT_RATE_US_DOLLAR, LocalDate.now()),
                new CurrencyEntity(EURO,      BigDecimal.valueOf(ONE), BigDecimal.valueOf(VUNIT_RATE_US_EURO),   VCODE_US_EURO,   VCH_CODE_EUR, VUNIT_RATE_US_EURO, LocalDate.now())
        );

        HttpURLConnection mockedConnection = mock(HttpURLConnection.class);
        when(HttpUtils.createConnection()).thenReturn(mockedConnection);
        when(SoapUtils.getCurrencyRatesByDate(anyString())).thenReturn(valuteCursOnDateList);
        when(currencyMapper.mapValuteCursOnDateListToDtoList(valuteCursOnDateList)).thenReturn(currenciesResponse);
        currenciesResponse = List.of(
                new CurrencyResponseDto(US_DOLLAR, BigDecimal.valueOf(ONE), BigDecimal.valueOf(VUNIT_RATE_US_DOLLAR), VCODE_US_DOLLAR, VCH_CODE_USD, VUNIT_RATE_US_DOLLAR),
                new CurrencyResponseDto(EURO,      BigDecimal.valueOf(ONE), BigDecimal.valueOf(VUNIT_RATE_US_EURO),   VCODE_US_EURO,   VCH_CODE_EUR, VUNIT_RATE_US_EURO)
        );
        when(currencyMapper.mapDtoListToEntityList(currenciesResponse)).thenReturn(currencies);
        when(currencyRepository.saveAll(anyList())).thenReturn(currencies);

        // Method Call
        List<CurrencyResponseDto> result = currencyService.upload();

        // Checks
        verify(currencyRepository, times(ONE)).saveAll(currencies);
        assertEquals(currenciesResponse, result);
        assertEquals(2, result.size());
    }

}