package ru.bank.entity;

import lombok.*;
import ru.bank.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RUB_FINANCIAL_OPERATIONS")
public class FinancialOperationEntity extends BaseEntity {

    @NotNull
    private LocalDate dateAt;

    @NotBlank
    private String description;

    @Min(1)
    private int amount;

}
