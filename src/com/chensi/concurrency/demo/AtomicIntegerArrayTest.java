package com.chensi.concurrency.demo;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * chensi
 * 2019/3/3
 **/
public class AtomicIntegerArrayTest {
    static int[] value = new int[] {1,2};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0, 3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }
}
