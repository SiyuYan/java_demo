package com.yansiyu.homework.comparator;

import com.yansiyu.homework.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MultiThreadUrlComparator {


    public void compareWholeFile(String fileName1, String fileName2) {
        Map<Integer, String> file1 = FileUtils.readFile(fileName1);
        Map<Integer, String> file2 = FileUtils.readFile(fileName2);

        if (file1.size() == 0 || file2.size() == 0) {
            System.out.println("File are empty, Please check!");
        }

        if (file1.size() != file2.size()) {
            System.out.println("File size are different, will compare part of them!");
        }

        compareUrls(file1, file2);
    }

    private List<Map<Integer, Boolean>> compareUrls(Map<Integer, String> file1, Map<Integer, String> file2) {
        int urlLength = Math.min(file1.size(), file2.size());

        ExecutorService executorService = Executors.newWorkStealingPool(10);

        List<UrlCompareTask> tasks = new ArrayList<>();

        for (int index = 0; index < urlLength; index++) {
            tasks.add(new UrlCompareTask(
                    index,
                    file1.get(index),
                    file2.get(index)));
        }

        try {
            return executorService
                    .invokeAll(tasks, 10, TimeUnit.MINUTES)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    })
                    .collect(Collectors.toList());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
