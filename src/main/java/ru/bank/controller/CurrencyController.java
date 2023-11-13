package ru.bank.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bank.dto.response.CurrencyResponseDto;
import ru.bank.service.CurrencyService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/currencies")
@Api(tags = "Currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @ApiOperation(value = "This method uploads the exchange rates for USD and EUR")
    @GetMapping("/upload")
    public ResponseEntity<List<CurrencyResponseDto>> upload() {
        List<CurrencyResponseDto> currencies = currencyService.upload();
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

}
