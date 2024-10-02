package com.jsp.DAO;

import com.jsp.model.BankUserInfo;

public interface AdminDAO {
	public boolean login(String emailid,int password);
    void registration(BankUserInfo bankUserInfo);
    void displayAccountDetails(String email,int password );
	void changeMobileNumber(int accnum,String oldmobilenum,String newmobilenum);
	void deleteAccountDetails(long accnum);
}
