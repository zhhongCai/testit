package interview;

import java.util.Comparator;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2020/7/10 21:09
 * @Description:
 */
public class ExtensionNameComparator implements Comparator<Extension> {

    /**
     * sort extType, extType is a string and can
     * be "User", "Dept", "AO", "TMO", "Other",
     * ort by User > Dept > AO > TMO > Other;
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Extension o1, Extension o2) {
        return 0;
    }
}
