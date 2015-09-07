package com.test.it.pattern.bridge;

/**
 * Created by caizh on 2015/8/14.
 */
public interface Persistence {
    public String persist(Object o);

    public Object findById(String id);

    public void deleteById(String id);
}
