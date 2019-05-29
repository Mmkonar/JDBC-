package com.cg.paymentwallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import com.cg.paymentwallet.bean.Customer;
import com.cg.paymentwallet.bean.Transaction;
import com.cg.paymentwallet.exception.CustomerException;
import com.cg.paymentwallet.util.DButil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

public class TransactionDAOImpl implements iTransactionDAO {
	Connection conn = null;

	@Override
	public Transaction addtransaction(Transaction trans) throws CustomerException {
		// TODO Auto-generated method stub
		conn = DButil.getConnection();

		try {
			PreparedStatement pst = conn.prepareStatement("INSERT INTO transaction VALUES(?,?,?,?)");
			pst.setDouble(1,trans.getTransactionID() );
			pst.setString(2, trans.getType());
			pst.setLong(3, trans.getAcno());
			pst.setInt(4, trans.getAmount());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new CustomerException("Problem in Transaction details" + e.getMessage());
		}
		return trans;
	}

	@Override
	public ArrayList<Transaction> gettransaction(double acno) throws CustomerException {
		// TODO Auto-generated method stub
		
		ArrayList<Transaction> arr = new ArrayList<>();
		conn = DButil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select transactionid,type,acno,amount from transaction where acno=?");
			pst.setDouble(1, acno);
			ResultSet rst = pst.executeQuery();
			
//			Statement st = conn.createStatement();
//			ResultSet rst = st.executeQuery("select transactionid,type,acno,amount from transaction where ");
			while (rst.next()) {
					Transaction trans = new Transaction();
					trans.setAcno(rst.getLong("acno"));
					trans.setAmount(rst.getInt("amount"));
					trans.setTransactionID(rst.getInt("transactionid"));
					trans.setType(rst.getString("type"));
					arr.add(trans);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return arr;
			


	}


}