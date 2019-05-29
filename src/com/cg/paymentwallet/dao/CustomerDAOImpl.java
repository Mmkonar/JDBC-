package com.cg.paymentwallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.paymentwallet.bean.Customer;
import com.cg.paymentwallet.exception.CustomerException;
import com.cg.paymentwallet.util.DButil;

public class CustomerDAOImpl implements iCustomerDAO {

	Connection conn = null;

	public CustomerDAOImpl() {

	}

	@Override
	public Customer createAccount(Customer cust) throws CustomerException {
		// customer.put(cust.getAcNO(), cust);
		// System.out.println(cust);
		// return cust;
		conn = DButil.getConnection();
		System.out.println(cust);
		try {
			PreparedStatement pst = conn.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?)");
			pst.setLong(1, cust.getAcNO());
			pst.setString(5, cust.getAddress());
			pst.setInt(6, cust.getBalance());

			pst.setString(3, cust.getName());
			pst.setLong(4, cust.getPhone_number());
			// pst.setString(5, cust.getAddress());
			pst.setInt(2, cust.getPin());
			// pst.setInt(6, cust.getBalance());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new CustomerException("Problem in inserting customer details" + e.getMessage());
		}

		return cust;

	}

	@Override
	public Customer AddMoney(long acc, int pin, int balance) throws CustomerException {
		String sql = "select * from customer where accno=?";
		Customer cust = null;
		conn = DButil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setLong(1, acc);
			ResultSet rst1 = pst.executeQuery();
			if (rst1.next()) {
				cust = new Customer();
				cust.setAcNO(rst1.getLong("accno"));
				cust.setAddress(rst1.getString("address"));
				cust.setBalance(rst1.getInt("balance"));
				cust.setName(rst1.getString("name"));
				cust.setPhone_number(rst1.getLong("phonenumber"));
				cust.setPin(rst1.getInt("pin"));

				PreparedStatement ps1 = conn.prepareStatement("update customer set balance=? where accno=?");
				int afterbalance = cust.getBalance() + balance;
				System.out.println(afterbalance);
				ps1.setLong(2, acc);
				ps1.setInt(1, afterbalance);
				int count1 = ps1.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return cust;
	}

	@Override
	public Customer TransferMoney(long acc, long acc1, int balance) throws CustomerException {
		String sql = "SELECT * from customer WHERE accno=?";
		Customer customer = null;
		Customer cust1 = null;
		conn = DButil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setLong(1, acc);
			ResultSet rst = pst.executeQuery();
			PreparedStatement pst1 = conn.prepareStatement(sql);
			pst1.setLong(1, acc1);
			ResultSet rst1 = pst1.executeQuery();
			if (rst.next()) {
				cust1 = new Customer();
				cust1.setBalance(rst.getInt("balance"));
				PreparedStatement ps1 = conn.prepareStatement("update customer set balance=? where accno=?");
				int afterbalance = cust1.getBalance() - balance;

				ps1.setLong(2, acc);
				ps1.setInt(1, afterbalance);
				int count1 = ps1.executeUpdate();
				if (rst1.next()) {
					customer = new Customer();
					customer.setBalance(rst1.getInt("balance"));
					PreparedStatement ps2 = conn.prepareStatement("update customer set balance=? where accno=?");
					int afterbalance1 = customer.getBalance() + balance;
					System.out.println(afterbalance1);
					ps2.setLong(2, acc1);
					ps2.setInt(1, afterbalance1);
					int count2 = ps2.executeUpdate();
					System.out.println(count2);
					//
					// PreparedStatement ps1 = conn.prepareStatement("update customer set balance=?
					// where accno=?");
					// ps1.setLong(2, acc1);
					// ps1.setInt(1, afterbalance);
					// int count1 = ps.executeUpdate();
					// if (count1 == 0)
					// throw new CustomerException("Customer not found");
				}
			}

		} catch (SQLException e) {

			throw new CustomerException(e.getMessage());
		}
		return customer;
	}

	@Override
	public ArrayList<Customer> getAccountList() throws CustomerException {
		ArrayList<Customer> cust = new ArrayList<Customer>();
		conn = DButil.getConnection();
		try {
			Statement st = conn.createStatement();
			ResultSet rst = st.executeQuery("select accno,name,address,balance from customers");
			while (rst.next()) {
				Customer cust1 = new Customer();
				cust1.setAcNO(rst.getLong("accno"));
				cust1.setName(rst.getString("name"));
				cust1.setAddress(rst.getString("address"));
				cust1.setBalance(rst.getInt("balance"));
				cust.add(cust1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cust;
	}

	@Override
	public Customer getAccount(long acc) throws CustomerException {
		String sql = "SELECT * from customer WHERE accno=?";
		Customer cust1 = null;
		conn = DButil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setLong(1, acc);
			ResultSet rst = pst.executeQuery();
			if (rst.next()) {
				cust1 = new Customer();
				cust1.setAcNO(rst.getLong("accno"));
				cust1.setName(rst.getString("name"));
				cust1.setAddress(rst.getString("address"));
				cust1.setPin(rst.getShort("pin"));
				cust1.setBalance(rst.getInt("balance"));
			}
		} catch (SQLException e) {

			throw new CustomerException(e.getMessage());
		}
		return cust1;
	}

	@Override
	public Customer withdraw(long acc, int amount) throws CustomerException {
		String sql = "SELECT * from customer WHERE accno=?";
		Customer customer = null;
		conn = DButil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setLong(1, acc);
			ResultSet rst = pst.executeQuery();
			if (rst.next()) {
				customer = new Customer();
				customer.setBalance(rst.getInt("balance"));
				if (customer.getBalance() > amount) {
					int currentbalance = customer.getBalance() - amount;
					PreparedStatement ps = conn.prepareStatement("update customer set balance=? where accno=?");
					ps.setLong(2, acc);
					ps.setInt(1, currentbalance);
					int count = ps.executeUpdate();
					if (count == 0)
						throw new CustomerException("Customer not found");

				} else
					throw new CustomerException("Enter amount  less than  " + customer.getBalance());
			}
		} catch (SQLException e) {

			throw new CustomerException(e.getMessage());
		}
		return customer;
	}

}
