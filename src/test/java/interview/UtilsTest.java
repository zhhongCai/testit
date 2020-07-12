package interview;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2020/7/11 19:31
 * @Description:
 */
public class UtilsTest {

    private int[] allKeys;

    private List<Extension> extensions;

    private List<String> extTypes;

    private Map<String, Integer> extTypeOrderMap;

    private List<SaleItem> saleItems;

    @Before
    public void before() {
        allKeys = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        extTypes = new ArrayList<>(ExtensionComparators.EXT_TYPE_ORDER_MAP.keySet());
        extTypeOrderMap = ExtensionComparators.EXT_TYPE_ORDER_MAP;

        extensions = genExtensions(10);
        saleItems = genSaleItems(12);
    }

    /**
     * generate test data
     * @param n
     * @return
     */
    private List<SaleItem> genSaleItems(int n) {
        List<SaleItem> list = new ArrayList<>(n);

        Random random = new Random();

        for (int i = 0; i < n; i++) {
            list.add(new SaleItem(i > 11 ? i % 12 : i, random.nextDouble() * 100.0));
        }

        return list;
    }

    /**
     * generate test data
     *
     * @param count
     * @return
     */
    private List<Extension> genExtensions(int count) {
        List<Extension> list = new ArrayList<>(count);
        Random random = new Random();
        int r;
        Extension e;
        for (int i = 0; i < count; i++) {
            e = new Extension();
            r = random.nextInt(count);
            e.setFirstName(r % 2 == 0 ? null :  r + "first");
            r = random.nextInt(count);
            e.setLastName(r % 2 == 0 ? null : r + "lastName");
            r = random.nextInt(count);
            e.setExt(r % 5 == 0 ? null : r + "ext");

            r = random.nextInt(count);
            e.setExtType(extTypes.get(r % 5));

            list.add(e);
        }
        return list;
    }

    /**
     * case: normal
     */
    @Test
    public void test_getUnUsedKeys() {
        int[] usedKeys = {2, 3, 4, 8};
        int[] unusedKeys = Utils.getUnUsedKeys(allKeys, usedKeys);
        Assert.assertNotNull(unusedKeys);
        Assert.assertEquals(allKeys.length - usedKeys.length, unusedKeys.length);

        Set<Integer> allKeySet = Arrays.stream(allKeys).boxed().collect(Collectors.toSet());
        Set<Integer> usedKeySet = Arrays.stream(usedKeys).boxed().collect(Collectors.toSet());
        for (int unusedKey : unusedKeys) {
            Assert.assertTrue(allKeySet.contains(unusedKey));
            Assert.assertFalse(usedKeySet.contains(unusedKey));
        }
    }

    /**
     * case: allKeys is empty
     */
    @Test
    public void test_getUnUsedKeys_allKeysIsEmpty() {
        int[] usedKeys = {2, 3, 4, 8};
        int[] unusedKeys = Utils.getUnUsedKeys(new int[]{}, usedKeys);
        Assert.assertNotNull(unusedKeys);
        Assert.assertEquals(0, unusedKeys.length);

        unusedKeys = Utils.getUnUsedKeys(null, usedKeys);
        Assert.assertNotNull(unusedKeys);
        Assert.assertEquals(0, unusedKeys.length);
    }

    /**
     * case: usedKeys is empty
     */
    @Test
    public void test_getUnUsedKeys_usedKeysIsEmpty() {
        int[] unusedKeys = Utils.getUnUsedKeys(allKeys, new int[]{});
        Assert.assertNotNull(unusedKeys);
        Assert.assertEquals(allKeys.length, unusedKeys.length);

        unusedKeys = Utils.getUnUsedKeys(allKeys, null);
        Assert.assertNotNull(unusedKeys);
        Assert.assertEquals(allKeys.length, unusedKeys.length);
    }

    /**
     * case: normal
     */
    @Test
    public void test_compare() {
        Assert.assertTrue(Utils.compare("", "hello") < 0);
        Assert.assertTrue(Utils.compare("hello", "") > 0);
        Assert.assertTrue(Utils.compare(null, "hello") < 0);
        Assert.assertTrue(Utils.compare("hello", null) > 0);
        Assert.assertTrue(Utils.compare("hello", "world") < 0);
        Assert.assertTrue(Utils.compare("world", "hello") > 0);
        Assert.assertEquals(0, Utils.compare("", ""));
        Assert.assertEquals(0, Utils.compare(null, null));
        Assert.assertEquals(0, Utils.compare("hello", "hello"));
    }

    /**
     * case: normal
     */
    @Test
    public void test_sortByName() {
        List<Extension> sortedList = Utils.sortByName(extensions);
        Assert.assertNotNull(sortedList);

        Extension pre = sortedList.get(0);
        Extension current;
        for (int i = 1; i < sortedList.size(); i++,  pre = current) {
            current = sortedList.get(i);

            int result = Utils.compare(pre.getFirstName(), current.getFirstName());
            if (result == 0) {
                result = Utils.compare(pre.getLastName(), current.getLastName());
                if (result == 0) {
                    result = Utils.compare(pre.getExt(), current.getExt());

                    Assert.assertTrue(result <= 0);
                    continue;
                }
                Assert.assertTrue(result < 0);
                continue;
            }
            Assert.assertTrue(result < 0);
        }
    }

