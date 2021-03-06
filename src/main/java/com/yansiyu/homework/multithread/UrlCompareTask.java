package com.yansiyu.homework.multithread;

import com.google.api.client.util.Strings;
import com.yansiyu.homework.comparator.JsonComparator;
import com.yansiyu.homework.http.HttpClient;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class UrlCompareTask implements Callable<HashMap<Integer, Boolean>> {

    private int index = 0;

    private String url1;

    private String url2;

    private JsonComparator jsonComparator;

    private long startTime;

    public UrlCompareTask() {
    }

    public UrlCompareTask(int index, String url1, String url2) {
        this.index = index;
        this.url1 = url1;
        this.url2 = url2;
    }

    public String getUrl1() {
        return url1;
    }

    void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    void setUrl2(String url2) {
        this.url2 = url2;
    }

    void setJsonComparator(JsonComparator jsonComparator) {
        this.jsonComparator = jsonComparator;
    }

    private JsonComparator getJsonComparator() {
        if (jsonComparator == null) {
            jsonComparator = new JsonComparator();
        }
        return jsonComparator;
    }

    @Override
    public HashMap<Integer, Boolean> call() throws Exception {
        // add time cost in here

        long startTime = System.currentTimeMillis();

        HashMap<Integer, Boolean> result = new HashMap<>();

        JsonComparator jsonComparator = this.getJsonComparator();

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

        long endTime = System.currentTimeMillis();

        if (isMatch) {
            System.out.println(String.format("%s equals to %s, time: %s ms", url1, url2,
                    getTimeDiff(endTime, startTime)));
        } else {
            System.out.println(String.format("%s not equals to %s, time: %s ms", url1, url2
                    , getTimeDiff(endTime, startTime)));
        }
        result.put(index, isMatch);
        return result;
    }

    private long getTimeDiff(long endTime, long startTime) {

        return (endTime - startTime);
    }
}

