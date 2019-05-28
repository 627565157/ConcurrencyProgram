package com.chensi.concurrency.demo;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal对象为键、任意对象为值的存储结构。一个线程可以根据一个ThreadLocal对象查询到绑定在这个线程上的一个值。
 * 可以被复用在方法调用耗时统计上：使用AOP，在方法调用切入点之前执行begin,方法调用后的切入点执行end，这样可以统计
 * 方法的执行耗时
 * @Author: chensi
 * @Date: 2019/5/28 22:30
 * @Version 1.0
 */
public class ThreadLocalTest {
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>();
    protected Long initialValue() {
        return System.currentTimeMillis();
    }

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws Exception {
        ThreadLocalTest.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + ThreadLocalTest.end() +" mills");
    }
}
