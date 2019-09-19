package com.wblv.demo.concurrentCollection.linkedBlockingDeque;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
        LinkedBlockingDeque<String> list = new LinkedBlockingDeque<String>(3);
        Client client = new Client(list);
        Thread thread = new Thread(client);
        thread.start();
        for(int i =0;i<5;i++){
            for(int j =0;j<3;j++){
                try {
                    String request = list.take();
                    System.out.printf("Main: request: %s at %s. Size: %d\n",request,new Date(),list.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                

                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Main : End of the program.");
    }
}
