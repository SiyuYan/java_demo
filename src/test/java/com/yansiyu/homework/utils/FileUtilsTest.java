package com.yansiyu.homework.utils;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;


/**
 * Created by siyu.yan on 2020/3/7.
 */
public class FileUtilsTest {
    @Test
    public void shouldReturnFileContent() {

        Map<Integer, String> lines = FileUtils.readFile("file1");

        Assert.assertEquals(lines.size(), 6);
        Assert.assertEquals(lines.get(0), "https://reqres.in/api/users/3");
        Assert.assertEquals(lines.get(4), "https://reqres.in/api/users?page=1");

    }

    @Test
    public void shouldReturnNullIfFileNotExist() {
        Map<Integer, String> lines = FileUtils.readFile("testtest");
        Assert.assertEquals(lines, null);

    }
}
