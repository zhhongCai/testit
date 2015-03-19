package com.test.it.guava;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * Author: caizh
 * CreateTime: 2015/3/16 15:37
 * Version: 1.0
 */
public class ObjectsTest {
    private String name;
    private Integer count;

    public ObjectsTest(String name, Integer count) {
        this.name = Preconditions.checkNotNull(name, "名称不能为空");
        Preconditions.checkArgument(count > 0, "count必须大于0");
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, count);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ObjectsTest) || obj == this) {
            return false;
        }
        ObjectsTest t = (ObjectsTest) obj;
        return Objects.equal(name, t.getName())
                && Objects.equal(count, t.getCount());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", this.name)
                .add("count", this.count).toString();
    }

    public static void main(String[] args) {
        ObjectsTest test = new ObjectsTest("test", 12);
        ObjectsTest test2 = new ObjectsTest("test", 2);
        System.out.println("test equal test2: " + test.equals(test2));
        System.out.println(test.toString());
        System.out.println(test2.toString());
        ObjectsTest test3 = new ObjectsTest("test3", -1);
//        ObjectsTest test4 = new ObjectsTest(null, 123);
    }
}
