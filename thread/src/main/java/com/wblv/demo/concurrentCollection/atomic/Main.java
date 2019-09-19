package com.wblv.demo.concurrentCollection.atomic;

public class Main {

	public static void main(String[] args) {

		//17. ����һ�� Account ���������������Ϊ 1000��
		Account account=new Account();
		account.setBalance(1000);

		Company company=new Company(account);
		Thread companyThread=new Thread(company);
		Bank bank=new Bank(account);
		Thread bankThread=new Thread(bank);
		System.out.print(account. getBalance());
		
		companyThread.start();
		bankThread.start();
		
		try {
			companyThread.join();
			bankThread.join();
			System.out.print(account. getBalance());
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}

}
