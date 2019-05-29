package com.cg.paymentwallet.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.paymentwallet.bean.Customer;
import com.cg.paymentwallet.bean.Transaction;
import com.cg.paymentwallet.exception.CustomerException;
import com.cg.paymentwallet.service.CustomerServiceImpl;
import com.cg.paymentwallet.service.CustomerValidator;
import com.cg.paymentwallet.service.TransactionServiceImpl;

public class Main {

	public static void main(String[] args) throws CustomerException {

		// TODO Auto-generated method stub
		long acno;
		int pin;
		int option;
		int choice;
		Scanner sc = new Scanner(System.in);
		Customer cust = new Customer();
		Transaction trans = new Transaction();
		TransactionServiceImpl service1 = new TransactionServiceImpl();
		CustomerValidator validate = new CustomerValidator();
		CustomerServiceImpl service = new CustomerServiceImpl();
		do {
			System.out.println(
					"Enter the option:->\n1.Create Account\n2.Add Money \n3.Withdraw money \n4.Transfer money \n5.Print Transactions \n6.Show balance\n7.exit");
			option = sc.nextInt();
			switch (option) {

			case 1:
				try {
					System.out.println("Enter your name");
					String name = sc.next();
					if(validate.validname(name)==false)
						throw new CustomerException("Enter a valid name");
					System.out.println("Enter your account number");
					acno = sc.nextLong();
					System.out.println("Enter your phone number");
					long phone = sc.nextLong();
					if(validate.validphone(Long.toString(phone))==false)
						throw new CustomerException("Enter valid phone number");
					System.out.println("Enter your pin");
					pin = sc.nextInt();
					Scanner sc1 = new Scanner(System.in);
					System.out.println("Enter your address");
					String address = sc1.nextLine();
					cust.setName(name);
					cust.setAddress(address);
					cust.setAcNO(acno);
					cust.setPhone_number(phone);
					cust.setPin(pin);

					service.createAccount(cust);
				} catch (CustomerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Account Successfully created");
				break;
			case 2:
				try {
					System.out.println("Enter the account number ");
					acno = sc.nextLong();
					System.out.println("Enter your pin");
					pin = sc.nextInt();
					Customer cust1 = service.getAccount(acno);
					System.out.println(cust1);
					if(cust1!=null) {
					if (cust1.getPin() == pin) {
						System.out.println("Enter the amount you want to add");
						int amount = sc.nextInt();
						service.AddMoney(acno, pin, amount);
						trans.setAcno(acno);
						trans.setAmount(amount);
						trans.setTransactionID((Math.random()*100));
						trans.setType("Add money");
						service1.addtransaction(trans);
						System.out.println("Amount Added successfully");
					} else {
						System.out.println("Enter valid pin");
					}}
					else {
						System.out.println("Enter valid account number ");
					}
				} catch (CustomerException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 3:

				System.out.println("Enter your account number ");
				acno = sc.nextLong();
				System.out.println("Enter your pin");
				pin = sc.nextInt();

				Customer cus = service.getAccount(acno);
				if(cus!=null) {
				if (cus.getPin() == pin) {
					System.out.println("Enter amount you want to withdraw");
					int withdraw = sc.nextInt();
					if(cus.getBalance()<withdraw) {
						System.out.println("Enter amount less than "+cus.getBalance());
					}else {
					try {
						service.withdraw(acno, withdraw);
						trans.setAcno(acno);
						trans.setAmount(withdraw);
						trans.setTransactionID((Math.random()*100));
						trans.setType("Withdraw");
						service1.addtransaction(trans);
					} catch (CustomerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}}
				}else {
					System.out.println("Enter valid account number ");
				}
				System.out.println("Amount widthdrawed successfully");
				break;
			case 4:
				System.out.println("Enter your account number ");
				acno = sc.nextLong();
				System.out.println("Enter your pin");
				pin = sc.nextInt();
				Customer cusacc1 = service.getAccount(acno);
				if(cusacc1 != null) {
				if(cusacc1.getPin()==pin) {
					System.out.println("Enter the account number you want to transfer");
					long accno = sc.nextLong();
					System.out.println("Enter the amount to be transferred");
					int amount = sc.nextInt();
					if(cusacc1.getBalance()<amount) {
						System.out.println("Enter amount less than "+cusacc1.getBalance());
					}else {
					service.TransferMoney(acno, accno, amount);
					trans.setAcno(acno);
					trans.setAmount(amount);
					trans.setTransactionID(((int) (Math.random() * 100000)) % 1000);
					trans.setType("transfer");
					service1.addtransaction(trans);
					System.out.println("Amount transferred successfully");
					}
				}
				}
				else {
					System.out.println("Enter valid account number ");
				}
				
				
				break;
			case 5:
				System.out.println("Enter your account number ");
				acno = sc.nextLong();
				System.out.println("Enter your pin");
				pin = sc.nextInt();
				ArrayList<Transaction> trans1 = service1.gettransaction(acno);
				if(trans1==null) {
					System.out.println("No transactions ");
				}else {
					for(Transaction t:trans1) {
						System.out.println(t);
					}
				}
				
			
				break;
			case 6:
				try {
					System.out.println("Enter your account number ");
					acno = sc.nextLong();
					System.out.println("Enter your pin");
					pin = sc.nextInt();

					Customer cust3 = service.getAccount(acno);
					if(cust3 != null) {
					if (cust3.getPin() == pin) {
						System.out.println("Current Balance " + cust3.getBalance());
					}
					}else {
						System.out.println("Enter valid account ");
					}
				} catch (CustomerException e) {
				System.out.println(e.getMessage());
				}

				break;
			case 7:
				System.exit(0);

			}

		} while (option != 7);

	}

}
