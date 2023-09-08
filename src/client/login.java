package client;

import java.util.*;
import java.sql.*;

public class Login {
	
	String users[] = {"user","client","admin"};
	
	public int signUp(String name,String mail,String password,String phone,String user_type){
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			PreparedStatement stmt = con.prepareStatement("insert into user (name,mail,password,phone,user_type) values (?,?,?,?,?);");
			stmt.setString(1,name);
			stmt.setString(2,mail);
			stmt.setString(3,password);
			stmt.setString(4,phone);
			stmt.setString(5,user_type);
			stmt.executeUpdate();
			con.close();
			System.out.println("User Registered Successfully!!!");
			System.out.println("Please Proceeed To Sign In");
			return 1;
			}catch(Exception e)
			{
				System.out.println("Mail ID or Phone Already Exists");
				return 0;
			}
		}
	
	int signIn(String mail,String pass){
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			Statement stmt = con.createStatement();
			
			String get_login_details = "select * from user where mail=\""+mail+"\"";
			ResultSet rs = stmt.executeQuery(get_login_details);
			rs.next();
			
			String user_mail = rs.getString("mail");
			String db_pass=rs.getString("password");
			int user_id = rs.getInt("ID");
			con.close();
			
			if(db_pass.equals(pass) && user_mail.equals(mail)) {
				System.out.println("Login Successful!");
				return user_id;
		}
			else
				return 0;
			}catch(Exception e)
			{
				System.out.println("Failed to connect ");				
			} 
		return 0;
	}
	
	public int main(){
		int user_ID = 0,choice=0;
		String mail;
		String pass;
		String name;
		String password;
		String phone;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1.Sign In ");
		System.out.println("2.Sign Up");
		choice = sc.nextInt();
		sc.nextLine();
		
		switch(choice)
		{
			
		case 1:			
				System.out.println("Enter Mail ID:");
				mail = sc.nextLine();
				System.out.println("Enter Password:");
				pass = sc.nextLine();
				user_ID = signIn(mail,pass);
				System.out.println();
				break;
		case 2:			
				int input=0;				
				System.out.println("Enter User Type");
				System.out.println("1. User");
				System.out.println("2. Client");
				System.out.println("3. Admin");
				input = sc.nextInt();
				sc.nextLine();
								
				System.out.println("Enter Name:");
				name = sc.nextLine();
				System.out.println("Enter Mail ID:");
				mail = sc.nextLine();
				System.out.println("Enter Password:");
				password = sc.nextLine();
				System.out.println("Enter Phone:");
				phone= sc.nextLine();
				int registration_status = signUp(name,mail,password,phone,users[input-1]);
				if(registration_status==0)
					System.out.println("Registration unsuccessful. Please try again.");
				break;
		}	
		return user_ID;	
	}
}
