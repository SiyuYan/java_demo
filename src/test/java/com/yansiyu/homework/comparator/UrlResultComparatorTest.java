package com.yansiyu.homework.comparator;

import com.yansiyu.homework.http.HttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpClient.class})
public class UrlResultComparatorTest {

    private UrlResultComparator comparator = new UrlResultComparator();


    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(HttpClient.class);

        PowerMockito.when(HttpClient
                .get(anyString())).thenReturn("{\"name\":\"xiao\"}");
    }

    @Test
    public void shouldReturnFalseIfResultNotMatch() {
        String URL1 = "https://reqres.in/api/users/2";
        String URL2 = "https://reqres.in/api/users/3";
        PowerMockito.when(HttpClient
                .get(URL1)).thenReturn("{\"name\":\"xiao1\"}");
        PowerMockito.when(HttpClient
                .get(URL2)).thenReturn("{\"name\":\"xiao\"}");

        boolean compare = comparator.compare(URL1, URL2);

        assertFalse(compare);
    }

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
