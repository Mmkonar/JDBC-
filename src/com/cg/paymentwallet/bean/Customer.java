package com.cg.paymentwallet.bean;

public class Customer {
private long AcNO;
private int pin;
private String name;
public Customer(long AcNO, int pin, String name, long phone_number, String address, int balance) {
	this.AcNO= AcNO;
	this.pin = pin;
	this.name = name;
	this.phone_number = phone_number;
	this.address = address;
	this.balance = balance;
}
private long phone_number;
private String address;
private int balance=500;
public long getAcNO() {
	return AcNO;
}
public void setAcNO(long acNO) {
	AcNO = acNO;
}
public int getPin() {
	return pin;
}

public void setPin(int pin) {
	this.pin = pin;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public long getPhone_number() {
	return phone_number;
}
public void setPhone_number(long phone_number) {
	this.phone_number = phone_number;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public int getBalance() {
	return balance;
}
public void setBalance(int balance) {
	this.balance = balance;
}
@Override
public String toString() {
	return "Customer [AcNO=" + AcNO + ", pin=" + pin + ", name=" + name + ", phone_number=" + phone_number
			+ ", address=" + address + ", balance=" + balance + "]";
}
public Customer() {
	
}

}
