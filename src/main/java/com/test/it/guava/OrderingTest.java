package com.test.it.guava;

import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: caizh
 * CreateTime: 2015/3/16 15:12
 * Version: 1.0
 */
public class OrderingTest {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(23);
        numbers.add(11);
        numbers.add(26);
        numbers.add(62);
        numbers.add(5);
        numbers.add(0);
        numbers.add(51);

        Ordering ordering = Ordering.natural();
        System.out.println("numbers list: " + numbers);

        Collections.sort(numbers, ordering);
        System.out.println("sorted in natural order: " + numbers);

        System.out.println("list is sorted: " + ordering.isOrdered(numbers));
        System.out.println("min num: " + ordering.min(numbers));
        System.out.println("max num: " + ordering.max(numbers));

        Collections.sort(numbers, ordering.reverse());
        System.out.println("natural reverse list: " + numbers);

        numbers.add(null);
        Collections.sort(numbers, ordering.nullsFirst());
        System.out.println("null first list: " + numbers);
        Collections.sort(numbers, ordering.nullsFirst().reverse());
        System.out.println("reverse null first list: " + numbers);

    }
}
