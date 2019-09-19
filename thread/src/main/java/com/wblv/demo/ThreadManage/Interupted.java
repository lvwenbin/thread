package com.wblv.demo.ThreadManage;

public class Interupted {

	public static void main(String[] args) {
		for (int i = 0; i <1; i++) {
		Thread thread= new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				if(Thread.currentThread().isInterrupted()==true)
				{
				System.out.println(">>>>>>>>>>>"+Thread.currentThread().getName());
				}
			}
				
			});
		
		thread.start();
		thread.interrupt();
		}

	}

}
