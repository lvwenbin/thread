package com.wblv.demo.concurrentCollection.atomic;

public class Bank implements Runnable {

	//13. ����һ��˽�� Account ���ԣ���Ϊ account��
	private Account account;

	//14. ʵ����Ĺ��캯������ʼ����������ֵ��
	public Bank(Account account) {
	this.account=account;
	}

	//15. ʵ������� run() ������ʹ�� account �� subtractAmount() �����������������10�εĵݼ����ݼ���Ϊ1000��
	@Override
	public void run() {
	for (int i=0; i<10; i++){
	account.subtractAmount(1000);
	}
	}
}
