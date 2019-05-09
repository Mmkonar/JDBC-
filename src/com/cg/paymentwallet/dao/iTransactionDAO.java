package com.cg.paymentwallet.dao;

import java.util.List;

import com.cg.paymentwallet.bean.Transaction;
import com.cg.paymentwallet.exception.CustomerException;

public interface iTransactionDAO {
	public Transaction addtransaction(Transaction trans) throws CustomerException;
	public Transaction gettransaction(long acc ) throws CustomerException;
}
