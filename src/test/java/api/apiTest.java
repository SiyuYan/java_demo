package api;

import com.yansiyu.homework.multithread.MultiUrlsCompare;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yansiyu.homework.utils.FileUtils.readFile;

public class apiTest {
    private HashMap content;


    @Test
    public void verifyResponseSame() {
        // given
        MultiUrlsCompare comparator = new MultiUrlsCompare();
        Map<Integer, String> file1 = readFile("file1");
        Map<Integer, String> file2 = readFile("file2");

        //when
        List<Map<Integer, Boolean>> results =
                comparator.compareUrls(file1, file2);

        //Then

    }
}
