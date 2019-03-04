package com.chensi.concurrency.demo;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * chensi
 * 2019/3/4
 **/
public class ExchangerTest {
    private static final Exchanger<String> exgr = new Exchanger<String>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(() -> {
            try {
                String A = "银行流水A";
                String changerContent = exgr.exchange(A);
                System.out.println("changerContent: " + changerContent);
            } catch (InterruptedException e) {}
        });
        threadPool.execute(() -> {
            try {
                String B = "银行流水B";
                String A = exgr.exchange(B);
                System.out.println("A: " + A);
            } catch (InterruptedException e) {}
        });
        threadPool.shutdown();
    }
}
