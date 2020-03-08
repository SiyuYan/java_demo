package com.yansiyu.homework.multithread;

import org.testng.annotations.Test;

public class MultiUrlsCompareTest {

    @Test
    public void shouldWorkAsExpected() {

        System.out.println("################Final Result###################");

        MultiUrlsCompare comparator = new MultiUrlsCompare();

        comparator.compareWithFileNames("file1", "file2");
    }
}
