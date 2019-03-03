package com.chensi.concurrency.demo;

import java.util.concurrent.CyclicBarrier;

/**
 * chensi
 * 2019/3/3
 **/
public class CyclicBarrierTest {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch(Exception e) {
            }
            System.out.println(1);
        }).start();
        try {
            cyclicBarrier.await();
        } catch(Exception e) {
        }
        System.out.println(2);
    }
}
