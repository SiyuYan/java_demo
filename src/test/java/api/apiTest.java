package api;

import com.yansiyu.homework.utils.FileUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class apiTest {
    private HashMap content;


    @BeforeMethod
    public void setUp() {
        this.content = FileUtils.getFileContent();
    }

    @Test
    public void runOtherTest1() {
        Map<Integer, String> file1 = (Map<Integer, String>) this.content.get("file1");
        System.out.println(file1);
//        UrlCompareTask comparator = new UrlCompareTask();
//        String result = HttpClient.get("https://reqres.in/api/users/3");
//        boolean compare = comparator.compare(URL1, URL2);


    }
}
