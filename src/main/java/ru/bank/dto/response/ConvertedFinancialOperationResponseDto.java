package ru.bank.dto.response;

import lombok.*;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConvertedFinancialOperationResponseDto {
    private int id;
    private LocalDate dateAt;
    private String description;
    private double amount;
}
