package com.cg.paymentwallet.dao;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import com.cg.paymentwallet.bean.Transaction;
import com.cg.paymentwallet.exception.CustomerException;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

public class TransactionDAOImpl implements iTransactionDAO {
	Map<Long,Transaction> transact = new HashMap();
	

	@Override
	public Transaction addtransaction(Transaction trans) throws CustomerException {
		// TODO Auto-generated method stub
transact.put(trans.getAcno(), trans);
		
		return trans;
	}

	@Override
	public Transaction gettransaction(long acc) throws CustomerException {
		
		// TODO Auto-generated method stub
Transaction t1 = transact.get(acc);
return t1;
}
}