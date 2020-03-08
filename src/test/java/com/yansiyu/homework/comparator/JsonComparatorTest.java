package com.yansiyu.homework.comparator;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JsonComparatorTest {

    private JsonComparator comparator;

    @BeforeClass
    public void setUp() {
        if (comparator == null) {
            comparator = new JsonComparator();
        }
    }

    @Test
    public void shouldReturnFalseIfCompareWithNull() {

        String json1 = null;
        String json2 = "{}";

        boolean result = comparator.compare(json1, json2);
        Assert.assertFalse(result);
    }

    @Test
    public void shouldReturnTrueIfCompareEmptyJson() {

        String json1 = "{}";
        String json2 = "{}";

        boolean result = comparator.compare(json1, json2);
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfJsonNotMatch() {

        String json1 = "{\"data\":{\"id\":3,\"email\":\"emma.wong@reqres.in\",\"first_name\":\"Emma\",\"last_name\":\"Wong\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/olegpogodaev/128.jpg\"}}\n";
        String json2 = "{\"data\":{\"id\":2,\"email\":\"janet.weaver@reqres.in\",\"first_name\":\"Janet\",\"last_name\":\"Weaver\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg\"}}\n";

        boolean result = comparator.compare(json1, json2);
        Assert.assertFalse(result);
    }

    @Test
    public void shouldReturnTrueIfJsonMach() {

        String json1 = "{\"data\":{\"id\":3,\"email\":\"emma.wong@reqres.in\",\"first_name\":\"Emma\",\"last_name\":\"Wong\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/olegpogodaev/128.jpg\"}}\n";
        String json2 = "{\"data\":{\"id\":3,\"email\":\"emma.wong@reqres.in\",\"first_name\":\"Emma\",\"last_name\":\"Wong\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/olegpogodaev/128.jpg\"}}\n";

        boolean result = comparator.compare(json1, json2);
        Assert.assertTrue(result);
    }
}
