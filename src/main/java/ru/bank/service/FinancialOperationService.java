package ru.bank.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bank.dto.request.FinancialOperationRequestDto;
import ru.bank.dto.response.FinancialOperationResponseDto;
import ru.bank.dto.response.ConvertedFinancialOperationResponseDto;
import ru.bank.entity.CurrencyEntity;
import ru.bank.entity.FinancialOperationEntity;
import ru.bank.exception.EntityDoesNotExistException;
import ru.bank.exception.NoFoundFinancialOperationsException;
import ru.bank.mapper.FinancialOperationMapper;
import ru.bank.repository.FinancialOperationRepository;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class FinancialOperationService {

    private final FinancialOperationRepository financialOperationRepository;
    private final FinancialOperationMapper financialOperationMapper;
    private final Validator validator;
    private final CurrencyService currencyService;

    @Transactional
    public List<FinancialOperationResponseDto> addAll(List<FinancialOperationEntity> financialOperations) {
        Set<ConstraintViolation<List<FinancialOperationEntity>>> violations = validator.validate(financialOperations);
        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        financialOperations = financialOperationRepository.saveAll(financialOperations);
        log.info("Successfully added financial operations in rubles");
        return financialOperationMapper.mapEntitiesToDtoList(financialOperations);
    }

    public List<ConvertedFinancialOperationResponseDto> recalculateAll(FinancialOperationRequestDto conversionParameters) {
        Set<ConstraintViolation<FinancialOperationRequestDto>> violations = validator.validate(conversionParameters);
        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        double vunitRate = currencyService.findByVchCode(conversionParameters.getVchCode())
                .map(CurrencyEntity::getVunitRate)
                .orElseThrow(() -> new EntityDoesNotExistException(CurrencyEntity.class, conversionParameters.getVchCode()));
        List<FinancialOperationEntity> periodFinancialOperations =
                financialOperationRepository.getFinancialOperationsForPeriod(conversionParameters.getDateFrom(),
                                                                             conversionParameters.getDateTo())
                        .orElseThrow(() -> new NoFoundFinancialOperationsException(conversionParameters.getDateFrom(),
                                conversionParameters.getDateTo()));
        List<ConvertedFinancialOperationResponseDto> convertedFinancialOperations =
                financialOperationMapper.mapEntitiesToConvertedDtoList(periodFinancialOperations);
        convertedFinancialOperations = convertAmountToCurrency(convertedFinancialOperations, vunitRate);
        log.info("Successfully recalculated financial operations in the selected currency for the specified period");
        return convertedFinancialOperations;
    }

    private List<ConvertedFinancialOperationResponseDto> convertAmountToCurrency(
            List<ConvertedFinancialOperationResponseDto> financialOperations, double vunitRate) {
        financialOperations.forEach(financialOperation ->
                financialOperation.setAmount(Math.round(financialOperation.getAmount() / 60 / vunitRate * 100.0) / 100.0));
        return financialOperations;
    }

}
