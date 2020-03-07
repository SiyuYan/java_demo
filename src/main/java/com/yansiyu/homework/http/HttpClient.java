package com.yansiyu.homework.http;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.Strings;

import java.io.IOException;

public class HttpClient {

    private static HttpRequestFactory requestFactory;

    public static String get(String url) {
        if (Strings.isNullOrEmpty(url)) {
            return null;
        }

        if (!UrlChecker.isValid(url)) {
            return null;
        }
        try {
            return requestFactory
                    .buildGetRequest(new GenericUrl(url))
                    .execute()
                    .parseAsString();
        } catch (IOException e) {
            System.out.println("Please check the url!");
            return null;
        }
    }

    public HttpRequestFactory getRequestFactory() {
        if (requestFactory == null) {
            requestFactory = new NetHttpTransport().createRequestFactory();
        }
        return requestFactory;
    }

}
