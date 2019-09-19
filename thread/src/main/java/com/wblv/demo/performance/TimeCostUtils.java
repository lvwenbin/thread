package com.wblv.demo.performance;

import java.util.concurrent.atomic.AtomicLong;

public class TimeCostUtils {
	private static AtomicLong        readLong  = new AtomicLong();  
	  
    private static AtomicLong        writeLong = new AtomicLong();  
  
    public final static String       WRITE     = "write";  
  
    public final static String       READ      = "read";  
  
    static ThreadLocal<TimesRecords> recordMap = new ThreadLocal<TimesRecords>();  
  
    public static void start(String prefix) {  
  
        TimesRecords timesRecords = new TimesRecords(prefix, System.nanoTime());  
        recordMap.set(timesRecords);  
    }  
  
    public static void end() {  
        TimesRecords timesRecords = recordMap.get();  
        long cost = System.nanoTime() - timesRecords.getCost();  
  
        // 计算每次的开销时间  
        if (timesRecords.getName().equals(WRITE)) {  
            writeLong.addAndGet(cost);  
        } else {  
            readLong.addAndGet(cost);  
        }  
    }  
  
    public static AtomicLong getReadLong() {  
        return readLong;  
    }  
  
    public static AtomicLong getWriteLong() {  
        return writeLong;  
    }  
  
    static class TimesRecords {  
  
        private String name;  
  
        private long   cost;  
  
        public TimesRecords(String name, long cost) {  
            this.name = name;  
            this.cost = cost;  
        }  
  
        public String getName() {  
            return name;  
        }  
  
        public void setName(String name) {  
            this.name = name;  
        }  
  
        public long getCost() {  
            return cost;  
        }  
  
        public void setCost(long cost) {  
            this.cost = cost;  
        }  
  
    }  
}
