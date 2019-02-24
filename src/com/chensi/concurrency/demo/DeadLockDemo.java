package com.chensi.concurrency.demo;

/**
 * 死锁示例
 * author:chensi
 */
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.currentThread().sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("1");
                }
            }
        });
        Thread t2 = new Thread(() -> {
           synchronized (B) {
               synchronized (A) {
                   System.out.println("2");
               }
           }
        });
        t1.start();
        t2.start();
    }
}