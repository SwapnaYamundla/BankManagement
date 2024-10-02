package com.jsp.DAO;

public class UserDAOHelp {
public static UserDAO helper() {
 UserDAO userDAO = new UserDAOImple();
 return userDAO;
}
}
