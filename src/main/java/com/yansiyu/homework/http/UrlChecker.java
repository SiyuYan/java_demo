package com.yansiyu.homework.http;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlChecker {

    public static boolean isValid(String url) {
        try {
            URL obj = new URL(url);
            obj.toURI();
            return true;
        } catch (MalformedURLException e) {
            return false;
        } catch (URISyntaxException e) {
            return false;
        }
    }
}