    /**
     * case: normal
     */
    @Test
    public void test_sortByExtType() {
        List<Extension> sortedList = Utils.sortByExtType(extensions);
        Assert.assertNotNull(sortedList);

        Extension pre = sortedList.get(0);
        Extension current;
        for (int i = 1; i < sortedList.size(); i++,  pre = current) {
            current = sortedList.get(i);

            Assert.assertTrue(
                    extTypeOrderMap.get(pre.getExtType()) <= extTypeOrderMap.get(current.getExtType()));
        }
    }

    /**
     * case: illegal extType
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_sortByExtType_illegalExtType() {
        List<Extension> list = genExtensions(2);
        list.get(0).setExtType("users");
        List<Extension> sortedList = Utils.sortByExtType(list);
    }

    /**
     * case: list is empty
     */
    @Test
    public void test_sortEmptyList() {
        Assert.assertTrue(Utils.sortByName(new ArrayList<>()).isEmpty());
        Assert.assertNull(Utils.sortByName(null));

        Assert.assertTrue(Utils.sortByExtType(new ArrayList<>()).isEmpty());
        Assert.assertNull(Utils.sortByExtType(null));

    }

    /**
     * case: normal
     */
    @Test
    public void test_sumByQuarter() {
        List<QuarterSalesItem> list = Utils.sumByQuarter(saleItems);
        Assert.assertNotNull(list);
        Assert.assertEquals(4, list.size());

        double sumAll = 0.0;
        int quarter;
        Double quarterSum;
        Map<Integer, Double> quarterValMap = new HashMap<>(4);
        for (SaleItem saleItem : saleItems) {
            sumAll += saleItem.getSaleNumbers();
            quarter = QuarterEnum.from(saleItem.getMonth()).ordinal();
            quarterSum = quarterValMap.get(quarter);
            quarterValMap.put(quarter, (quarterSum == null ? 0.0 : quarterSum) + saleItem.getSaleNumbers());
        }

        double quarterSumAll = 0.0;
        for (QuarterSalesItem quarterSalesItem : list) {
            quarterSumAll += quarterSalesItem.getValue();

            Assert.assertEquals(quarterValMap.get(quarterSalesItem.getQuarter()), quarterSalesItem.getValue(), 0.000001);
        }
        Assert.assertEquals(sumAll, quarterSumAll, 0.000001);
    }

    /**
     * case: normal
     */
    @Test
    public void test_maxByQuarter() {
        List<QuarterSalesItem> list = Utils.maxByQuarter(saleItems);
        Assert.assertNotNull(list);
        Assert.assertEquals(4, list.size());

        int quarter;
        Map<Integer, Double> quarterValMap = new HashMap<>(4);
        for (SaleItem saleItem : saleItems) {
            quarter = QuarterEnum.from(saleItem.getMonth()).ordinal();
            Double max = quarterValMap.get(quarter);
            if (max ==  null) {
                quarterValMap.put(quarter, saleItem.getSaleNumbers());
                continue;
            }
            if (max < saleItem.getSaleNumbers()) {
                quarterValMap.put(quarter, saleItem.getSaleNumbers());
            }
        }

        for (QuarterSalesItem quarterSalesItem : list) {
            Assert.assertEquals(quarterValMap.get(quarterSalesItem.getQuarter()),
                    quarterSalesItem.getValue(),0.000001);
        }
    }

    /**
     * case: compute list is empty
     */
    @Test
    public void test_emptyList() {
        List<QuarterSalesItem> list = Utils.maxByQuarter(null);
        Assert.assertNotNull(list);
        Assert.assertEquals(0, list.size());

        list = Utils.sumByQuarter(null);
        Assert.assertNotNull(list);
        Assert.assertEquals(0, list.size());

        list = Utils.sumByQuarter(null);
        Assert.assertNotNull(list);
        Assert.assertEquals(0, list.size());
    }

    /**
     * case: saleItem's month is illegal
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_maxByQuarter_illegalMonth() {
        List<SaleItem> list = genSaleItems(1);
        list.get(0).setMonth(12);
        Utils.maxByQuarter(list);
    }

    /**
     * case: saleItem's month is illegal
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_maxByQuarter_illegalMonth2() {
        List<SaleItem> list = genSaleItems(1);
        list.get(0).setMonth(-1);
        Utils.maxByQuarter(list);
    }

    /**
     * case: saleItem's month is illegal
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_sumByQuarter_illegalMonth() {
        List<SaleItem> list = genSaleItems(1);
        list.get(0).setMonth(12);
        Utils.sumByQuarter(list);
    }

    /**
     * case: saleItem's month is illegal
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_sumByQuarter_illegalMonth2() {
        List<SaleItem> list = genSaleItems(1);
        list.get(0).setMonth(-1);
        Utils.sumByQuarter(list);
    }
}
