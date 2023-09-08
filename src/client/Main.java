package client;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {
	
	static String get_user_type(int user_ID){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			Statement stmt = con.createStatement();
			String get_login_details = "select * from user where ID=\""+user_ID+"\"";
			ResultSet rs = stmt.executeQuery(get_login_details);
			rs.next();
			String data = rs.getString("user_type");
			con.close();
			return data;
			}catch(Exception e){
				System.out.println("Connection failed.");
				return "";
			}
		}
	
	static void exitfn(int flag) {
		if (flag==0)
		System.out.println("Logged out.\n------  END OF PROGRAM  ------");
		}
	

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		int login_user_id=0;
		String login_type="";
		System.out.println("\n\t---------------  ME-BOOK  ----------------");
		System.out.println("\t---- Movie and Event Ticket Booking ------\n");

		
		do
		{
			Login user_login = new Login();
			login_user_id = user_login.main();
			if(login_user_id!=0)
			{
				break;
			}
		}while(login_user_id==0);
		
		login_type = get_user_type(login_user_id);
		
		if(login_type.equals("user"))
		{
			User user = new User();	
			user.main(login_user_id);
		}
			
		else if(login_type.equals("client"))
		{
			Client client = new Client();
			client.main(login_user_id);
		}
		else if(login_type.equals("admin"))
		{
			Admin admin = new Admin();
			admin.main(login_user_id);
		}
		else if(login_type.equals(""))
		{
			System.out.println("Error");
		}	
	}

}
