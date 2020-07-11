package interview;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2020/7/10 21:31
 * @Description:
 */
public interface Computer {

    /**
     * compute SaleItem to QuarterSalesItem, save/update result to quarterSalesItemMap
     *
     * @param quarterSalesItemMap
     * @param saleItem
     */
    void compute(@NotNull Map<Integer, QuarterSalesItem> quarterSalesItemMap, @NotNull SaleItem saleItem);

    default List<QuarterSalesItem> compute(List<SaleItem> saleItems) {
        if (Utils.isEmpty(saleItems)) {
            return Collections.emptyList();
        }

        // quarter as key
        Map<Integer, QuarterSalesItem> quarterSalesItemMap = new HashMap<>(4);

        saleItems.forEach(saleItem -> this.compute(quarterSalesItemMap, saleItem));

        return new ArrayList<>(quarterSalesItemMap.values());
    }
}
