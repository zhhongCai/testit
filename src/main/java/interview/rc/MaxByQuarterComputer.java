package interview.rc;

import com.sun.istack.internal.NotNull;

import java.util.Map;


/**
 * @Author: theonecai
 * @Date: Create in 2020/7/11 21:31
 * @Description:
 */
public class MaxByQuarterComputer implements Computer {

    public final static MaxByQuarterComputer INSTANCE = new MaxByQuarterComputer();

    @Override
    public void compute(@NotNull Map<Integer, QuarterSalesItem> quarterSalesItemMap, @NotNull SaleItem saleItem) {
        QuarterEnum quarter = QuarterEnum.from(saleItem.getMonth());
        QuarterSalesItem quarterSalesItem = quarterSalesItemMap.get(quarter.ordinal());

        if (quarterSalesItem == null) {
            quarterSalesItem = new QuarterSalesItem(quarter.ordinal(), saleItem.getSaleNumbers());

            quarterSalesItemMap.put(quarter.ordinal(), quarterSalesItem);
            return;
        }

        quarterSalesItem.setValue(Math.max(saleItem.getSaleNumbers(), quarterSalesItem.getValue()));
    }
}
