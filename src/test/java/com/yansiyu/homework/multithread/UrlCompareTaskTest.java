package com.yansiyu.homework.multithread;

import com.yansiyu.homework.comparator.JsonComparator;
import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.mockito.MockitoAnnotations;


import java.util.HashMap;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class UrlCompareTaskTest {

    private UrlCompareTask compareTask;

    @Mock
    private JsonComparator jsonComparator;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        compareTask = new UrlCompareTask();
        when(jsonComparator.compare(anyString(), anyString())).thenReturn(true);
        compareTask.setJsonComparator(jsonComparator);
    }

    @Test
    public void shouldReturnJsonComparatorResultIfResultUrlValid() throws Exception {

        // given
        String URL1 = "https://reqres.in/api/users/2";
        String URL2 = "https://reqres.in/api/users/3";
        compareTask.setUrl1(URL1);
        compareTask.setUrl2(URL2);

        when(jsonComparator.compare(anyString(), anyString())).thenReturn(false);
        compareTask.setJsonComparator(jsonComparator);

        HashMap<Integer, Boolean> result = compareTask.call();

        Assert.assertFalse(result.get(0));
    }


    @Test
    public void shouldReturnFalseIfOneUrlIsEmpty() throws Exception {
        String URL = "https://reqres.in/api/users/3";

        compareTask.setUrl1(URL);
        compareTask.setUrl2(null);

        HashMap<Integer, Boolean> result = compareTask.call();

        Assert.assertFalse(result.get(0));
    }

    @Test
    public void shouldReturnTrueIfUrlBothAreNull() throws Exception {
        compareTask.setUrl1(null);
        compareTask.setUrl2(null);

        HashMap<Integer, Boolean> result = compareTask.call();

        Assert.assertTrue(result.get(0));
    }
}
