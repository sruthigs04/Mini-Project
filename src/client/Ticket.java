package client;

import java.sql.*;
import java.util.Date;
import java.util.*;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  

public class Ticket {
	String ID;
	String name;
	String artists[];
	int numTickets;  
	double price;	
	int user_ID;
	int show_ID;
	int ticket_ID;
	int seat_ID;
	int seat_count;
	String maxRow;
	int maxCol;
//	int seatPrice;
	Seat seats[] = new Seat[10];	
	
	MovieShow ms = new MovieShow();
	Scanner sc = new Scanner(System.in);
	
	double calculatePrice (int seat_count, String type) {
		double cost=14.99;
		if (type.equals("Elite"))
			cost+=180*seat_count;
		if(type.equals("Premium"))
			cost+=145*seat_count;
		if(type.equals("Economy"))
			cost+=110*seat_count;
		return cost;
	}
	
	int bookTicket(int userID) 
	{
		user_ID = userID;
		String seatName;
		String seatType;
		float seatPrice=0;
		Seat var = new Seat();
//		int flag=0;
		
		//select show
		System.out.println("\n\t\t------ Book Tickets ------");
		ms.viewShow();
		System.out.println("Select Show");
		show_ID=sc.nextInt();
		
		//connection establishment 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			
									// update movieticket table
			
			// get current date and time
			Date date = Calendar.getInstance().getTime();  
			DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");  
			String booked_date = dateFormat.format(date);  
			Date time = Calendar.getInstance().getTime();  
			DateFormat dateFormat1 = new SimpleDateFormat("HH:mm");  
			String booked_time = dateFormat1.format(time); 
			
			// get other details
			System.out.println("Enter number of seats :");
			seat_count=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter category");
			seatType=sc.nextLine();
			if (seatType=="Elite")
				seatPrice =180;
			if(seatType =="Premium")
				seatPrice =145;
			if(seatType =="Economy")
				seatPrice =110;
			
			//display available seats
			ms.showSeat(show_ID);

			
			//update seats Array			
			for(int i=0;i<seat_count;i++)
			{
				System.out.println("Select seat");
				seatName=sc.nextLine();
				Seat s= new Seat(seatName,seatType,seatPrice);
				seats[i]=new Seat("","",0);
				seats[i]=s;	
			}
			
			// calculate price 
			price = calculatePrice(seat_count,seatType);
			
//			String cmd="insert into movieticket (price,user_ID,show_ID,seat_count,booked_date,booked_time) values (\""+price+"\","+user_ID+","+show_ID+","+seat_count+",\""+booked_date+"\",\""+booked_time+"\");";
			String cmd="insert into movieticket (price,user_ID,show_ID,seat_count,booked_date,booked_time) values (?,?,?,?,?,?);";	
//			Statement smt1 = con.createStatement();
			PreparedStatement statement = con.prepareStatement(cmd);
			statement.setDouble(1, price);
			statement.setInt(2, user_ID);
			statement.setInt(3, show_ID);
			statement.setInt(4, seat_count);
			statement.setString(5, booked_date);
			statement.setString(6, booked_time);
//			System.out.println(cmd);
			statement.executeUpdate();

			//get ticket ID
			String getTicketID="select ID from movieticket where booked_date=\""+booked_date+"\" and booked_time=\""+booked_time+"\";";
			Statement smt = con.createStatement();
			ResultSet rs1 = smt.executeQuery(getTicketID);
			if (rs1.next()==false) {
				System.out.println("Ticket ID unavailable. Booking failed.");
				return 0;
			}				
			else {
			do
	        {
//	            System.out.println("Ticket ID: "+rs1.getInt("ID"));
	            ticket_ID=rs1.getInt("ID");
	        }while(rs1.next());			}
			
								// update movieticket table
			
			for (int i=0;i<seat_count;i++)
			{
				String cmd2 = "insert into bookedtickets (ticket_ID,seat_ID,seat_name) values (?,?,?)";
				seat_ID=var.getSeat(show_ID,seats[i].name);
				PreparedStatement statement1 = con.prepareStatement(cmd2);
				statement1.setInt(1, ticket_ID);
				statement1.setInt(2, seat_ID);
				statement1.setString(3, seats[i].name);
//				System.out.println(cmd2);
				statement1.executeUpdate();
			}	
			
			
			System.out.println("Tickets have been booked successfully!");
		}catch (Exception e)
		{
			System.out.println("Failed to book tickets. Please try again! " + e);
		}
		return 0;
	}
	
	void cancelTicket() {}	
}

//insert into movieticket (price,user_ID,show_ID,seat_count) values (360,1,3,2);
//insert into bookedtickets (ticket_ID,seat_ID,seat_name) values (?,?,?)
//select seat.ID from seat inner join movieshow on movieshow.screen_ID=seat.screen_ID where seat.name="B12" and movieshow.ID=1;