package com.cg.paymentwallet.bean;

public class Transaction {
private double transactionID;
private String type;
private long acno;
private int amount;
public double getTransactionID() {
	return transactionID;
}
public void setTransactionID(double d) {
	this.transactionID = d;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public long getAcno() {
	return acno;
}
public void setAcno(long acno2) {
	this.acno = acno2;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
@Override
public String toString() {
	return "Transaction [transactionID=" + transactionID + ", type=" + type + ", acno=" + acno + ", amount=" + amount
			+ "]";
}


}
