package client;

import java.sql.*;
import java.sql.DriverManager;
import java.util.*;

public class Ticket {
	String ID;
	String date;
	String name;
	String artists[];
	int numTickets;  
	int price;
	
	int user_ID;
	int show_ID;
	int seat_count;
	MovieShow ms = new MovieShow();
	Scanner sc = new Scanner(System.in);
	
	int bookTicket(int userID) {
		user_ID = userID;
		System.out.println("Select show");
		ms.viewShow();
		show_ID=sc.nextInt();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			String cmd="insert into movieticket (price,user_ID,show_ID,seat_count) values (?,?,?,?);";			
			PreparedStatement statement = con.prepareStatement(cmd);
			statement.setInt(1, price);
			statement.setInt(1, user_ID);
			statement.setInt(1, show_ID);
			statement.setInt(1, seat_count);
		}catch (Exception e)
		{
			System.out.println("Failed to book tickets. Please try again!" + e);
		}
//		insert into movieticket (price,user_ID,show_ID,seat_count) values (360,1,3,2);
//		insert into bookedtickets (ticket_ID,seat_ID,seat_name) values (?,?,?)
		return 0;
	}
	
	void cancelTicket() {}
	
	
}
