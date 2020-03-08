package com.yansiyu.homework.comparator;

import org.testng.annotations.Test;

public class MultiThreadUrlComparatorTest {


    @Test
    public void shouldWorkAsExpected() {

        MultiThreadUrlComparator comparator = new MultiThreadUrlComparator();

        comparator.compareWholeFile("file1", "file2");

    }
}
