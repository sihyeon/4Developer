package com.project4D.fdpay.model;

public class User {
	String ID;
	char[] PW;
	String email;
	String phone;

	public User() {
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public char[] getPW() {
		return PW;
	}

	public void setPW(char[] pW) {
		PW = pW;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
