package com.chensi.concurrency.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * chensi
 * 2019/3/3
 **/
public class AtomicIntegerTest {

    static AtomicInteger ai = new AtomicInteger(1);

    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.get());
    }
}
