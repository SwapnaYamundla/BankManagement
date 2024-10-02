package com.jsp.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import com.jsp.model.BankUserInfo;

public class UserDAOImple implements UserDAO{
BankUserInfo userInfo=new BankUserInfo();
String url="jdbc:mysql://localhost:3306/teca54?user=root&password=12345";
@Override
public BankUserInfo userlogin(String email, int password) {
String login="select * from bank_user_details where user_email_id=? and user_password=?";
try {
	Connection connection=DriverManager.getConnection(url);
	PreparedStatement ps=connection.prepareStatement(login);
	ps.setString(1, email);
	ps.setInt(2, password);
	ResultSet rs=ps.executeQuery();
	BankUserInfo bankuserInfo=new BankUserInfo();
	if(rs.isBeforeFirst()) {
		if(rs.next()) {
			bankuserInfo.setAccountnumber(rs.getInt("account_number"));
			return bankuserInfo;
		}
	}
	else {
		return null;
	}
} 


catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}		
	return null;
}	

@Override
public void credit(int accountnumber, double amount) {
	String query="select * from bank_user_details where account_number=?";
	String credit="update bank_user_details set amount=? where account_number=?";
	String statement="insert into statement values(?,?,?,?,?,?,?,?,?)";
	if(amount>0) {
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1,accountnumber);
			ResultSet rs=ps.executeQuery();
			if(rs.isBeforeFirst()) {
				if(rs.next()) {
					double databaseAmount=rs.getDouble("account_number");
					int userid=rs.getInt("user_id");
					databaseAmount+=amount;
					PreparedStatement ps1=connection.prepareStatement(credit);
					ps1.setDouble(1,databaseAmount);
					ps1.setInt(2,accountnumber);
					int update=ps1.executeUpdate();
					if(update>0) {
						PreparedStatement ps2=connection.prepareStatement(statement);
						ps2.setDate(1,Date.valueOf(LocalDate.now()));
						ps2.setString(2,"Bank");
						ps2.setDouble(3,databaseAmount);
						Random r=new Random();
						int transactionid=r.nextInt(90000)+10000;
						ps2.setInt(4,transactionid);
						ps2.setTime(5,Time.valueOf(LocalTime.now()));
						ps2.setInt(6,accountnumber);
						ps2.setInt(7,userid);
						ps2.setString(8,"credit");
						int executeUpdate=ps2.executeUpdate();
						if(executeUpdate>0) {
							System.out.println("amount credited succefully");
						}
						else {
							System.out.println("404 error");
						}
						
					}
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	}

@Override
public void debit(int accountnum, double amount) {
	String query="select * from bank_user_details where account_number=?";
	String debit="update bank_user_details set amount=? where account_number=?";
	String statement="insert into statement values(?,?,?,?,?,?,?,?)";
	if(amount>0) {
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1,accountnum);
			ResultSet rs=ps.executeQuery();
			if(rs.isBeforeFirst()) {
				if(rs.next()) {
					double databaseAmount=rs.getDouble("account_number");
					if(amount<databaseAmount) {
					int userid=rs.getInt("user_id");
					databaseAmount-=amount;
					PreparedStatement ps1=connection.prepareStatement(debit);
					ps1.setDouble(1,databaseAmount);
					ps1.setInt(2,accountnum);
					int update=ps1.executeUpdate();

					if(update>0) {
						PreparedStatement ps2=connection.prepareStatement(statement);
						ps2.setDate(1,Date.valueOf(LocalDate.now()));
						ps2.setString(2,"Bank");
						ps2.setDouble(3,databaseAmount);
						Random r=new Random();
						int transactionid=r.nextInt(90000)+10000;
						ps2.setInt(4,transactionid);
						ps2.setTime(5,Time.valueOf(LocalTime.now()));
						ps2.setInt(6,accountnum);
						ps2.setInt(7,userid);
						ps2.setString(8,"credit");
						int executeUpdate=ps2.executeUpdate();
						if(executeUpdate>0) {
							System.out.println("amount debited successfully");
						}
						else {
							System.out.println("404 error");
						}
						
					}
					}
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}	
	
}

@Override
public void print(int accountnum) {
	String query="select * from bank_user_details where account_number=?";
	String statement="insert into statement values(?,?,?,?,?,?,?,?,?)";
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1,accountnum);
			ResultSet rs=ps.executeQuery();
			if(rs.isBeforeFirst()) {
				if(rs.next()) {
					double databaseAmount=rs.getDouble("account_number");
					int userid=rs.getInt("user_id");
					String transactiotype=rs.getString("transaction_type");
					Date dateOfTransaction=rs.getDate("date_of_transaction");
					int transactionid=rs.getInt("transaction_id");
					Time timeOftranaction=rs.getTime("time_of_transaction");
					String status=rs.getString("status");
System.out.println("account number: "+accountnum+"user id: "+userid+" transaction date: "+dateOfTransaction+" transaction time: "+timeOftranaction);
System.out.println("transaction id: "+transactionid+" status: "+status+" amounut: "+databaseAmount);
						
					}
				}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
}

@Override
public void changePassword(int accnum, int oldpass, int newpass) {
	String query="select * from bank_user_details where account_number=?";
	String passUpdate="update bank_user_details set user_password=? where account_number=?";
	try {
		Connection connection=DriverManager.getConnection(url);
		PreparedStatement ps=connection.prepareStatement(query);
		ps.setInt(1, accnum);
	
		ResultSet resultset=ps.executeQuery();
		if(resultset.isBeforeFirst()) {
			PreparedStatement ps1=connection.prepareStatement(passUpdate);
			ps1.setInt(1, newpass);
			ps1.setInt(2, accnum);
			int result=ps1.executeUpdate();
			if(result>0) {
				System.out.println("succefully updated password");
			}
			else {
				System.out.println("404 error");
			}
		}
		else {
			System.out.println("Invalid Account number or old password");
		}
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	
}

@Override
public void getAmount(int accnum) {
String query="select * from bank_user_details where account_number=?";
try {
	Connection connection=DriverManager.getConnection(url);
	PreparedStatement ps=connection.prepareStatement(query);
	ps.setInt(1, accnum);
	ResultSet resultset=ps.executeQuery();
	if(resultset.isBeforeFirst()) {
		if(resultset.next()) {
		double amount=resultset.getDouble("amount");
		System.out.println("amount in that account: "+amount);
		}
	}
	else {
		System.out.println("Invalid Account number ");
	}
} 
catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}		
}


}
