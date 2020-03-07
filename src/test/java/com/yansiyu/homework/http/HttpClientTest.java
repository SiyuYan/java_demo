package com.yansiyu.homework.http;

import org.junit.Test;

import static org.junit.Assert.*;

public class HttpClientTest {

    @Test
    public void shouldGetCorrectResultWhenUrlValid() {
        String result = HttpClient.get("https://reqres.in/api/users/3");
        assertNotNull(result);
    }

    @Test
    public void shouldGetCorrectResultWhenIsInvalid() {
        String result = HttpClient.get("http://reqres");
        assertNull(result);
    }
}
