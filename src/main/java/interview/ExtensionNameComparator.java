package interview;

import java.util.Comparator;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2020/7/10 21:09
 * @Description:
 */
public class ExtensionNameComparator implements Comparator<Extension> {

    public final static ExtensionNameComparator INSTANCE = new ExtensionNameComparator();

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
