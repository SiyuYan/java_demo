package com.yansiyu.homework.comparator;

import org.junit.Test;

import static org.junit.Assert.*;

public class UrlResultComparatorTest {

    private UrlResultComparator comparator = new UrlResultComparator();

    @Test
    public void shouldReturnTrueIfTheResultAreSame() {
        String URL = "https://reqres.in/api/users/3";
        boolean compare = comparator.compare(URL, URL);

        assertTrue(compare);
    }


    @Test
    public void shouldReturnFalseIfOneUrlIsEmpty() {
        String URL = "https://reqres.in/api/users/3";
        boolean compare = comparator.compare(URL, null);

        assertFalse(compare);
    }

    @Test
    public void shouldReturnFalseIfUrlsAreNull() {
        boolean compare = comparator.compare(null, null);

        assertTrue(compare);
    }
}
