package interview;

import java.util.Comparator;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2020/7/10 21:11
 * @Description:
 */
public class ExtensionExtTypeComparator implements Comparator<Extension> {

    /**
     * sort by firstName + lastName + ext,
     * if firstName is the same then sort by lastName and
     * ext, please note lastName and ext can be empty string or null.
     * @param extension
     * @param extension2
     * @return
     */
    @Override
    public int compare(Extension extension, Extension extension2) {
        int result = extension.getFirstName().compareTo(extension2.getFirstName());
        if (result != 0) {
            return result;
        }



        return 0;
    }
}
