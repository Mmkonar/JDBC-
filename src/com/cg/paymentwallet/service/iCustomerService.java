package com.cg.paymentwallet.service;

import java.util.ArrayList;


import com.cg.paymentwallet.bean.Customer;
import com.cg.paymentwallet.exception.CustomerException;

public interface iCustomerService {
public Customer createAccount(Customer cust) throws CustomerException;
public ArrayList<Customer> getAccountList() throws CustomerException;
public Customer getAccount(long acc) throws CustomerException;
public Customer withdraw(long acc,int amount) throws CustomerException;
public Customer AddMoney(long acc,int pin,int balance) throws CustomerException;
public Customer TransferMoney(long acc,long acc1,int balance) throws CustomerException;
public Customer ShowBalance(long acc,int pin) throws CustomerException;
}
