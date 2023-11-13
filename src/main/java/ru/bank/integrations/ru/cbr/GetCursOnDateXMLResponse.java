package ru.bank.integrations.ru.cbr;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.ToString;

@ToString
public class GetCursOnDateXMLResponse {

    private GetCursOnDateXMLResult getCursOnDateXMLResult;

    @JacksonXmlProperty(localName = "GetCursOnDateXMLResult")
    public GetCursOnDateXMLResult getGetCursOnDateXMLResult() {
        return getCursOnDateXMLResult;
    }

}
