package ru.bank.constants;

import java.time.LocalDate;

public class TestConstants {

    public static final String AUSTRALIAN_DOLLAR      = "Австралийский доллар";
    public static final String AZERBAIJANI_MANAT      = "Азербайджанский манат";
    public static final String BRITISH_POUND_STERLING = "Фунт стерлингов Соединенного королевства";
    public static final String ARMENIAN_DRAM          = "Армянский драм";
    public static final String US_DOLLAR              = "Доллар США";
    public static final String EURO                   = "Евро";

    public static final String VCH_CODE_AUD = "AUD";
    public static final String VCH_CODE_AZN = "AZN";
    public static final String VCH_CODE_GBP = "GBP";
    public static final String VCH_CODE_AMD = "AMD";
    public static final String VCH_CODE_USD = "USD";
    public static final String VCH_CODE_EUR = "EUR";

    public static final int VCODE_AUSTRALIAN_DOLLAR      = 36;
    public static final int VCODE_AZERBAIJANI_MANAT      = 944;
    public static final int VCODE_BRITISH_POUND_STERLING = 826;
    public static final int VCODE_ARMENIAN_DRAM          = 51;
    public static final int VCODE_US_DOLLAR              = 840;
    public static final int VCODE_US_EURO                = 978;

    public static final double VUNIT_RATE_AUSTRALIAN_DOLLAR      = 58.9801;
    public static final double VUNIT_RATE_AZERBAIJANI_MANAT      = 54.0745;
    public static final double VUNIT_RATE_BRITISH_POUND_STERLING = 113.0329;
    public static final double VUNIT_RATE_ARMENIAN_DRAM          = 22.8287;
    public static final double VUNIT_RATE_US_DOLLAR              = 91.9266;
    public static final double VUNIT_RATE_US_EURO                = 98.4076;
    public static final double VUNIT_RATE                        = 91.9266;

    public static final int RUB_FINANCIAL_OPERATION_FIRST   = 72000;
    public static final int RUB_FINANCIAL_OPERATION_SECOND  = 48000;
    public static final int RUB_FINANCIAL_OPERATION_THIRD   = 210000;
    public static final int RUB_FINANCIAL_OPERATION_FOURTH  = 840000;
    public static final int RUB_FINANCIAL_OPERATION_FIFTH   = 3894000;
    public static final int RUB_FINANCIAL_OPERATION_SIXTH   = 384000;
    public static final int RUB_FINANCIAL_OPERATION_SEVENTH = 570000;

    public static final double USD_FINANCIAL_OPERATION_FIRST   = 13.05d;
    public static final double USD_FINANCIAL_OPERATION_SECOND  = 8.7d;
    public static final double USD_FINANCIAL_OPERATION_THIRD   = 38.07d;
    public static final double USD_FINANCIAL_OPERATION_FOURTH  = 152.3d;
    public static final double USD_FINANCIAL_OPERATION_FIFTH   = 706d;
    public static final double USD_FINANCIAL_OPERATION_SIXTH   = 13.05d;

    public static final double ACCURACY_TO_INTEGERS     = 0.1d;
    public static final double ACCURACY_TO_TENTHS       = 0.01d;
    public static final double ACCURACY_TO_HUNDREDTHS   = 0.001d;

    public static final int YEAR           = 2023;
    public static final int MONTH          = 11;
    public static final int DAY_OF_MONTH_1 = 1;
    public static final int DAY_OF_MONTH_3 = 3;
    public static final int DAY_OF_MONTH_5 = 5;
    public static final int DAY_OF_MONTH_9 = 9;

    public static final LocalDate DATE_FROM = LocalDate.of(2023, 11, 1);
    public static final LocalDate DATE_TO   = LocalDate.of(2023, 11, 5);

    public static final int ZERO  = 0;
    public static final int ONE   = 1;
    public static final int TWO   = 2;
    public static final int THREE = 3;
    public static final int FOUR  = 4;
    public static final int FIVE  = 5;
    public static final int SIX   = 6;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    public static final int HUNDRED = 100;

    public static final String INCOME = "Income";

    public static final String FIELD_NAME_DESCRIPTION = "description";
    public static final String FIELD_NAME_AMOUNT      = "amount";
    public static final String FIELD_NAME_DATE        = "date";
    public static final String FIELD_NAME_VCH_CODE    = "vchCode";

    public static final String ERROR_NOT_NULL         = "must not be null";
    public static final String ERROR_NOT_BLANK        = "should not be empty";
    public static final String ERROR_LESS_THAN_ONE    = "must be at least 1";

    public static final String PATH_NOT_NULL          = "{javax.validation.constraints.NotNull.message}";
    public static final String PATH_NOT_BLANK         = "{javax.validation.constraints.NotBlank.message}";
    public static final String PATH_LESS_THAN_ONE     = "{javax.validation.constraints.Min.message}";

    private TestConstants() { }

}
