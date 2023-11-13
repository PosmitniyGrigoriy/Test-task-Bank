package ru.bank.integrations.ru.cbr;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.ToString;

@ToString
public class SoapBody {

    private GetCursOnDateXMLResponse getCursOnDateXMLResponse;

    @JacksonXmlProperty(localName = "GetCursOnDateXMLResponse", namespace = "http://web.cbr.ru/")
    public GetCursOnDateXMLResponse getGetCursOnDateXMLResponse() {
        return getCursOnDateXMLResponse;
    }

}
