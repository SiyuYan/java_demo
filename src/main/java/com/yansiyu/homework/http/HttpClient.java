package com.yansiyu.homework.http;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;

public class HttpClient {

    private static HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();

    public static String get(String url) {
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

}
