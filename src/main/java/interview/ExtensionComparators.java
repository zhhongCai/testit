package interview;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: theonecai
 * @Date: Create in 2020/7/11 20:39
 * @Description:
 */
public class ExtensionComparators {

    public static final ExtensionExtTypeComparator EXT_TYPE_COMPARATOR = new ExtensionExtTypeComparator();
    public static final ExtensionNameComparator NAME_COMPARATOR = new ExtensionNameComparator();

    public static final Map<String, Integer> EXT_TYPE_ORDER_MAP;

    static {
        Map<String, Integer> map = new HashMap<>(5);
        map.put("Other", 1);
        map.put("TMO", 2);
        map.put("AO", 3);
        map.put("Dept", 4);
        map.put("User", 5);

        EXT_TYPE_ORDER_MAP = Collections.unmodifiableMap(map);
    }

    static class ExtensionExtTypeComparator implements Comparator<Extension> {


        /**
         * sort extType, extType is a string and can
         * be "User", "Dept", "AO", "TMO", "Other",
         * sort by User > Dept > AO > TMO > Other;
         * @param first
         * @param second
         * @return
         */
        @Override
        public int compare (Extension first, Extension second){
            return Optional.ofNullable(EXT_TYPE_ORDER_MAP.get(first.getExtType()))
                        .orElseThrow(() -> new IllegalArgumentException("unknown extType " + first.getExtType()))
                    .compareTo(Optional.ofNullable(EXT_TYPE_ORDER_MAP.get(second.getExtType()))
                            .orElseThrow(() -> new IllegalArgumentException("unknown extType " + second.getExtType())));
        }
    }

    static class ExtensionNameComparator implements Comparator<Extension> {


        /**
         * sort by firstName + lastName + ext,
         * if firstName is the same then sort by lastName and
         * ext, please note lastName and ext can be empty string or null.
         * @param first
         * @param second
         * @return
         */
        @Override
        public int compare(Extension first, Extension second) {
            int result = Utils.compare(first.getFirstName(), second.getFirstName());
            if (result != 0) {
                return result;
            }

            result = Utils.compare(first.getLastName(), second.getLastName());
            if (result != 0) {
                return result;
            }

            return Utils.compare(first.getExt(), second.getExt());
        }
    }
}
