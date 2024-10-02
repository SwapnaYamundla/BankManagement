package com.jsp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.jsp.model.BankUserInfo;

public class AdminDAOImple implements AdminDAO{
	String url="jdbc:mysql://localhost:3306/teca54?user=root&password=12345";
	public boolean login(String emailid, int password) {
	
		String query="select * from admin where admin_email_id=? and admin_password=?";
		try {
	Connection connection=DriverManager.getConnection(url);
	PreparedStatement ps=connection.prepareStatement(query);
	ps.setString(1, emailid);
	ps.setInt(2, password);
	ResultSet rs=ps.executeQuery();
	if(rs.isBeforeFirst()) {
		return true;
	}
	else {
		return false;
	}
	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void registration(BankUserInfo bankUserInfo) {
		
		String query="insert into bank_user_details(user_name, user_email_id, user_mobile_number, user_password, account_number, IFSC_code, address, amount, user_bankemailid, gender, DOB) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1,bankUserInfo.getUsername() );
			ps.setString(2, bankUserInfo.getUseremailid());
			ps.setString(3, bankUserInfo.getMobilenum());
			Random r=new Random();
			int num=r.nextInt(9000)+1000;
			ps.setInt(4, num);
			int accnum=r.nextInt(9000000)+1000000;
			ps.setInt(5, accnum);
			ps.setString(6, "JSP54JNTU");
			ps.setString(7, bankUserInfo.getAddress());
			ps.setDouble(8,bankUserInfo.getAmount());
			int mailnum=r.nextInt(9)+10;
			ps.setString(9,bankUserInfo.getUsername()+mailnum+"@gmail.com");
			ps.setString(10,bankUserInfo.getGender());
			ps.setDate(11, bankUserInfo.getDob());
			int result=ps.executeUpdate();
			if(result>0) {
				System.out.println("Registration succefull");
			}
			else {
				System.out.println("Registartion failed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void displayAccountDetails(String email, int password) {
		String query="select * from bank_user_details where user_email_id=? and user_password=?";
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, email);
			ps.setInt(2, password);
			ResultSet result=ps.executeQuery();
			if(result.isBeforeFirst()) {
				if(result.next()) {
				System.out.println("*********************");
				System.out.println(" Bank account Holder's name: "+result.getString("user_name"));//column name
				String num=result.getString("user_mobile_number");
				num=num.replace(num.substring(2, 8),"********");
				System.out.println("Bank account Holder's mobile number: "+num);
				System.out.println("Bank account Holder's Date of Birth: "+result.getDate("DOB"));				
				System.out.println("Bank account Holder's Gender: "+result.getString("gender"));
				System.out.println("Bank account Holder's Account number: "+result.getString("account_number"));
				System.out.println("Bank account Holder's Account balance: "+result.getDouble("amount"));
			}
			else {
				System.out.println("404 error");
			}
		}
		else {
			System.out.println("No user is present....");
		}	
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void changeMobileNumber(int accnum, String oldmobilenum, String newmobilenum) {
	String query="select * from bank_user_details where account_number=? and user_mobile_number=?";
	String numUpdate="update bank_user_details set user_mobile_number=? where account_number=?";
	try {
		Connection connection=DriverManager.getConnection(url);
		PreparedStatement ps=connection.prepareStatement(query);
		ps.setInt(1, accnum);
		ps.setString(2, oldmobilenum);
		ResultSet resultset=ps.executeQuery();
		if(resultset.isBeforeFirst()) {
			PreparedStatement ps1=connection.prepareStatement(numUpdate);
			ps1.setString(1, newmobilenum);
			ps1.setInt(2, accnum);
			int result=ps1.executeUpdate();
			if(result>0) {
				System.out.println("succefully updated mobile number");
			}
			else {
				System.out.println("404 error");
			}
		}
		else {
			System.out.println("Invalid Account number or old mobile number");
		}
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	}

	@Override
	public void deleteAccountDetails(long accnum) {
		String query="delete from bank_user_details where account_number=?";
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setLong(1,accnum);
			int result=ps.executeUpdate();
			if(result>0) {
				System.out.println("data deleted");
			}
			else {
				System.out.println("data deletion failed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
