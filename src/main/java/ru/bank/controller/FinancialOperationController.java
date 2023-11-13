package ru.bank.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bank.dto.request.FinancialOperationRequestDto;
import ru.bank.dto.response.FinancialOperationResponseDto;
import ru.bank.dto.response.ConvertedFinancialOperationResponseDto;
import ru.bank.entity.FinancialOperationEntity;
import ru.bank.service.FinancialOperationService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/financial-operations")
@Api(tags = "Financial Operations")
public class FinancialOperationController {

    private final FinancialOperationService financialOperationService;

    @ApiOperation(value = "This method adds financial transactions in rubles (specify kopecks in amount field)")
    @PostMapping("/add/all")
    public ResponseEntity<List<FinancialOperationResponseDto>> addAll(
            @RequestBody List<FinancialOperationEntity> financialOperations) {
        List<FinancialOperationResponseDto> rubFinancialOperations =
                financialOperationService.addAll(financialOperations);
        return new ResponseEntity<>(rubFinancialOperations, HttpStatus.OK);
    }
    
    @ApiOperation(value = "This method recalculates financial transactions in the selected currency " +
            "for the specified period")
    @PostMapping("/recalculate")
    public ResponseEntity<List<ConvertedFinancialOperationResponseDto>> recalculateFinancialTransactionsByCurrency(
            @RequestBody FinancialOperationRequestDto conversionParameters) {
        List<ConvertedFinancialOperationResponseDto> rubFinancialOperations =
                financialOperationService.recalculateAll(conversionParameters);
        return new ResponseEntity<>(rubFinancialOperations, HttpStatus.OK);
    }

}
