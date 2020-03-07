package com.yansiyu.homework.comparator;

import com.google.api.client.util.Strings;
import com.yansiyu.homework.http.HttpClient;

public class UrlResultComparator extends JsonComparator {

    @Override
    public boolean compare(String url1, String url2) {

        if (Strings.isNullOrEmpty(url1) && Strings.isNullOrEmpty(url2)) {
            return true;
        }

        if (Strings.isNullOrEmpty(url1) || Strings.isNullOrEmpty(url2)) {
            return false;
        }

        String result1 = HttpClient.get(url1);

        String result2 = HttpClient.get(url2);

        return super.compare(result1, result2);
    }

}
