package ru.bank.service;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.bank.dto.request.FinancialOperationRequestDto;
import ru.bank.dto.response.ConvertedFinancialOperationResponseDto;
import ru.bank.dto.response.FinancialOperationResponseDto;
import ru.bank.entity.CurrencyEntity;
import ru.bank.entity.FinancialOperationEntity;
import ru.bank.enumeration.CurrencyEnum;
import ru.bank.exception.EntityDoesNotExistException;
import ru.bank.exception.NoFoundFinancialOperationsException;
import ru.bank.mapper.FinancialOperationMapper;
import ru.bank.repository.FinancialOperationRepository;
import ru.bank.utils.CurrencyUtils;

import javax.validation.*;
import javax.validation.metadata.ConstraintDescriptor;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.bank.constants.TestConstants.*;

public class FinancialOperationServiceTest {

    @Mock
    private FinancialOperationRepository financialOperationRepository;

    @Mock
    private FinancialOperationMapper financialOperationMapper;

    @Mock
    private Validator validator;

    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private FinancialOperationService financialOperationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddAllSuccess() {
        // Preparation
        List<FinancialOperationEntity> financialOperations = List.of(
                new FinancialOperationEntity(LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), INCOME, RUB_FINANCIAL_OPERATION_FIRST),
                new FinancialOperationEntity(LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), INCOME, RUB_FINANCIAL_OPERATION_SECOND),
                new FinancialOperationEntity(LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), INCOME, RUB_FINANCIAL_OPERATION_THIRD),
                new FinancialOperationEntity(LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_3), INCOME, RUB_FINANCIAL_OPERATION_FOURTH),
                new FinancialOperationEntity(LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_3), INCOME, RUB_FINANCIAL_OPERATION_FIFTH),
                new FinancialOperationEntity(LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_5), INCOME, RUB_FINANCIAL_OPERATION_FIRST),
                new FinancialOperationEntity(LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_9), INCOME, RUB_FINANCIAL_OPERATION_SIXTH),
                new FinancialOperationEntity(LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_9), INCOME, RUB_FINANCIAL_OPERATION_SEVENTH)
        );
        List<FinancialOperationResponseDto> response = List.of(
                new FinancialOperationResponseDto(ONE,   LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), INCOME, RUB_FINANCIAL_OPERATION_FIRST),
                new FinancialOperationResponseDto(TWO,   LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), INCOME, RUB_FINANCIAL_OPERATION_SECOND),
                new FinancialOperationResponseDto(THREE, LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), INCOME, RUB_FINANCIAL_OPERATION_THIRD),
                new FinancialOperationResponseDto(FOUR,  LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_3), INCOME, RUB_FINANCIAL_OPERATION_FOURTH),
                new FinancialOperationResponseDto(FIVE,  LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_3), INCOME, RUB_FINANCIAL_OPERATION_FIFTH),
                new FinancialOperationResponseDto(SIX,   LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_5), INCOME, RUB_FINANCIAL_OPERATION_FIRST),
                new FinancialOperationResponseDto(SEVEN, LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_9), INCOME, RUB_FINANCIAL_OPERATION_SIXTH),
                new FinancialOperationResponseDto(EIGHT, LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_9), INCOME, RUB_FINANCIAL_OPERATION_SEVENTH)
        );
        when(validator.validate(financialOperations)).thenReturn(Collections.emptySet());
        when(financialOperationRepository.saveAll(financialOperations)).thenReturn(financialOperations);
        when(financialOperationMapper.mapEntitiesToDtoList(financialOperations)).thenReturn(response);

        // Method Call
        List<FinancialOperationResponseDto> result = financialOperationService.addAll(financialOperations);

        // Checks
        verify(validator).validate(financialOperations);
        verify(financialOperationRepository).saveAll(financialOperations);
        verify(financialOperationMapper).mapEntitiesToDtoList(financialOperations);
        assertEquals(response, result);
        assertEquals(financialOperations.size(), result.size());
    }

    @Test
    public void testAddAllWithConstraintViolationToFieldDate() {
        // Preparation
        List<FinancialOperationEntity> financialOperations = List.of(
                new FinancialOperationEntity(null, INCOME, RUB_FINANCIAL_OPERATION_FIRST)
        );
        Set<ConstraintViolation<List<FinancialOperationEntity>>> violations = new HashSet<>();
        violations.add(createConstraintViolationToFieldDate());
        when(validator.validate(financialOperations)).thenReturn(violations);

        // Method Call and Check
        assertThrows(ConstraintViolationException.class, () -> {
            financialOperationService.addAll(financialOperations);
        });
    }

    @Test
    public void testAddAllWithConstraintViolationToFieldDescription() {
        // Preparation
        List<FinancialOperationEntity> financialOperations = List.of(
                new FinancialOperationEntity(LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), null, RUB_FINANCIAL_OPERATION_FIRST)
        );
        Set<ConstraintViolation<List<FinancialOperationEntity>>> violations = new HashSet<>();
        violations.add(createConstraintViolationToFieldDescription());
        when(validator.validate(financialOperations)).thenReturn(violations);

        // Method Call and Check
        assertThrows(ConstraintViolationException.class, () -> {
            financialOperationService.addAll(financialOperations);
        });
    }

    @Test
    public void testAddAllWithConstraintViolationToFieldAmount() {
        // Preparation
        List<FinancialOperationEntity> financialOperations = List.of(
                new FinancialOperationEntity(LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), INCOME, ZERO)
        );
        Set<ConstraintViolation<List<FinancialOperationEntity>>> violations = new HashSet<>();
        violations.add(createConstraintViolationToFieldAmount());
        when(validator.validate(financialOperations)).thenReturn(violations);

        // Method Call and Check
        assertThrows(ConstraintViolationException.class, () -> {
            financialOperationService.addAll(financialOperations);
        });
    }

    @Test
    public void testAddAllWithConstraintViolationToAllFields() {
        // Preparation
        List<FinancialOperationEntity> financialOperations = List.of(
                new FinancialOperationEntity(null, null, ZERO)
        );
        Set<ConstraintViolation<List<FinancialOperationEntity>>> violations = new HashSet<>();
        violations.add(createConstraintViolationToFieldDate());
        violations.add(createConstraintViolationToFieldDescription());
        violations.add(createConstraintViolationToFieldAmount());
        when(validator.validate(financialOperations)).thenReturn(violations);

        // Method Call and Check
        assertThrows(ConstraintViolationException.class, () -> {
            financialOperationService.addAll(financialOperations);
        });
    }

    @Test
    public void testRecalculateAllSuccess() {
        // Preparation
        List<FinancialOperationEntity> financialOperations = List.of(
                new FinancialOperationEntity(LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), INCOME, RUB_FINANCIAL_OPERATION_FIRST),
                new FinancialOperationEntity(LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), INCOME, RUB_FINANCIAL_OPERATION_SECOND),
                new FinancialOperationEntity(LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), INCOME, RUB_FINANCIAL_OPERATION_THIRD),
                new FinancialOperationEntity(LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_3), INCOME, RUB_FINANCIAL_OPERATION_FOURTH),
                new FinancialOperationEntity(LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_3), INCOME, RUB_FINANCIAL_OPERATION_FIFTH),
                new FinancialOperationEntity(LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_5), INCOME, RUB_FINANCIAL_OPERATION_FIRST)
        );
        List<ConvertedFinancialOperationResponseDto> response = List.of(
                new ConvertedFinancialOperationResponseDto(ONE,   LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), INCOME, RUB_FINANCIAL_OPERATION_FIRST),
                new ConvertedFinancialOperationResponseDto(TWO,   LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), INCOME, RUB_FINANCIAL_OPERATION_SECOND),
                new ConvertedFinancialOperationResponseDto(THREE, LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), INCOME, RUB_FINANCIAL_OPERATION_THIRD),
                new ConvertedFinancialOperationResponseDto(FOUR,  LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_3), INCOME, RUB_FINANCIAL_OPERATION_FOURTH),
                new ConvertedFinancialOperationResponseDto(FIVE,  LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_3), INCOME, RUB_FINANCIAL_OPERATION_FIFTH),
                new ConvertedFinancialOperationResponseDto(SIX,   LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_5), INCOME, RUB_FINANCIAL_OPERATION_FIRST)
        );
        List<ConvertedFinancialOperationResponseDto> response2 = List.of(
                new ConvertedFinancialOperationResponseDto(ONE,   LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), INCOME, USD_FINANCIAL_OPERATION_FIRST),
                new ConvertedFinancialOperationResponseDto(TWO,   LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), INCOME, USD_FINANCIAL_OPERATION_SECOND),
                new ConvertedFinancialOperationResponseDto(THREE, LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), INCOME, USD_FINANCIAL_OPERATION_THIRD),
                new ConvertedFinancialOperationResponseDto(FOUR,  LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_3), INCOME, USD_FINANCIAL_OPERATION_FOURTH),
                new ConvertedFinancialOperationResponseDto(FIVE,  LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_3), INCOME, USD_FINANCIAL_OPERATION_FIFTH),
                new ConvertedFinancialOperationResponseDto(SIX,   LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_5), INCOME, USD_FINANCIAL_OPERATION_SIXTH)
        );
        FinancialOperationRequestDto conversionParameters = new FinancialOperationRequestDto(
                LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_5), CurrencyEnum.USD);
        Set<ConstraintViolation<FinancialOperationRequestDto>> violations = Collections.emptySet();
        List<CurrencyEntity> exchangeRates = CurrencyUtils.createCurrencies(CurrencyEnum.USD.name(), CurrencyEnum.USD.name());
                when(validator.validate(conversionParameters)).thenReturn(violations);
        when(currencyService.findByDateRangeAndVchCode(DATE_FROM, DATE_TO, CurrencyEnum.USD))
                .thenReturn(Optional.of(exchangeRates));
        when(financialOperationRepository.getFinancialOperationsForPeriod(
                conversionParameters.getDateFrom(), conversionParameters.getDateTo()))
                .thenReturn(Optional.of(financialOperations));
        List<ConvertedFinancialOperationResponseDto> convertedFinancialOperations = response;
        when(financialOperationMapper.mapEntitiesToConvertedDtoList(financialOperations))
                .thenReturn(response);
        financialOperationService = spy(financialOperationService);
