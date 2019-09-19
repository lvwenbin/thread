package com.wblv.demo.sync;

//public class SynTest {
//	public static void main(String[] argv) {  
//        new TestThread("12345678asdf").start();  
//        new TestThread("12345678asdf").start();  
//    }  
//public void loop(String random) {  
//    System.out.println("thread name：" + Thread.currentThread().getName()  
//            + " 传入的random:" + random);  
//    synchronized (random) {  
//        System.out.println("thread name："  
//                + Thread.currentThread().getName() + " 开始执行循环");  
//        for (int i = 0; i < 10; i++) {  
//            System.out.println("thread name："  
//                    + Thread.currentThread().getName() + " i=" + i);  
//        }  
//        System.out.println("thread name："  
//                + Thread.currentThread().getName() + " 执行循环结束");  
//
//    }  
//
//}  
//}
//class TestThread extends Thread {  
//    private String name;  
//  
//    public TestThread(String name) {  
//        super();  
//        this.name = name;  
//    }  
//  
//    public void run() {  
//        SynTest syntest = new SynTest();  
//        syntest.loop(name);  
//    }  
//  
//}  
//synchronized修饰方法是作用在类的实例上，如果代码块也是修饰到gai实例的话，他们就会达到互斥的效果
public class SynTest {  
    public static void main(String[] argv) {  
        SynTest syn1=new SynTest();  
        new TestThread(syn1).start();  
        new TestThread1(syn1).start();  
    }  
    //循环方法  
    public void loop() {  
        System.out.println("thread name：" + Thread.currentThread().getName());  
        synchronized (this) {  
            System.out.println("thread name："  
                    + Thread.currentThread().getName() + " 开始执行循环");  
            for (int i = 0; i < 10; i++) {  
                System.out.println("thread name："  
                        + Thread.currentThread().getName() + " i=" + i);  
            }  
            System.out.println("thread name："  
                    + Thread.currentThread().getName() + " 执行循环结束");  
  
        }  
  
    }  
    //循环方法1  
    public synchronized void loop1() {  
        System.out.println("thread name：" + Thread.currentThread().getName());  
  
        System.out.println("thread name：" + Thread.currentThread().getName()  
                + " 开始执行循环");  
        for (int i = 0; i < 10; i++) {  
            System.out.println("thread name："  
                    + Thread.currentThread().getName() + " i=" + i);  
        }  
        System.out.println("thread name：" + Thread.currentThread().getName()  
                + " 执行循环结束");  
  
    }  
  
}  
//测试线程  
class TestThread extends Thread {  
    private SynTest syn;  
  
    public TestThread(SynTest syn) {  
        super();  
        this.syn = syn;  
    }  
  
    public void run() {  
        syn.loop();  
    }  
  
}  
//测试线程1  
class TestThread1 extends Thread {  
    private SynTest syn;  
  
    public TestThread1(SynTest syn) {  
        super();  
        this.syn = syn;  
    }  
  
    public void run() {  
      
        syn.loop1();  
    }  
  
}  
