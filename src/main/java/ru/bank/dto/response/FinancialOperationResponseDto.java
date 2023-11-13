package ru.bank.dto.response;

import lombok.*;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FinancialOperationResponseDto {
    private int id;
    private LocalDate date;
    private String description;
    private int amount;
}
