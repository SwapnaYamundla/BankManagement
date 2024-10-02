package com.jsp.DAO;

import com.jsp.model.BankUserInfo;

public interface UserDAO {
BankUserInfo userlogin(String email,int password);
void credit(int accountnumber,double amount);
void debit(int accountnum,double amount);
void print(int accountnum);
void changePassword(int accountnumber,int oldpass,int newpass);
void getAmount(int accountnum);
}
