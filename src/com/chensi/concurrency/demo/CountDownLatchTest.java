package com.chensi.concurrency.demo;

import java.util.concurrent.CountDownLatch;

/**
 * chensi
 * 2019/3/3
 **/
public class CountDownLatchTest {
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println(1);
            countDownLatch.countDown();
        }).start();
        new Thread(() -> {
            System.out.println(2);
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
        System.out.println(3);
    }
}
