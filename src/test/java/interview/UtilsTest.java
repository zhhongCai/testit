package interview;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class UtilsTest {

    @Before
    public void before() {

    }

    @After
    public void after() {

    }

    /**
     * normal case
     */
    @Test
    public void test_getUnUsedKeys() {
        int[] allKeys = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] usedKeys = {2, 3, 4, 8};
        int[] unusedKeys = Utils.getUnUsedKeys(allKeys, usedKeys);
        Assert.assertNotNull(unusedKeys);
        Assert.assertEquals(allKeys.length - usedKeys.length, unusedKeys.length);

        Set<Integer> allKeySet = Arrays.stream(allKeys).boxed().collect(Collectors.toSet());
        Set<Integer> usedKeySet = Arrays.stream(usedKeys).boxed().collect(Collectors.toSet());
        for (int unusedKey : unusedKeys) {
            Assert.assertTrue(allKeySet.contains(unusedKey));
            Assert.assertTrue(usedKeySet.contains(unusedKey));
        }
    }
}
