package ru.bank.constants;

public class HttpConstants {

    public static final String BASE_URL = "http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx";

    public static final String REQUEST_TYPE_POST = "POST";

    public static final String HOST_KEY   = "Host";
    public static final String HOST_VALUE = "www.cbr.ru";

    public static final String CONTENT_TYPE_KEY   = "Content-Type";
    public static final String CONTENT_TYPE_VALUE = "application/soap+xml; charset=utf-8";

    public static final String ENCODING = "utf-8";

    public static final String XML_INPUT_DATA_START = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
            "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
            "xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">" +
            "<soap12:Body>" +
            "<GetCursOnDateXML xmlns=\"http://web.cbr.ru/\">" +
            "<On_date>";
    public static final String XML_INPUT_DATA_END = "</On_date>" +
            "</GetCursOnDateXML>" +
            "</soap12:Body>" +
            "</soap12:Envelope>";

    private HttpConstants() { }

}
