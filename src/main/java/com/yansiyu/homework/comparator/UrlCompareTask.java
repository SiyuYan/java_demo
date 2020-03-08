package com.yansiyu.homework.comparator;

import com.google.api.client.util.Strings;
import com.yansiyu.homework.http.HttpClient;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class UrlCompareTask implements Callable<HashMap<Integer, Boolean>> {

    private int index;

    private String url1;

    private String url2;

    private JsonComparator jsonComparator;

    public UrlCompareTask() {
    }

    public UrlCompareTask(int index, String url1, String url2) {
        this.index = index;
        this.url1 = url1;
        this.url2 = url2;
    }

    @Override
    public HashMap<Integer, Boolean> call() throws Exception {
        HashMap<Integer, Boolean> result = new HashMap<>();
        if (jsonComparator == null) {
            jsonComparator = new JsonComparator();
        }

        if (Strings.isNullOrEmpty(url1) && Strings.isNullOrEmpty(url2)) {
            System.out.println(String.format("%s equals to %s", url1, url2));
            result.put(index, true);
            return result;
        }

        if (Strings.isNullOrEmpty(url1) || Strings.isNullOrEmpty(url2)) {
            System.out.println(String.format("%s not equals to %s", url1, url2));
            result.put(index, false);
            return result;
        }
        HttpClient httpClient = new HttpClient();
        String result1 = httpClient.get(url1);
        String result2 = httpClient.get(url2);

        boolean isMatch = jsonComparator.compare(result1, result2);

        if (isMatch) {
            System.out.println(String.format("%s equals to %s", url1, url2));
        } else {
            System.out.println(String.format("%s not equals to %s", url1, url2));
        }
        result.put(index, isMatch);
        return result;
    }
}
