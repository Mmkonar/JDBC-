package com.cg.paymentwallet.service;

import java.util.ArrayList;
import java.util.List;

import com.cg.paymentwallet.bean.Transaction;
import com.cg.paymentwallet.exception.CustomerException;

public interface iTransaction {
	public Transaction addtransaction(Transaction trans) throws CustomerException;
	public ArrayList<Transaction> gettransaction(double acno ) throws CustomerException;
}
