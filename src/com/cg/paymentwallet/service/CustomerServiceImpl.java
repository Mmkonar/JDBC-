package com.cg.paymentwallet.service;

import java.util.ArrayList;

import com.cg.paymentwallet.bean.Customer;
import com.cg.paymentwallet.dao.CustomerDAOImpl;
import com.cg.paymentwallet.dao.iCustomerDAO;
import com.cg.paymentwallet.exception.CustomerException;

public class CustomerServiceImpl implements iCustomerService {
	iCustomerDAO dao;

	public CustomerServiceImpl() {
		dao = new CustomerDAOImpl();
	}

	@Override
	public Customer createAccount(Customer cust) throws CustomerException {
		return dao.createAccount(cust);
	}

	@Override
	public Customer AddMoney(long acc, int pin, int balance) throws CustomerException {
	return dao.AddMoney(acc, pin, balance);
	}

	@Override
	public Customer TransferMoney(long acc, long acc1, int balance) throws CustomerException {
return dao.TransferMoney(acc, acc1, balance);
	}

	@Override
	public Customer ShowBalance(long acc, int pin) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Customer> getAccountList() throws CustomerException {
		return dao.getAccountList();
	}

	@Override
	public Customer getAccount(long acc) throws CustomerException {
	return dao.getAccount(acc);
	}



	@Override
	public Customer withdraw(long acc, int amount) throws CustomerException {
	return dao.withdraw(acc, amount);
	}



}
