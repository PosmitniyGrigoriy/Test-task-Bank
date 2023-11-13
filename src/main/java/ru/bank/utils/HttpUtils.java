package ru.bank.utils;

import ru.bank.constants.HttpConstants;
import ru.bank.exception.FailedCreateHttpConnectionException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    private HttpUtils() { }

    public static HttpURLConnection createConnection() {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(HttpConstants.BASE_URL).openConnection();
            connection.setRequestMethod(HttpConstants.REQUEST_TYPE_POST);
            connection.setRequestProperty(HttpConstants.HOST_KEY, HttpConstants.HOST_VALUE);
            connection.setRequestProperty(HttpConstants.CONTENT_TYPE_KEY, HttpConstants.CONTENT_TYPE_VALUE);
            connection.setDoOutput(true);
        } catch (IOException ex) {
            throw new FailedCreateHttpConnectionException(ex.toString());
        }
        return connection;
    }

}
