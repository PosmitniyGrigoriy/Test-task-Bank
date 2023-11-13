package ru.bank.integrations.ru.cbr;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ValuteCursOnDate {

    private String vname;
    private BigDecimal vnom;
    private BigDecimal vcurs;
    private int vcode;
    private String vchCode;
    private double vunitRate;

    @JacksonXmlProperty(localName = "Vname")
    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    @JacksonXmlProperty(localName = "Vnom")
    public BigDecimal getVnom() {
        return vnom;
    }

    @JacksonXmlProperty(localName = "Vcurs")
    public BigDecimal getVcurs() {
        return vcurs;
    }

    @JacksonXmlProperty(localName = "Vcode")
    public int getVcode() {
        return vcode;
    }

    @JacksonXmlProperty(localName = "VchCode")
    public String getVchCode() {
        return vchCode;
    }

    @JacksonXmlProperty(localName = "VunitRate")
    public double getVunitRate() {
        return vunitRate;
    }

}