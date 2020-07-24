package interview.rc;


/**
 * Quarter enum
 *
 * @Author: theonecai
 * @Date: Create in 2020/7/10 21:11
 * @Description:
 */
public enum  QuarterEnum {
    SPRING(0, 2),
    SUMMER(3, 5),
    AUTUMN(6, 8),
    WINTER(9, 11);

    /**
     * quarter's start month
     */
    private final int startMonth;

    /**
     * quarter's end month
     */
    private final int endMonth;


    private QuarterEnum(int startMonth, int endMonth) {
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }

    public static QuarterEnum from(int month) {
        if (month < SPRING.startMonth || month > WINTER.endMonth) {
            throw new IllegalArgumentException("month must be from 0 to 11, now is " + month);
        }
        if (month < SUMMER.startMonth) {
            return SPRING;
        }
        if (month < AUTUMN.startMonth) {
            return SUMMER;
        }
        if (month < WINTER.startMonth) {
            return AUTUMN;
        }
        return WINTER;
    }
}
