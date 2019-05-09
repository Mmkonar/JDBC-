package com.cg.paymentwallet.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.paymentwallet.exception.CustomerException;

public class CustomerValidator {
public boolean validphone(String phone) {
	if(phone ==null) {
		throw new NullPointerException();
	}
	Pattern pat = Pattern.compile("^[0-9]\\d{9}$");
	Matcher mat = pat.matcher(phone);
	if(mat.matches()) 
		return true ;
	else
			return false;
	}

public boolean validname(String name) {
	if(name ==null) {
		throw new NullPointerException();
	}
	Pattern pat= Pattern.compile("^[A-z][a-z0-9_-]{3,19}$");
	
	Matcher mat= pat.matcher(name);
	if(mat.matches())
		return true;
	else
		return false;
}
public boolean validaccount(String acc) {
	if(acc ==null) {
		throw new NullPointerException();
	}
	Pattern pat = Pattern.compile("^[0-9]\\d{6,}$");
	Matcher mat = pat.matcher(acc);
	if(mat.matches()) 
		return true ;
	else
			return false;
}
public boolean validamount(String acc) {
	if(acc ==null) {
		throw new NullPointerException();
	}
	boolean flag = Pattern.matches("[0-9]{3,10}$", acc);
	if(flag) {
		return true;
	}else
		return true;
}
}
