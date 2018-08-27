package com.noahfields.Models;

import java.sql.Date;
import java.text.DecimalFormat;

public class Customer {

	private int id;
	private String last_name;
	private String first_name;
	private String username;
	private String e_mail;
	private String password;
	//ignore Salt - only get these when using DAO
	private String address_line_1;
	private String address_line_2;
	private int zipcode;
	private long phone;
	private String member_status;
	private Date join_date;
	private double balance;
	
	public Customer() {
		super();
	}
	

	public Customer(int id, String last_name, String first_name, String username, String e_mail, String address_line_1,
			String address_line_2, int zipcode, int phone, String member_status, Date join_date, int balance) {
		super();
		this.id = id;
		this.last_name = last_name;
		this.first_name = first_name;
		this.username = username;
		this.e_mail = e_mail;
		this.address_line_1 = address_line_1;
		this.address_line_2 = address_line_2;
		this.zipcode = zipcode;
		this.phone = phone;
		this.member_status = member_status;
		this.join_date = join_date;
		this.balance = balance;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the e_mail
	 */
	public String getE_mail() {
		return e_mail;
	}

	/**
	 * @param e_mail the e_mail to set
	 */
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	/**
	 * @return the address_line_1
	 */
	public String getAddress_line_1() {
		return address_line_1;
	}

	/**
	 * @param address_line_1 the address_line_1 to set
	 */
	public void setAddress_line_1(String address_line_1) {
		this.address_line_1 = address_line_1;
	}

	/**
	 * @return the address_line_2
	 */
	public String getAddress_line_2() {
		return address_line_2;
	}

	/**
	 * @param address_line_2 the address_line_2 to set
	 */
	public void setAddress_line_2(String address_line_2) {
		this.address_line_2 = address_line_2;
	}

	/**
	 * @return the zipcode
	 */
	public int getZipcode() {
		return zipcode;
	}
	
	public String getZipcodeText() {
		DecimalFormat format = new DecimalFormat("00000");
		return format.format(zipcode);
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the phone
	 */
	public long getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the member_status
	 */
	public String getMember_status() {
		return member_status;
	}

	/**
	 * @param member_status the member_status to set
	 */
	public void setMember_status(String member_status) {
		this.member_status = member_status;
	}

	/**
	 * @return the join_date
	 */
	public Date getJoin_date() {
		return join_date;
	}

	/**
	 * @param join_date the join_date to set
	 */
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}
	
	public String getBalanceText() {
		return String.format("$%.2f", balance);
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (address_line_1 == null) {
			if (other.address_line_1 != null)
				return false;
		} else if (!address_line_1.equals(other.address_line_1))
			return false;
		if (address_line_2 == null) {
			if (other.address_line_2 != null)
				return false;
		} else if (!address_line_2.equals(other.address_line_2))
			return false;
		if (balance != other.balance)
			return false;
		if (e_mail == null) {
			if (other.e_mail != null)
				return false;
		} else if (!e_mail.equals(other.e_mail))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (id != other.id)
			return false;
		if (join_date == null) {
			if (other.join_date != null)
				return false;
		} else if (!join_date.equals(other.join_date))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (member_status == null) {
			if (other.member_status != null)
				return false;
		} else if (!member_status.equals(other.member_status))
			return false;
		if (phone != other.phone)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (zipcode != other.zipcode)
			return false;
		return true;
	}


	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", last_name=" + last_name + ", first_name=" + first_name + ", username="
				+ username + ", e_mail=" + e_mail + ", address_line_1=" + address_line_1 + ", address_line_2="
				+ address_line_2 + ", zipcode=" + zipcode + ", phone=" + phone + ", member_status=" + member_status
				+ ", join_date=" + join_date + ", balance=" + balance + "]";
	}
	
	
	
	
	
}
