package com.yansiyu.homework.utils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
