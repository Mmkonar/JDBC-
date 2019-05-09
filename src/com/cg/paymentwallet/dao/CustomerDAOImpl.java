package com.cg.paymentwallet.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.cg.paymentwallet.bean.Customer;
import com.cg.paymentwallet.exception.CustomerException;

public class CustomerDAOImpl<Transaction> implements iCustomerDAO {
	private static Map<Long, Customer> customer = new HashMap();
	Map<Long,Transaction> Transaction = new HashMap();

	static {
		customer.put((long) 100000, new Customer(100000, 123, "mmk", 996942166, "Mumbai", 15000));
		customer.put((long) 100001, new Customer(100001, 234, "mari", 996942166, "bangalore", 5000));
		customer.put((long) 100002, new Customer(100002, 456, "neha", 89894733, "Gwalior", 150000));

	}

	@Override
	public Customer createAccount(Customer cust) throws CustomerException {
		customer.put(cust.getAcNO(), cust);
		System.out.println(cust);
		return cust;

	}

	@Override
	public Customer AddMoney(long acc, int pin, int balance) throws CustomerException {
		Customer cust2 = customer.get(acc);
		int currentbalance = cust2.getBalance();
		int totalbalance = currentbalance + balance;
		cust2.setBalance(totalbalance);
		System.out.println(cust2);
		return cust2;
	}

	@Override
	public Customer TransferMoney(long acc, long acc1, int balance) throws CustomerException {
		Customer cus = customer.get(acc);
		Customer cus1 = customer.get(acc1);

		if (cus.getBalance() < balance) {
			System.out.println("Enter amount less than  " + cus.getBalance());
		} else {
			int acc1currentbalance = cus.getBalance() - balance;
			cus.setBalance(acc1currentbalance);

			int acc2currentbalance = cus1.getBalance() + balance;
			cus1.setBalance(acc2currentbalance);

		}
		return cus1;
	}

	@Override
	public ArrayList<Customer> getAccountList() throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getAccount(long acc) throws CustomerException {
		Customer cust = customer.get(acc);
		return cust;
		
	}

	@Override
	public Customer withdraw(long acc, int amount) throws CustomerException {
		Customer cus = customer.get(acc);
		int balance = cus.getBalance();
		int availbalance = balance - amount;
		cus.setBalance(availbalance);
		System.out.println(cus);
		return cus;
	
	}

}