//        doReturn(response2)
//                .when(financialOperationService)
//                .convertAmountToCurrency(response, exchangeRates);
        convertedFinancialOperations = response2;

        // Method Call
        List<ConvertedFinancialOperationResponseDto> result =
                financialOperationService.recalculateAll(conversionParameters);
        System.out.println(result);
        // Checks
        verify(validator).validate(conversionParameters);
        verify(currencyService).findByDateRangeAndVchCode(DATE_FROM, DATE_TO, CurrencyEnum.USD);
        verify(financialOperationRepository).getFinancialOperationsForPeriod(
                conversionParameters.getDateFrom(), conversionParameters.getDateTo());
        verify(financialOperationMapper).mapEntitiesToConvertedDtoList(financialOperations);
        assertEquals(convertedFinancialOperations, result);
        assertEquals(convertedFinancialOperations.size(), result.size());
        assertEquals(USD_FINANCIAL_OPERATION_FIRST, result.get(ZERO).getAmount(),  ACCURACY_TO_HUNDREDTHS);
        assertEquals(USD_FINANCIAL_OPERATION_SECOND,   result.get(ONE).getAmount(),   ACCURACY_TO_TENTHS);
        assertEquals(USD_FINANCIAL_OPERATION_THIRD, result.get(TWO).getAmount(),   ACCURACY_TO_HUNDREDTHS);
        assertEquals(USD_FINANCIAL_OPERATION_FOURTH, result.get(THREE).getAmount(), ACCURACY_TO_TENTHS);
        assertEquals(USD_FINANCIAL_OPERATION_FIFTH,   result.get(FOUR).getAmount(),  ACCURACY_TO_INTEGERS);
        assertEquals(USD_FINANCIAL_OPERATION_SIXTH, result.get(FIVE).getAmount(),  ACCURACY_TO_HUNDREDTHS);
    }

    @Test
    public void testRecalculateAllThrowsNoFoundFinancialOperationsException() {
        // Preparation
        FinancialOperationRequestDto conversionParameters = new FinancialOperationRequestDto(
                LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_5), CurrencyEnum.USD);
        Set<ConstraintViolation<FinancialOperationRequestDto>> violations = Collections.emptySet();
        when(validator.validate(conversionParameters)).thenReturn(violations);
        when(currencyService.findByDateRangeAndVchCode(DATE_FROM, DATE_TO, CurrencyEnum.USD))
                .thenReturn(Optional.of(CurrencyUtils.createCurrencies(CurrencyEnum.USD.name(), CurrencyEnum.USD.name())));
        when(financialOperationRepository.getFinancialOperationsForPeriod(
                conversionParameters.getDateFrom(), conversionParameters.getDateTo()))
                .thenReturn(Optional.empty());

        // Method Call and Check
        assertThrows(NoFoundFinancialOperationsException.class, () -> {
            financialOperationService.recalculateAll(conversionParameters);
        });
    }

    @Test
    public void testRecalculateAllThrowsEntityDoesNotExistException() {
        // Preparation
        FinancialOperationRequestDto conversionParameters = new FinancialOperationRequestDto(
                LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_5), CurrencyEnum.USD);
        Set<ConstraintViolation<FinancialOperationRequestDto>> violations = Collections.emptySet();
        when(validator.validate(conversionParameters)).thenReturn(violations);
        when(currencyService.findByDateRangeAndVchCode(DATE_FROM, DATE_TO, CurrencyEnum.USD))
                .thenReturn(Optional.empty());

        // Method Call and Check
        assertThrows(EntityDoesNotExistException.class, () -> {
            financialOperationService.recalculateAll(conversionParameters);
        });
    }

    @Test
    public void testAddAllWithConstraintViolationToFieldVchCode() {
        // Preparation
        FinancialOperationRequestDto conversionParameters = new FinancialOperationRequestDto(
                LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_1), LocalDate.of(YEAR, MONTH, DAY_OF_MONTH_5), null);
        Set<ConstraintViolation<FinancialOperationRequestDto>> violations = new HashSet<>();
        violations.add(createConstraintViolationToFieldVchCode());
        when(validator.validate(conversionParameters)).thenReturn(violations);

        // Method Call and Check
        assertThrows(ConstraintViolationException.class, () -> {
            financialOperationService.recalculateAll(conversionParameters);
        });
    }

    private ConstraintViolation<List<FinancialOperationEntity>> createConstraintViolationToFieldDate() {
        return new ConstraintViolation<>() {
            @Override
            public String getMessage() { return ERROR_NOT_NULL; }

            @Override
            public String getMessageTemplate() { return PATH_NOT_NULL; }

            @Override
            public Object getInvalidValue() { return null; }

            @Override
            public Path getPropertyPath() { return PathImpl.createPathFromString(FIELD_NAME_DATE); }

            @Override
            public List<FinancialOperationEntity> getRootBean() { return null; }

            @Override
            public Class<List<FinancialOperationEntity>> getRootBeanClass() { return null; }

            @Override
            public Object getLeafBean() { return null; }

            @Override
            public Object[] getExecutableParameters() { return new Object[ZERO]; }

            @Override
            public Object getExecutableReturnValue() { return null; }

            @Override
            public ConstraintDescriptor<?> getConstraintDescriptor() { return null; }

            @Override
            public <U> U unwrap(Class<U> aClass) { return null; }
        };
    }

    private ConstraintViolation<List<FinancialOperationEntity>> createConstraintViolationToFieldDescription() {
        return new ConstraintViolation<>() {
            @Override
            public String getMessage() { return ERROR_NOT_BLANK; }

            @Override
            public String getMessageTemplate() { return PATH_NOT_BLANK; }

            @Override
            public Object getInvalidValue() { return ""; }

            @Override
            public Path getPropertyPath() { return PathImpl.createPathFromString(FIELD_NAME_DESCRIPTION); }

            @Override
            public List<FinancialOperationEntity> getRootBean() { return null; }

            @Override
            public Class<List<FinancialOperationEntity>> getRootBeanClass() { return null; }

            @Override
            public Object getLeafBean() { return null; }

            @Override
            public Object[] getExecutableParameters() { return new Object[ZERO]; }

            @Override
            public Object getExecutableReturnValue() { return null; }

            @Override
            public ConstraintDescriptor<?> getConstraintDescriptor() { return null; }

            @Override
            public <U> U unwrap(Class<U> aClass) { return null; }
        };
    }

    private ConstraintViolation<List<FinancialOperationEntity>> createConstraintViolationToFieldAmount() {
        return new ConstraintViolation<>() {
            @Override
            public String getMessage() { return ERROR_LESS_THAN_ONE; }

            @Override
            public String getMessageTemplate() { return PATH_LESS_THAN_ONE; }

            @Override
            public Object getInvalidValue() { return ZERO; }

            @Override
            public Path getPropertyPath() { return PathImpl.createPathFromString(FIELD_NAME_AMOUNT); }

            @Override
            public List<FinancialOperationEntity> getRootBean() { return null; }

            @Override
            public Class<List<FinancialOperationEntity>> getRootBeanClass() { return null; }

            @Override
            public Object getLeafBean() { return null; }

            @Override
            public Object[] getExecutableParameters() { return new Object[ZERO]; }

            @Override
            public Object getExecutableReturnValue() { return null; }

            @Override
            public ConstraintDescriptor<?> getConstraintDescriptor() { return null; }

            @Override
            public <U> U unwrap(Class<U> aClass) { return null; }
        };
    }

    private ConstraintViolation<FinancialOperationRequestDto> createConstraintViolationToFieldVchCode() {
        return new ConstraintViolation<>() {
            @Override
            public String getMessage() { return ERROR_NOT_NULL; }

            @Override
            public String getMessageTemplate() { return PATH_NOT_NULL; }

            @Override
            public Object getInvalidValue() { return ZERO; }

            @Override
            public Path getPropertyPath() { return PathImpl.createPathFromString(FIELD_NAME_VCH_CODE); }

            @Override
            public FinancialOperationRequestDto getRootBean() { return null; }

            @Override
            public Class<FinancialOperationRequestDto> getRootBeanClass() { return null; }

            @Override
            public Object getLeafBean() { return null; }

            @Override
            public Object[] getExecutableParameters() { return new Object[ZERO]; }

            @Override
            public Object getExecutableReturnValue() { return null; }

            @Override
            public ConstraintDescriptor<?> getConstraintDescriptor() { return null; }

            @Override
            public <U> U unwrap(Class<U> aClass) { return null; }
        };
    }

}
