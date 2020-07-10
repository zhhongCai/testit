package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

	private final static ExtensionNameComparator NAME_COMPARATOR = new ExtensionNameComparator();
	private final static ExtensionExtTypeComparator EXT_TYPE_COMPARATOR = new ExtensionExtTypeComparator();

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
		return doSort(extensions, NAME_COMPARATOR);
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
		return doSort(extensions, EXT_TYPE_COMPARATOR);
	}

	/**
	 * sort extensions by comparator
	 * @param extensions
	 * @param comparator
	 * @return
	 */
	private static List<Extension> doSort(List<Extension> extensions, Comparator<Extension> comparator) {
		if (extensions == null || extensions.size() == 0) {
			return extensions;
		}

		extensions.sort(comparator);
		return extensions;
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
		if (saleItems == null || saleItems.size() == 0) {
			return Collections.emptyList();
		}

		QuarterEnum quarter;
		QuarterSalesItem item;
		Map<Integer, QuarterSalesItem> quarterSalesItemMap = new HashMap<>(4);

		for (SaleItem saleItem : saleItems) {
			quarter = QuarterEnum.quarterFrom(saleItem.getMonth());

			item = quarterSalesItemMap.get(quarter.ordinal());
			if (item == null) {
				item = new QuarterSalesItem();
				item.setQuarter(quarter.ordinal());
				item.setValue(0.0);

				quarterSalesItemMap.put(quarter.ordinal(), item);
			}
			item.setValue(item.getValue() + saleItem.getSaleNumbers());
		}

		return new ArrayList<>(quarterSalesItemMap.values());
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
		if (saleItems == null || saleItems.size() == 0) {
			return Collections.emptyList();
		}

		QuarterEnum quarter;
		QuarterSalesItem item;
		// quarter as key
		Map<Integer, QuarterSalesItem> quarterSalesItemMap = new HashMap<>(4);

		for (SaleItem saleItem : saleItems) {
			quarter = QuarterEnum.quarterFrom(saleItem.getMonth());

			item = quarterSalesItemMap.get(quarter);
			if (item == null) {
				item = new QuarterSalesItem();
				item.setQuarter(quarter.ordinal());
				item.setValue(saleItem.getSaleNumbers());

				quarterSalesItemMap.put(quarter.ordinal(), item);
				continue;
			}
			if (saleItem.getSaleNumbers() > item.getValue()) {
				item.setValue(saleItem.getSaleNumbers());
			}
		}

		return new ArrayList<>(quarterSalesItemMap.values());
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
		if (allKeys == null || allKeys.length == 0) {
			return new int[0];
		}
		if (usedKeys == null || usedKeys.length == 0) {
			return allKeys;
		}

		Set<Integer> usedSet = Arrays.stream(usedKeys).boxed().collect(Collectors.toSet());
		return Arrays.stream(allKeys).filter(key -> !usedSet.contains(key)).toArray();
	}

}
