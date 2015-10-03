package com.project4D.fdpay.model;

/**
 * user generator (builder pattern) for chaining
 */
public class UserBuilder {
	private final User u;

	public UserBuilder() {
		this.u = new User();
	}

	public UserBuilder setID(String id) {
		u.setID(id);
		return this;
	}

	public UserBuilder setPW(char[] pw) {
		u.setPW(pw);
		return this;
	}

	public UserBuilder setEmail(String email) {
		u.setEmail(email);
		return this;
	}

	public UserBuilder setPhone(String phone) {
		u.setPhone(phone);
		return this;
	}
	
	public User build() {
		return u;
	}
}
