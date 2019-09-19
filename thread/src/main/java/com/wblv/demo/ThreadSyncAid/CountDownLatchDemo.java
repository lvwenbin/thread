package com.wblv.demo.ThreadSyncAid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CountDownLatchDemo {  
    final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {  
        CountDownLatch latch=new CountDownLatch(2);//两个工人的协作  
        CyclicBarrier cb = new CyclicBarrier(2, new Runnable() {
            //当所有线程到达barrier时执行
            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("Inside Barrier");
                
            }
        });
        Worker worker1=new Worker("zhang san", 5000, latch,cb);  
        Worker worker2=new Worker("li si", 8000, latch,cb);  
        worker1.start();//  
        worker2.start();//  
       latch.await();//等待所有工人完成工作  
        System.out.println("all work done at "+sdf.format(new Date()));  
    }  
      
      
    static class Worker extends Thread{  
        String workerName;   
        int workTime;  
        CountDownLatch latch;  
        CyclicBarrier cb;
        public Worker(String workerName ,int workTime ,CountDownLatch latch, CyclicBarrier cb){  
             this.workerName=workerName;  
             this.workTime=workTime;  
             this.latch=latch;  
             this.cb=cb;
        }  
        public void run(){  
            System.out.println("Worker "+workerName+" do work begin at "+sdf.format(new Date()));  
            try {
				cb.await();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (BrokenBarrierException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            doWork();//工作了  
            System.out.println("Worker "+workerName+" do work complete at "+sdf.format(new Date()));  
            //latch.countDown();//工人完成工作，计数器减一  
            try {
				cb.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }  
          
        private void doWork(){  
            try {  
                Thread.sleep(workTime);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
      
       
}  
 