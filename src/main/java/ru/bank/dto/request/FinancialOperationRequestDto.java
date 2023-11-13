package ru.bank.dto.request;

import lombok.*;
import ru.bank.enumeration.CurrencyEnum;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FinancialOperationRequestDto {

    @NotNull
    private LocalDate dateFrom;

    @NotNull
    private LocalDate dateTo;

    @NotNull
    private CurrencyEnum vchCode;

}
