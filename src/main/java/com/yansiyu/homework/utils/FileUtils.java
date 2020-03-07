package com.yansiyu.homework.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by siyu.yan on 2020/3/7.
 */
public class FileUtils {

    public static Map<Integer, String> readFile(String fileName) {
        HashMap<Integer, String> result = new HashMap<>();
        try {

            URL resource = FileUtils.class.getClassLoader().getResource(fileName);

            if (resource == null) {
                return null;
            }

            List<String> lines = Files.lines(Paths.get(resource.getPath()))
                    .collect(Collectors.toList());

            for (int index = 0; index < lines.size(); index++) {
                result.put(index, lines.get(index));
            }

            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
