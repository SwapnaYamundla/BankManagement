package com.jsp.BankProject;

import java.sql.Date;
import java.util.Scanner;

import com.jsp.DAO.AdminDAO;
import com.jsp.DAO.AdminDAOHelp;
import com.jsp.DAO.UserDAO;
import com.jsp.DAO.UserDAOHelp;
import com.jsp.model.BankUserInfo;

public class App 
{
	public static void main( String[] args )
    {
		BankUserInfo bui=new BankUserInfo();
    	Scanner sc=new Scanner(System.in);
        AdminDAO u=AdminDAOHelp.helper();
        System.out.println("Welcome to bank application");
        int count=0;
        while(count<=3) {
        	System.out.println("1.to admin login ");
        	System.out.println("2.to user login");
        	
        	int choice=sc.nextInt();
        	switch(choice) {
        	case 1:{
        		
        		System.out.println("enter emailid ");
                String email=sc.next();
                System.out.println("enter password");
                int pass=sc.nextInt();
                if(u.login(email, pass)) {
                	System.out.println("1.to register ");
                	System.out.println("2.to display details");
                  	System.out.println("3. to delete details");
                	System.out.println("4.to update mobile number");
              
               System.out.println("enter choice");
               int choice2=sc.nextInt();
               switch(choice2) {
               case 1:{
        		System.out.println("enter user name");
        		bui.setUsername(sc.next());
        		
        		System.out.println("enter user email");
        		bui.setUseremailid(sc.next());
        		
        		System.out.println("enter mobile number");
        		bui.setMobilenum(sc.next());
        		
        		System.out.println("enter gender");
        		bui.setGender(sc.next());
        		
        		System.out.println("enter address");
        		bui.setAddress(sc.next());
        		
        		System.out.println("enter DOB");
        		String date=sc.next();
        		Date date1=Date.valueOf(date);
        		bui.setDob(date1);
        		
        		System.out.println("enter amount");
        		bui.setAmount(sc.nextDouble());
        		
        		u.registration(bui);
        		break;
               }
               case 2:{
            	System.out.println(" Enter bank emailid");
            	String mail=sc.next();
            	System.out.println("Enter the password");
            	int password=sc.nextInt();
            	u.displayAccountDetails(email, password);
            	break;
                }
               case 3:{
            	   
            	System.out.println("Enter the account number");
            	Long accountnum=sc.nextLong();
            	u.deleteAccountDetails(accountnum);
            	break;
               }
               case 4:{
            	System.out.println(" Enter account number");
            	int accnum=sc.nextInt();
            	System.out.println("enter old mobile number");
            	String oldmobilenum=sc.next();
            	System.out.println("enter new mobile number");
            	String newmobilenumber=sc.next();
            	u.changeMobileNumber(accnum, oldmobilenum, newmobilenumber);
            	break;
               }
               default:System.out.println("Invalid choice for admin operation");
            	   break;
               }
        		
        	}
                else {
                	System.out.println("login failed");
                	count++;
                	if(count>=3) {
                		System.out.println("time out");
                		break;
                	}
       continue;
                		}
                break;
        	}
        	case 2:{
        		UserDAO userDAO=UserDAOHelp.helper();
        		System.out.println("enter emailid");
        		String mail=sc.next();
        		System.out.println("enter password");
        		int password=sc.nextInt();
        		BankUserInfo userlogin=userDAO.userlogin(mail, password);
        		if(userlogin!=null) {
        			System.out.println("1.to credit the amount"+"\n 2.to debit the amount"+"\n3. for statemnet printing"
        		+"\n 4.mobile to mobile transaction"+"\n5.changing the password"+"\n6.check balance");
        			int userchoice=sc.nextInt();
        			switch(userchoice) {
        			case 1:{
        				System.out.println("enter the account number");
        				int accnumber=sc.nextInt();
        				System.out.println("enter the amount");
        				double amount=sc.nextDouble();
        				if(userlogin.getAccountnumber()==accnumber) {
        					userDAO.credit(accnumber, amount);
        				}
        				else {
        					System.out.println("inavalid account number");
        				}
        			}
        			case 2:{
        				System.out.println("Enter account number");
        				int accnum=sc.nextInt();
        				System.out.println("Enter the amount");
        				double amount=sc.nextDouble();
        				if(userlogin.getAccountnumber()==accnum) {
        					userDAO.debit(accnum, amount);
        				}
        				else {
        					System.out.println("Invalid account number");
        				}
        				break;
        			}
        			case 3:{
        				System.out.println("enter the account number");
        				int accnum=sc.nextInt();
        				if(userlogin.getAccountnumber()==accnum) {
        					userDAO.print(accnum);
        				}
        				else {
        					System.out.println("Invalid account number");
        				}
        				break;
        			}
        			case 4:{
        				
        			break;	
        			}
        			case 5:{
        				System.out.println("Enter account number");
        				int accnum=sc.nextInt();
        				System.out.println("enter old password");
        				int oldp=sc.nextInt();
        				System.out.println("enter new password");
        				int newp=sc.nextInt();
        				userDAO.changePassword(accnum,oldp,newp);
        				break;
        			}
        			case 6:{
        				System.out.println("Enter account number");
        				int accnum=sc.nextInt();
        				userDAO.getAmount(accnum);
        				break;
        			}
        			default:System.out.println("Invalid user  operation choice");
        			break;
        			}
        			
        		}
        		else {
        			System.out.println("User login failed");
        		}
        		
        	break;
        	}
        	
        }
        	System.out.println("Do you want to continue the application");
        	System.out.println("enter 'yes' or 'no'");
        	String ch=sc.next();
        	if(ch.equals("yes")) {
        		continue;
        }
        	else {
        		System.out.println("Thank you ");
        		break;
        	}
        
        
        }
        
    }
}
