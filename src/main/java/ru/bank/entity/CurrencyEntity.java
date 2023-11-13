package ru.bank.entity;

import lombok.*;
import ru.bank.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CURRENCIES")
public class CurrencyEntity extends BaseEntity {

    @NotBlank
    private String vname;

    @Positive
    private BigDecimal vnom;

    @Positive
    private BigDecimal vcurs;

    @Min(1)
    private int vcode;

    @NotBlank
    private String vchCode;

    @Min(1)
    private double vunitRate;

}
