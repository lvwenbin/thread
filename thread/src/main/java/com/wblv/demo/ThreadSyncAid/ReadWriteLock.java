package com.wblv.demo.ThreadSyncAid;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
private static final ReentrantReadWriteLock lock=new ReentrantReadWriteLock(true);


public static void main(String args[])
{ 	long t1=System.currentTimeMillis();
System.out.println(t1);
ReadWriteLock test=new ReadWriteLock();
TestThread2[] tr=new TestThread2[10000];
for (int i = 0; i < 10000; i++) {
	tr[i]=new TestThread2(test);
}
System.out.println("thread autowired successfully");

for (int i = 0; i < 10000; i++) {
	if(i==9990)
	{
		long t2=System.currentTimeMillis();
		System.out.println(t2);
		System.out.println("system cost time:=================="+(t2-t1));
	}
	tr[i].start();
	
}
tr=null;


}
public void test()
{
	lock.writeLock().lock();
	System.out.println(lock);
	for (int i = 0; i < 10; i++) {
		System.out.println(Thread.currentThread().getName()+i);
	}
	
	
	lock.writeLock().unlock();
}




}
class TestThread2 extends Thread
{  
	private ReadWriteLock test3;
	public TestThread2(ReadWriteLock test)
	{
		test3=test;
	}
	public void run()
	{
		test3.test();
		
	}
}