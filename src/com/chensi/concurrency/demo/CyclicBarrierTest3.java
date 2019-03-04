package com.chensi.concurrency.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * chensi
 * 2019/3/4
 **/
public class CyclicBarrierTest3 {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        Thread thread = new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch(Exception e) {

            }
        });
        thread.start();
        thread.interrupt();
        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            System.out.println(cyclicBarrier.isBroken());
        }
    }
}
