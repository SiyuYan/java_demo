package com.yansiyu.homework.http;


import org.testng.Assert;
import org.testng.annotations.Test;

public class HttpClientTest {

    private HttpClient httpClient = new HttpClient();

    @Test
    public void shouldGetCorrectResultWhenUrlValid() {

        String result = httpClient.get("https://reqres.in/api/users/3");
        Assert.assertNotNull(result);
    }

    @Test
    public void shouldGetCorrectResultWhenIsInvalid() {
        String result = httpClient.get("http://reqres");
        Assert.assertNull(result);
    }
}
