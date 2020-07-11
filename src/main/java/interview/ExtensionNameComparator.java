package interview;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2020/7/10 21:09
 * @Description:
 */
public class ExtensionNameComparator implements Comparator<Extension> {

    public final static ExtensionNameComparator INSTANCE = new ExtensionNameComparator();

    private static final Map<String, Integer> EXT_TYPE_ORDER_MAP = new HashMap<>(5);

    static  {
        EXT_TYPE_ORDER_MAP.put("Other", 1);
        EXT_TYPE_ORDER_MAP.put("TMO", 2);
        EXT_TYPE_ORDER_MAP.put("AO", 3);
        EXT_TYPE_ORDER_MAP.put("Dept", 4);
        EXT_TYPE_ORDER_MAP.put("User", 5);
    }

    /**
     * sort extType, extType is a string and can
     * be "User", "Dept", "AO", "TMO", "Other",
     * sort by User > Dept > AO > TMO > Other;
     * @param first
     * @param second
     * @return
     */
    @Override
    public int compare(Extension first, Extension second) {
        return Optional.ofNullable(EXT_TYPE_ORDER_MAP.get(first.getExtType()))
                    .orElseThrow(() -> new IllegalArgumentException("unknown extType " + first.getExtType()))
                .compareTo(Optional.ofNullable(EXT_TYPE_ORDER_MAP.get(second.getExtType()))
                        .orElseThrow(() -> new IllegalArgumentException("unknown extType " + second.getExtType())));
    }
}
