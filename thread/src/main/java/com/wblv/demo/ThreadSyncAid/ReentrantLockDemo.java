package com.wblv.demo.ThreadSyncAid;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockDemo {  
    private ReentrantLock lock = new ReentrantLock();//乐观锁  
    public static void main(String[] argv) {  
    	ReentrantLockDemo syn1 = new ReentrantLockDemo();  
        new TestThread(syn1).start();  
        new TestThread(syn1).start();  
    }  
  
    // 循环方法  
    public void loop() {  
        System.out.println("thread name：" + Thread.currentThread().getName());  
        lock.lock(); // 加锁  
        System.out.println("thread name：" + Thread.currentThread().getName()  
                + " 开始执行循环");  
        for (int i = 0; i < 10; i++) {  
            System.out.println("thread name："  
                    + Thread.currentThread().getName() + " i=" + i);  
        }  
        System.out.println("thread name：" + Thread.currentThread().getName()  
                + " 执行循环结束");  
  
        lock.unlock();//执行完成释放锁  
  
    }  
}  
// 测试线程  
class TestThread extends Thread {  
    private ReentrantLockDemo syn;  
  
    public TestThread(ReentrantLockDemo syn) {  
        super();  
        this.syn = syn;  
    }  
  
    public void run() {  
        syn.loop();  
    }  
}  
