package com.wblv.demo.concurrentCollection.concurrentLinkDeque;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {
	 public static void main(String[] args)
	    {
	        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
	        Thread threads[] = new Thread[100];
	  
	        for (int i = 0; i < threads.length; i++) {
	            AddTask task = new AddTask(list);
	            threads[i] = new Thread(task);
	            threads[i].start();
	        }
	     System.out.println("AddTask has been lauched");
	  
	        for (int i = 0; i < threads.length; i++) {
	            try {
	                threads[i].join();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        System.out.println("the size of the list"+list.size());
	  
	        for (int i = 0; i < threads.length; i++) {
	            RemoveTask task = new RemoveTask(list);
	            threads[i] = new Thread(task);
	            threads[i].start();
	        }
	        System.out.println("RemoveTask has been lauched");
	  
	        for (int i = 0; i < threads.length; i++) {
	            try {
	                threads[i].join();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        System.out.println("the size of the list"+list.size());
	    }
}
