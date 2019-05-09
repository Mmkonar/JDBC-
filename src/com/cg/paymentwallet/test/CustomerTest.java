package com.cg.paymentwallet.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.cg.paymentwallet.service.CustomerValidator;

public class CustomerTest {
	CustomerValidator validate = new CustomerValidator();

	@Test
	public void testmethod1() {
		String s = "mari";
		boolean actual = validate.validname(s);
		boolean expected = true;
		assertEquals(expected, actual);
	}
	@Test
	public void testmethod2() {
		String phone = "1234567890";
		boolean actual = validate.validphone(phone);
		boolean expected = true;
		assertEquals(expected,actual);
	}
	@Test
	public void testmethod3() {
		String acc = "1234560";
		boolean actual = validate.validaccount(acc);
		boolean expected = true;
		assertEquals(expected,actual);
	}
}
