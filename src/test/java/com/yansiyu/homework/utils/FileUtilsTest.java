package com.yansiyu.homework.utils;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by siyu.yan on 2020/3/7.
 */
public class FileUtilsTest {
    @Test
    public void shouldReturnFileContent() {

        Map<Integer, String> lines = FileUtils.readFile("file1");

        assertThat(lines.size(), is(5));
        assertThat(lines.get(0), is("https://reqres.in/api/users/3"));
        assertThat(lines.get(4), is("https://reqres.in/api/users?page=1"));

    }

    @Test
    public void shouldReturnNullIfFileNotExist() {
        Map<Integer, String> lines = FileUtils.readFile("testtest");
        assertThat(lines, is(nullValue()));

    }
}
