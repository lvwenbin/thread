﻿package com.wblv.demo.ThreadSyncAid;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {  
    // 放并发锁的map  
    public static Map<String, Lock> currentACCount = new ConcurrentHashMap<String, Lock>();  
    // 放账户金额，本来应该放在数据库的，为了测试就放入map中  
    public static Map<String, Long> accountMoney = new ConcurrentHashMap<String, Long>();  
  
    public static void main(String[] argv) {  
    	long t1=System.currentTimeMillis();
    	System.out.println(t1);
    	new Thread1("100001",100).start();  
        new Thread1("100001",10).start();  
        new Thread1("100002",100).start();  
        new Thread1("100002",-10).start(); 
        new Thread1("100001",200).start();  
        long t2=System.currentTimeMillis();
    	System.out.println("=============="+(t2-t1));
        new Thread1("100001",20).start();  
    	
    }  
  
    /** 
     * 获取锁 
     *  
     * @param key 
     * @return 
     */  
    public static synchronized Lock getKey(String key) {  
        Lock obj = currentACCount.get(key);  
        if (obj == null) {  
            obj = new ReentrantLock();  
            currentACCount.put(key, obj);  
        }  
        System.out.println("获得key:" + key + ":" + obj);  
        return obj;  
    }  
  
    /** 
     * 修改账户金额 
     *  
     * @param account 
     *            账户 
     * @param money 
     *            金额 （单位：分） 
     */  
    public static void updateAccountMoney(String account, Long money) {  
        System.out.println("thread name：" + Thread.currentThread().getName()  
                + " 账号：" + account + ",money=" + money);  
        Lock lock = getKey(account); 
        //当锁已经被其它线程获取到，该线程将会处于等待状态  
        lock.lock();  
        System.out.println("thread name：" + Thread.currentThread().getName()  
                + " 账号：" + account + "获取到锁成功");  
        long oriAmount = accountMoney.get(account)==null?0:accountMoney.get(account);  
        System.out.println("thread name：" + Thread.currentThread().getName()  
                + " 账号：" + account + " 账户原金额：oriAmount=" + oriAmount);  
        long amount = oriAmount + money;  
        System.out.println("thread name：" + Thread.currentThread().getName()  
                + " 账号：" + account + " 更新后金额：amount=" + amount);  
        accountMoney.put(account, amount);  
        System.out.println("thread name：" + Thread.currentThread().getName()  
                + " 账号：" + account + "释放锁");  
        lock.unlock();  
    }  
  
}  


//测试线程  s
class Thread1 extends Thread {  
 private String account;  
 private long money;  

 public Thread1(String account, long money) {  
     super();  
     this.account = account;  
     this.money = money;  
 }  

 public void run() {  
	 LockDemo.updateAccountMoney(account, money);  
 }  

}  