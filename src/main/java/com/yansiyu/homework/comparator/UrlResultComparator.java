package com.yansiyu.homework.comparator;

import com.yansiyu.homework.http.HttpClient;

public class UrlResultComparator extends JsonComparator {

    public boolean compareResult(String url1, String url2) {

        String result1 = HttpClient.get(url1);

        String result2 = HttpClient.get(url2);

        return compare(result1, result2);
    }

}
