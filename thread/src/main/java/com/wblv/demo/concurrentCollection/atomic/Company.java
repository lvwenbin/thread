package com.wblv.demo.concurrentCollection.atomic;

public class Company implements Runnable {

	//9. ����һ��˽�� Account ���ԣ���Ϊ account��
	private Account account;

	//10. ʵ����Ĺ��캯������ʼ����������ֵ��
	public Company(Account account) {
	this.account=account;
	}

	//11. ʵ������� run() ������ ʹ�� account �� addAmount()�����������������10�εĵ�����������Ϊ1000��
	@Override
	public void run() {
	for (int i=0; i<10; i++){
	account.addAmount(1000);
	}
	}
}