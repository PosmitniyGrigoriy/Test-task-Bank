package ru.bank.integrations.ru.cbr;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.ToString;

@ToString
public class GetCursOnDateXMLResult {

    private ValuteData valuteData;

    @JacksonXmlProperty(localName = "ValuteData")
    public ValuteData getValuteData() {
        return valuteData;
    }

}
