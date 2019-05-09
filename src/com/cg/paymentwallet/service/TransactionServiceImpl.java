package com.cg.paymentwallet.service;

import java.util.List;

import com.cg.paymentwallet.bean.Transaction;

import com.cg.paymentwallet.dao.TransactionDAOImpl;

import com.cg.paymentwallet.dao.iTransactionDAO;
import com.cg.paymentwallet.exception.CustomerException;

public class TransactionServiceImpl implements iTransaction {
	iTransactionDAO dao;

	public TransactionServiceImpl() {
		dao = new TransactionDAOImpl();
	}

	@Override
	public Transaction addtransaction(Transaction trans) throws CustomerException {
		// TODO Auto-generated method stub
		return dao.addtransaction(trans);
	}

	@Override
	public Transaction gettransaction(long acc) throws CustomerException {
		return dao.gettransaction(acc);
	}

}
