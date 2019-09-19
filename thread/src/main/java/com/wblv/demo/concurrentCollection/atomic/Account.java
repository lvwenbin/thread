package com.wblv.demo.concurrentCollection.atomic;

import java.util.concurrent.atomic.AtomicLong;

public class Account {
	//2. ����һ��˽�� AtomicLong ���ԣ���Ϊ balance�����������˺ŵ���
	private AtomicLong balance;

	//3. ʵ����Ĺ��캯������ʼ����������ֵ��
	public Account(){
	balance=new AtomicLong();
	}

	//4. ʵ��һ����������Ϊ getBalance()�����������������ֵ��
	public long getBalance() {
	return balance.get();
	}

	//5. ʵ��һ����������Ϊ setBalance()�����������������ֵ��
	public void setBalance(long balance) {
	this.balance.set(balance);
	}

	//6. ʵ��һ����������Ϊ addAmount()���������������ֵ��
	public void addAmount(long amount) {
	this.balance.getAndAdd(amount);
	}

	//7. ʵ��һ����������Ϊ substractAmount() �������������ֵ��
	public void subtractAmount(long amount) {
	this.balance.getAndAdd(-amount);
	}
}
