package com.chensi.concurrency.demo;

import java.util.HashMap;
import java.util.UUID;

/**
 * HashMap在多线程环境下并发执行put操作会引起死循环，导致CPU利用率接近100%
 * chensi
 * 2019/2/26
 **/
public class HashMapMultiThreadErr {

    public static void main(String[] args) throws Exception{
        final HashMap<String, String> map = new HashMap<String, String>(2);
        Thread t = new Thread(() -> {
            for(int i = 0; i < 1000000; i++) {
                new Thread(() -> {
                    map.put(UUID.randomUUID().toString(), "");
                }, "ftf" + i).start();
            }
        }, "ftf");
        t.start();
        t.join();
    }
}
