package com.jsp.DAO;

import com.jsp.DAO.AdminDAO;
import com.jsp.DAO.AdminDAOImple;

public class AdminDAOHelp {
	public static AdminDAO helper() {
		AdminDAO ud=(AdminDAO) new AdminDAOImple();
		return ud;
}
}
