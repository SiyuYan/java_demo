package com.yansiyu.homework.http;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

class UrlChecker {

    static boolean isValid(String url) {
        try {
            URL obj = new URL(url);
            obj.toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }
}
