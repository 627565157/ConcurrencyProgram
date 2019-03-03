package com.chensi.concurrency.demo;

import java.util.Map;
import java.util.concurrent.*;

/**
 * chensi
 * 2019/3/3
 **/
public class BankWaterServiceOfCyclicBarrier implements Runnable{
    /**
     * 创建4个屏障，处理完成之后执行当前类的run方法
     */
    private CyclicBarrier c = new CyclicBarrier(4, this);

    /**
     * 假设只有4个sheet，所以只启动4个线程
     */
    private Executor executor = Executors.newFixedThreadPool(4);

    /**
     * 保存每个sheet计算出的银行流水结果
     */
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

    private void count() {
        for(int i = 0; i < 4; i++) {
            executor.execute(() -> {
                sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                try {
                    c.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    public static void main(String[] args) {
        BankWaterServiceOfCyclicBarrier bankWaterServiceOfCyclicBarrier = new BankWaterServiceOfCyclicBarrier();
        bankWaterServiceOfCyclicBarrier.count();
    }

    @Override
    public void run() {
        int result = 0;
        for(Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }
        //将结果输出
        sheetBankWaterCount.put("result", result);
        System.out.println(result);
    }
}
