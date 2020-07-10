package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

	//Question1
	public static List<Extension> sortByName(List<Extension> extensions) {
		return null;
	}


	//Question2
	public static List<Extension> sortByExtType(List<Extension> extensions) {
		return null;
	}

	//Question3
	public static List<QuarterSalesItem> sumByQuarter(List<SaleItem> saleItems) {
		if (saleItems == null || saleItems.size() == 0) {
			return Collections.emptyList();
		}

		int quarter;
		QuarterSalesItem quarterSalesItem;
		// quarter as key
		Map<Integer, QuarterSalesItem> quarterSalesItemMap = new HashMap(4);

		for (SaleItem saleItem : saleItems) {
			quarter = whichQuarter(saleItem.getMonth());
			quarterSalesItem = quarterSalesItemMap.get(quarter);
			if (quarterSalesItem == null) {
				quarterSalesItem = new QuarterSalesItem();
				quarterSalesItem.setQuarter(quarter);
				quarterSalesItem.setValue(0.0);

				quarterSalesItemMap.put(quarter, quarterSalesItem);
			}
			quarterSalesItem.setValue(quarterSalesItem.getValue() + saleItem.getSaleNumbers());
		}

		return new ArrayList<>(quarterSalesItemMap.values());
	}

	private static int whichQuarter(int month) {
		return 0;
	}

	/**
	 * Question4
	 *
	 * max all sales items by quarter
	 * @param saleItems
	 * @return
	 */
	public static List<QuarterSalesItem> maxByQuarter(List<SaleItem> saleItems) {
		return null;
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
	 * @return all unused keys
	 */
	public static int[] getUnUsedKeys(int[] allKeys, int[] usedKeys) {
		if (allKeys == null || allKeys.length < 1) {
			return new int[0];
		}
		if (usedKeys == null || usedKeys.length < 1) {
			return allKeys;
		}
		;
		Set<Integer> usedSet = Arrays.stream(usedKeys).boxed().collect(Collectors.toSet());
		return Arrays.stream(allKeys).filter(key -> !usedSet.contains(key)).toArray();
	}

}
