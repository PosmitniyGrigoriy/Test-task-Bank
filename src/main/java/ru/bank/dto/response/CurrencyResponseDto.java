package ru.bank.dto.response;

import lombok.*;

import java.math.BigDecimal;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CurrencyResponseDto {
    private String vname;
    private BigDecimal vnom;
    private BigDecimal vcurs;
    private int vcode;
    private String vchCode;
    private double vunitRate;
}
