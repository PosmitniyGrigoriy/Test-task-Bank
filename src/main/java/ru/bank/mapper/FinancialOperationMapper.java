package ru.bank.mapper;

import org.springframework.stereotype.Component;
import ru.bank.dto.response.FinancialOperationResponseDto;
import ru.bank.dto.response.ConvertedFinancialOperationResponseDto;
import ru.bank.entity.FinancialOperationEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class FinancialOperationMapper {

    public List<FinancialOperationResponseDto> mapEntitiesToDtoList(List<FinancialOperationEntity> financialOperations) {
        if (financialOperations == null) {
            return null;
        }
        List<FinancialOperationResponseDto> list = new ArrayList<>(financialOperations.size());
        for (FinancialOperationEntity financialOperation : financialOperations) {
            list.add(entityToDto(financialOperation));
        }
        return list;
    }

    public FinancialOperationResponseDto entityToDto(FinancialOperationEntity financialOperation) {
        if (financialOperation == null) {
            return null;
        }
        FinancialOperationResponseDto financialOperationResponse = new FinancialOperationResponseDto();
        financialOperationResponse.setId(financialOperation.getId());
        financialOperationResponse.setDate(financialOperation.getDate());
        financialOperationResponse.setDescription(financialOperation.getDescription());
        financialOperationResponse.setAmount(financialOperation.getAmount());
        return financialOperationResponse;
    }

    public List<ConvertedFinancialOperationResponseDto> mapEntitiesToConvertedDtoList(
            List<FinancialOperationEntity> financialOperations) {
        if (financialOperations == null) {
            return null;
        }
        List<ConvertedFinancialOperationResponseDto> list = new ArrayList<>(financialOperations.size());
        for (FinancialOperationEntity financialOperation : financialOperations) {
            list.add(entityToConvertedDto(financialOperation));
        }
        return list;
    }

    public ConvertedFinancialOperationResponseDto entityToConvertedDto(FinancialOperationEntity financialOperation) {
        if (financialOperation == null) {
            return null;
        }
        ConvertedFinancialOperationResponseDto convertedFinancialOperation = new ConvertedFinancialOperationResponseDto();
        convertedFinancialOperation.setId(financialOperation.getId());
        convertedFinancialOperation.setDate(financialOperation.getDate());
        convertedFinancialOperation.setDescription(financialOperation.getDescription());
        convertedFinancialOperation.setAmount(financialOperation.getAmount());
        return convertedFinancialOperation;
    }

}
