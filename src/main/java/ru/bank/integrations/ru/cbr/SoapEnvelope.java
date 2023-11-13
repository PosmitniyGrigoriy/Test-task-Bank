package ru.bank.integrations.ru.cbr;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.ToString;

@JacksonXmlRootElement(localName = "Envelope", namespace = "http://www.w3.org/2003/05/soap-envelope")
@ToString
public class SoapEnvelope {

    private SoapBody soapBody;

    @JacksonXmlProperty(localName = "Body", namespace = "http://www.w3.org/2003/05/soap-envelope")
    public SoapBody getSoapBody() {
        return soapBody;
    }

}
