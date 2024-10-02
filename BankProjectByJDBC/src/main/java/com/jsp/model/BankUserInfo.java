package com.jsp.model;

import java.sql.Date;

public class BankUserInfo {
	private int userid;
	private String username;
	private String bankemailid;
	private String useremailid;
	private int password;
	private String gender;
	private String address;
	private Date dob;
	private int accountnumber;
	private double amount;
	private String mobilenum;
	private String bankIFSCcode;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBankemailid() {
		return bankemailid;
	}
	public void setBankemailid(String bankemailid) {
		this.bankemailid = bankemailid;
	}
	public String getUseremailid() {
		return useremailid;
	}
	public void setUseremailid(String useremailid) {
		this.useremailid = useremailid;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public int getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(int accountnumber) {
		this.accountnumber = accountnumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getMobilenum() {
		return mobilenum;
	}
	public void setMobilenum(String i) {
		this.mobilenum = i;
	}
	public String getBankIFSCcode() {
		return bankIFSCcode;
	}
	public void setBankIFSCcode(String bankIFSCcode) {
		this.bankIFSCcode = bankIFSCcode;
	}
	public BankUserInfo() {
		super();

	}
	@Override
	public String toString() {
		return "BankUserInfo [userid=" + userid + ", username=" + username + ", bankemailid=" + bankemailid
				+ ", useremailid=" + useremailid + ", password=" + password + ", gender=" + gender + ", address=" + address
				+ ", dob=" + dob + ", accountnumber=" + accountnumber + ", amount=" + amount + ", mobilenum=" + mobilenum
				+ ", bankIFSCcode=" + bankIFSCcode + "]";
	}
	public BankUserInfo(int userid, String username, String bankemailid, String useremailid, int password, String gender,
			String address, Date dob, int accountnumber, double amount, String mobilenum, String bankIFSCcode) {
		super();
		this.userid = userid;
		this.username = username;
		this.bankemailid = bankemailid;
		this.useremailid = useremailid;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.dob = dob;
		this.accountnumber = accountnumber;
		this.amount = amount;
		this.mobilenum = mobilenum;
		this.bankIFSCcode = bankIFSCcode;
	}

}
