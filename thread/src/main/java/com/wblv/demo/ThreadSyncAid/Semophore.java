package com.wblv.demo.ThreadSyncAid;

import java.util.concurrent.Semaphore;

public class Semophore {
   
	
	
	private static final Semaphore semaphore=new Semaphore(1,true);
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						System.out.println("before >>>>>>>>>>>>"+Thread.currentThread().getName());
						semaphore.acquire();
						Thread.sleep(300);
						System.out.println("after >>>>>>>>>>>>"+Thread.currentThread().getName());
						semaphore.release();
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
					
					
				}
			}).start();
		}

	}

}
