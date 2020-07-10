package interview;


/**
 * Quarter enum
 *
 * @Author: zhenghong.cai
 * @Date: Create in 2020/7/10 21:11
 * @Description:
 */
public enum  QuarterEnum {
    SPRING("Spring", 0, 2),
    SUMMER("Summer", 3, 5),
    AUTUMN("Autumn", 6, 8),
    WINTER("Winter", 9, 11);

    /**
     * quarter's name
     */
    private String name;

    /**
     * quarter's begin month
     */
    private int beginMonth;

    /**
     * quarter's end month
     */
    private int endMonth;


    private QuarterEnum(String name, int beginMonth, int endMonth) {
        this.name = name;
        this.beginMonth = beginMonth;
        this.endMonth = endMonth;
    }

    public static QuarterEnum quarterFrom(int month) {
        if (SPRING.beginMonth > month || month > WINTER.endMonth) {
            throw new IllegalArgumentException("month must be from 0 to 11, now is " + month);
        }
        if (month < SUMMER.beginMonth) {
            return SPRING;
        }
        if (month < AUTUMN.beginMonth) {
            return SUMMER;
        }
        if (month < WINTER.beginMonth) {
            return AUTUMN;
        }
        return WINTER;
    }
}
