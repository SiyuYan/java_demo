package com.yansiyu.homework.comparator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.Strings;

import java.io.IOException;
import java.util.HashMap;

public class JsonComparator implements IComparator<String, String> {
    @Override
    public boolean compare(String result1, String result2) {
        if (Strings.isNullOrEmpty(result1) || Strings.isNullOrEmpty(result2)) {
            return false;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            HashMap resultMap1 = objectMapper
                    .readValue(result1, HashMap.class);
            HashMap resultMap2 = objectMapper
                    .readValue(result2, HashMap.class);
            return this.compareMap(resultMap1, resultMap2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean compareMap(HashMap resultMap1, HashMap resultMap2) {
        if (resultMap1.size() == 0 && resultMap2.size() == 0) {
            return true;
        }

        // check if have same size of keys
        if (resultMap1.size() == 0 || resultMap2.size() == 0) {
            return false;
        }

        for (Object key1 : resultMap1.keySet()) {
            // check if they have same key
            if (!resultMap2.containsKey(key1)) {
                return false;
            }

            Object value1 = resultMap1.get(key1);
            Object value2 = resultMap2.get(key1);

            // check if the type are different
            if (!value1.getClass().getName().equals(value2.getClass().getName())) {
                return false;
            }

            if (value1 instanceof HashMap) {
                return compareMap((HashMap) value1, (HashMap) value2);
            } else if (!value1.equals(value2)) {
                return false;
            }
        }
        return true;
    }
}
