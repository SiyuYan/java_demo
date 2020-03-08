package com.yansiyu.homework.multithread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MultiUrlsCompare {

    private ExecutorService executorService;

    public MultiUrlsCompare() {
        executorService = Executors.newWorkStealingPool(10);
    }

    public List<Future<HashMap<Integer, Boolean>>> compareUrls(
            Map<Integer, String> urls1,
            Map<Integer, String> urls2) {

        if (urls1.size() == 0 || urls2.size() == 0) {
            System.out.println("File are empty, Please check!");
        }

        if (urls1.size() != urls2.size()) {
            System.out.println("File size are different, will compare part of them!");
        }

        int urlLength = Math.min(urls1.size(), urls2.size());

        List<UrlCompareTask> tasks =
                generateCompareTasks(urls1, urls2, urlLength);

        return submitCompareTasks(tasks);
    }

    public List<HashMap<Integer, Boolean>> getResults(List<Future<HashMap<Integer, Boolean>>> tasks) {
        return tasks.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    private List<UrlCompareTask> generateCompareTasks(Map<Integer, String> file1,
                                                      Map<Integer, String> file2,
                                                      int urlLength) {
        List<UrlCompareTask> tasks = new ArrayList<>();

        for (int index = 0; index < urlLength; index++) {
            tasks.add(generateCompareTask(file1.get(index), file2.get(index), index));
        }
        return tasks;
    }

    private UrlCompareTask generateCompareTask(String url1, String url2, int index) {
        return new UrlCompareTask(index, url1, url2);
    }

    private List<Future<HashMap<Integer, Boolean>>> submitCompareTasks(List<UrlCompareTask> tasks) {
        try {
            return executorService
                    .invokeAll(tasks, 10, TimeUnit.MINUTES);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
