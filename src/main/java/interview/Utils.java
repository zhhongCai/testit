package interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

	/**
	 * Question1
	 *
	 * sort by firstName + lastName + ext,
	 * if firstName is the same then sort by lastName and
	 * ext, please note lastName and ext can be empty
	 * string or null.
	 *
	 * @param extensions
	 * @return
	 */
	public static List<Extension> sortByName(List<Extension> extensions) {
		return doSort(extensions, ExtensionNameComparator.INSTANCE);
	}

	/**
	 * Question2
	 *
	 * sort extType, extType is a string and can
	 * be "User", "Dept", "AO", "TMO", "Other",
	 * sort by User > Dept > AO > TMO > Other;
	 *
	 * @param extensions
	 * @return
	 */
	public static List<Extension> sortByExtType(List<Extension> extensions) {
		return doSort(extensions, ExtensionExtTypeComparator.INSTANCE);
	}

	/**
	 * sort list by comparator
	 *
	 * @param list
	 * @param comparator
	 * @return
	 */
	private static <T> List<T> doSort(List<T> list, Comparator<T> comparator) {
		if (isEmpty(list)) {
			return list;
		}

		list.sort(comparator);
		return list;
	}

	/**
	 * Question3
	 *
	 * sum all sales items by quarter
	 *
	 * @param saleItems
	 * @return sorted saleItems
	 */
	public static List<QuarterSalesItem> sumByQuarter(List<SaleItem> saleItems) {
		return SumByQuarterComputer.INSTANCE.compute(saleItems);
	}

	/**
	 * Question4
	 *
	 * max all sales items by quarter
	 *
	 * @param saleItems
	 * @return sorted saleItems
	 */
	public static List<QuarterSalesItem> maxByQuarter(List<SaleItem> saleItems) {
		return MaxByQuarterComputer.INSTANCE.compute(saleItems);
	}

	/**
	 * list is null or it's size is zero return true
	 *
	 * @param list
	 * @param <T>
	 * @return
	 */
	public static <T> boolean isEmpty(List<T> list) {
		return list == null || list.size() == 0;
	}

	/**
	 * array is null or it's length is zero return true
	 *
	 * @param array
	 * @return
	 */
	private static boolean isEmpty(int[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Question5
	 *
	 * We have all Keys: 0-9;
	 * usedKeys is an array to store all used keys like :[2,3,4];
	 * We want to get all unused keys, in this example it would be: [0,1,5,6,7,8,9,]
	 *
	 * @param allKeys all keys we have
	 * @param usedKeys keys been used
	 * @return keys in allKeys and not in usedKeys
	 */
	public static int[] getUnUsedKeys(int[] allKeys, int[] usedKeys) {
		if (isEmpty(allKeys)) {
			return new int[0];
		}
		if (isEmpty(usedKeys)) {
			return allKeys;
		}

		Set<Integer> usedSet = Arrays.stream(usedKeys).boxed().collect(Collectors.toSet());
		return Arrays.stream(allKeys).filter(key -> !usedSet.contains(key)).toArray();
	}

}
