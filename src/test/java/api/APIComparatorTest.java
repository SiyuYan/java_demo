package api;

import com.yansiyu.homework.multithread.MultiUrlsCompare;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import static com.yansiyu.homework.utils.FileUtils.readFile;

public class APIComparatorTest {


    @Test
    public void verifyResponseDiff() {
        // given
        MultiUrlsCompare comparator = new MultiUrlsCompare();
        Map<Integer, String> file1 = readFile("file1");
        Map<Integer, String> file2 = readFile("file2");

        //when
        assert file1 != null;
        List<Future<HashMap<Integer, Boolean>>> tasks =
                comparator.compareUrls(file1, file2);

        //then
        List<HashMap<Integer, Boolean>> results =
                comparator.getResults(tasks);

        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(), 6);
    }

    @Test
    public void verifyResponseSame() {
        // given
        MultiUrlsCompare comparator = new MultiUrlsCompare();
        Map<Integer, String> file1 = readFile("file1");

        //when
        assert file1 != null;
        List<Future<HashMap<Integer, Boolean>>> tasks =
                comparator.compareUrls(file1, file1);

        //then
        List<HashMap<Integer, Boolean>> results =
                comparator.getResults(tasks);

        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(), 6);
    }

    @Test
    public void verifyNoResponse() {
        // given
        MultiUrlsCompare comparator = new MultiUrlsCompare();
        Map<Integer, String> file3 = readFile("file3");

        //when
        assert file3 != null;
        List<Future<HashMap<Integer, Boolean>>> tasks =
                comparator.compareUrls(file3, file3);

        //then
        List<HashMap<Integer, Boolean>> results =
                comparator.getResults(tasks);

        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(), 0);
    }

    @Test
    public void verifyThousandsURLs() {
        // given
        MultiUrlsCompare comparator = new MultiUrlsCompare();
        Map<Integer, String> file4 = readFile("file4");
        Map<Integer, String> file5 = readFile("file5");

        //when
        assert file4 != null;
        List<Future<HashMap<Integer, Boolean>>> tasks =
                comparator.compareUrls(file4, file5);

        //then
        List<HashMap<Integer, Boolean>> results =
                comparator.getResults(tasks);

        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(), 1080);
    }
}
