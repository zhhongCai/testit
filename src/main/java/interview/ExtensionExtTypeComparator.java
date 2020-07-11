package interview;

import java.util.Comparator;
import java.util.Optional;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2020/7/10 21:11
 * @Description:
 */
public class ExtensionExtTypeComparator implements Comparator<Extension> {

    public final static ExtensionExtTypeComparator INSTANCE = new ExtensionExtTypeComparator();

    private final static String EMPTY_STR = "";

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
        int result = Optional.ofNullable(first.getFirstName())
                    .orElseThrow(() -> new IllegalArgumentException("first's firstName can't be null"))
                .compareTo(Optional.ofNullable(second.getFirstName())
                        .orElseThrow(() -> new IllegalArgumentException("second's firstName can't be null")));
        if (result != 0) {
            return result;
        }

        result = Optional.ofNullable(first.getLastName()).orElse(EMPTY_STR)
                .compareTo(Optional.ofNullable(second.getLastName()).orElse(EMPTY_STR));
        if (result != 0) {
            return result;
        }

        return Optional.ofNullable(first.getExt()).orElse(EMPTY_STR)
                .compareTo(Optional.ofNullable(second.getExt()).orElse(EMPTY_STR));
    }
}
