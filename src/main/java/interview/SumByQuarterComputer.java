package interview;

import com.sun.istack.internal.NotNull;

import java.util.Map;


public class SumByQuarterComputer implements Computer {

    public final static SumByQuarterComputer INSTANCE = new SumByQuarterComputer();

    @Override
    public void compute(@NotNull Map<Integer, QuarterSalesItem> quarterSalesItemMap, SaleItem saleItem) {
        QuarterEnum quarter = QuarterEnum.from(saleItem.getMonth());
        QuarterSalesItem quarterSalesItem = quarterSalesItemMap.get(quarter.ordinal());
        if (quarterSalesItem == null) {
            quarterSalesItem =  new QuarterSalesItem(quarter.ordinal(), 0.0);
            quarterSalesItemMap.put(quarter.ordinal(), quarterSalesItem);
        }
        quarterSalesItem.setValue(quarterSalesItem.getValue() + saleItem.getSaleNumbers());
    }
}
