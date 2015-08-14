package com.test.it.effectivejava;

import java.util.HashMap;
import java.util.Map;

/**
 * chap05: 第29条
 * Author: caizh
 * CreateTime: 2015/4/29 9:02
 * Version: 1.0
 */
public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<Class<?>, Object>();

    public <T> void putFavorites(Class<T> type, T data) {
        if(null == type) {
            throw new NullPointerException("");
        }
        favorites.put(type, data);
    }

    public <T> T getFavorites(Class<T> type) {
        return type.cast(favorites.get(type));
    }


    public static void main(String[] args) {
        Favorites favorites1 = new Favorites();
        favorites1.putFavorites(String.class, "string data");
        favorites1.putFavorites(Integer.class, 123);
        favorites1.putFavorites(Long.class, 321L);
        favorites1.putFavorites(String.class, "data string");

        System.out.println(favorites1.getFavorites(String.class));
    }

}
