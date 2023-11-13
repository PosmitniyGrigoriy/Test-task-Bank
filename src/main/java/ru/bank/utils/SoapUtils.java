package ru.bank.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.bank.constants.HttpConstants;
import ru.bank.exception.FailedConvertOneTypeToAnotherException;
import ru.bank.exception.FailedGetXmlException;
import ru.bank.integrations.ru.cbr.SoapEnvelope;
import ru.bank.integrations.ru.cbr.ValuteCursOnDate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;

public class SoapUtils {

    private SoapUtils() { }

    // An entry date example: 2023-11-11 or 2023-11-11T14:30:00
    public static List<ValuteCursOnDate> getCurrencyRatesByDate(String date) {
        HttpURLConnection connection = HttpUtils.createConnection();
        String requestBody = fillInRequestBody(date);
        String responseXmlString = getResponseXmlString(connection, requestBody);
        return convertXmlStringToObject(responseXmlString)
                .getSoapBody()
                .getGetCursOnDateXMLResponse()
                .getGetCursOnDateXMLResult()
                .getValuteData()
                .getValuteCursOnDateList();
    }

    private static String fillInRequestBody(String date) {
        return HttpConstants.XML_INPUT_DATA_START + date + HttpConstants.XML_INPUT_DATA_END;
    }

    private static String getResponseXmlString(HttpURLConnection connection, String requestBody) {
        StringBuilder response = new StringBuilder();
        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = requestBody.getBytes(HttpConstants.ENCODING);
            outputStream.write(input, 0, input.length);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (Exception ex) {
            throw new FailedGetXmlException(ex.toString());
        } finally {
            connection.disconnect();
        }
        return response.toString();
    }

    private static SoapEnvelope convertXmlStringToObject(String xmlString) {
        SoapEnvelope soapEnvelope;
        try {
            soapEnvelope = new XmlMapper().readValue(xmlString, SoapEnvelope.class);
        } catch (JsonProcessingException ex) {
            throw new FailedConvertOneTypeToAnotherException(String.class, SoapEnvelope.class, ex.toString());
        }
        return soapEnvelope;
    }

}
