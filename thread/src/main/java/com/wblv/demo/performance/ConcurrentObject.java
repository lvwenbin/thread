package com.wblv.demo.performance;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentObject {
	private static Random    random    = new Random();  
	  
    private final static int READ_NUM  = 100;  
  
    private final static int WRITE_NUM = 100;  
  
    private int              value;  
  
    private ReadWriteLock    lock      = new ReentrantReadWriteLock();  
  
    private Lock             locknew   = new ReentrantLock();  
  
    public static void main(String[] args) throws InterruptedException {  
  
        // int maxProcessor = Runtime.getRuntime().availableProcessors() * 2; 防止线程池大小过大，CPU过多的上下文切换导致的开销影响  
        int maxProcessor = READ_NUM + WRITE_NUM;// 线程池大小必须同 总共开启的对象  
        final ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(maxProcessor);  
  
        final CountDownLatch latch = new CountDownLatch(READ_NUM + WRITE_NUM);// 最后关闭线程池  
        final CyclicBarrier barrier = new CyclicBarrier(READ_NUM + WRITE_NUM);// 等待所有线程启动后并发读写  
  
        final ConcurrentObject concurrentObject = new ConcurrentObject();  
  
        for (int i = 0; i < READ_NUM; i++) {  
            newFixedThreadPool.execute(new Runnable() {  
  
                @Override  
                public void run() {  
                    try {  
                        barrier.await();  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
  
                    TimeCostUtils.start(TimeCostUtils.READ);  
                    concurrentObject.getValueLock();  
                    TimeCostUtils.end();  
  
                    latch.countDown();  
                }  
            });  
        }  
  
        for (int i = 0; i < WRITE_NUM; i++) {  
            newFixedThreadPool.execute(new Runnable() {  
  
                @Override  
                public void run() {  
  
                    int nextInt = random.nextInt(1000);  
                    try {  
                        barrier.await();  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
  
                    TimeCostUtils.start(TimeCostUtils.WRITE);  
                    concurrentObject.setValueLock(nextInt);  
                    TimeCostUtils.end();  
  
                    latch.countDown();  
                }  
            });  
        }  
  
        latch.await();  
  
        newFixedThreadPool.shutdown();  
  
        // 系统推出前，关闭线程池及计算平均耗时、总耗时  
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {  
  
            @Override  
            public void run() {  
  
                display();  
            }  
        }));  
  
    }  
  
    public static void display() {  
        System.out.println("read cost average:" + (TimeCostUtils.getReadLong().get() / READ_NUM) + " ns");  
  
        System.out.println("write cost average:" + (TimeCostUtils.getWriteLong().get() / WRITE_NUM) + " ns");  
    }  
  
    public int getValue() {  
  
        lock.readLock().lock();  
  
        try {  
            return value;  
        } finally {  
  
            lock.readLock().unlock();  
        }  
    }  
  
    public void setValue(int value) {  
        locknew.lock();  
  
        try {  
            this.value = value;  
        } finally {  
            locknew.unlock();  
        }  
  
    }  
  
    public int getValueLock() {  
  
        locknew.lock();  
  
        try {  
            return value;  
        } finally {  
  
            locknew.unlock();  
        }  
    }  
  
    public void setValueLock(int value) {  
        lock.writeLock().lock();  
  
        try {  
            this.value = value;  
        } finally {  
            lock.writeLock().unlock();  
        }  
  
    }  
  
    public synchronized int getValueSyn() {  
        return value;  
    }  
  
    public synchronized void setValueSyn(int value) {  
        this.value = value;  
    }  
}
