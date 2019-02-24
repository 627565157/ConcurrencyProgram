package com.chensi.concurrency.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 循环CAS实现原子操作
 * 使用CAS实现原子操作的问题：ABA问题;循环时间开销大;只能保证一个共享变量的操作
 * author:chensi
 **/
public class CASCounter {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        final CASCounter cas = new CASCounter();
        List<Thread> ts = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    cas.count();
                    cas.safeCount();
                }
            });
            ts.add(t);
        }
        for (Thread thread : ts) {
            thread.start();
        }
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    private void safeCount() {
        for (; ; ) {
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    private void count() {
        i++;
    }
}
