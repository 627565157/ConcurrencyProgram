package com.chensi.concurrency.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 使用Fork/Join计算 1-10的和
 * chensi
 * 2019/3/3
 **/
public class ForkJoinCountTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 3;//阈值
    private int start;
    private int end;

    public ForkJoinCountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if(canCompute) {
            for(int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            //如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            ForkJoinCountTask leftTask = new ForkJoinCountTask(start, middle);
            ForkJoinCountTask rightTask = new ForkJoinCountTask(middle + 1, end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            //合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinCountTask forkJoinCountTask = new ForkJoinCountTask(1, 10);
        //执行一个任务
        Future<Integer> result = forkJoinPool.submit(forkJoinCountTask);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {

        }
    }
}
