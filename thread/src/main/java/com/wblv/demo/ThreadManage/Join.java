package com.wblv.demo.ThreadManage;

public class Join {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Thread thread=new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(">>>>>>>>>>>>"+Thread.currentThread().getName());
					
				}
			});
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
