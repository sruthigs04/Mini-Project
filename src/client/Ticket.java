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
	
	int payment(double price2) {
		System.out.println("--- Payment ---");
		System.out.println("Amount to be paid - "+price2);
		System.out.println("Please enter Y to complete transaction and N to cancel.");
		String y = sc.next();
		if(y.equals("Y") || y.equals("y"))
			return 1;
		return 0;
	}
	
	void ticketPriceDetails() {
		System.out.println("Available Categories - ");
			System.out.println(" Elite   - 180.00 ");
			System.out.println(" Premium - 145.00 ");
			System.out.println(" Economy - 110.00 ");
	}
	
	
	int bookTicket(int userID, String city) 
	{
		user_ID = userID;
		String seatName;
		String seatType;
		float seatPrice=0;
		int check=1;
		Seat var = new Seat();
		
		//select show
		System.out.println("\n\t\t------ Book Tickets ------");
		check = ms.viewShow(city);
		if (check==0)
				return 0;
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
			ticketPriceDetails();
			System.out.println("Enter category : ");
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
			
			int paid = payment(price);
			if (paid!=1) {
				System.out.println("Payment declined. Please try again.");
				return 0;
			}
			else {
				System.out.println("Payment processed successfully.");
			}
			
			String cmd="insert into movieticket (price,user_ID,show_ID,seat_count,booked_date,booked_time) values (?,?,?,?,?,?);";	
			PreparedStatement statement = con.prepareStatement(cmd);
			statement.setDouble(1, price);
			statement.setInt(2, user_ID);
			statement.setInt(3, show_ID);
			statement.setInt(4, seat_count);
			statement.setString(5, booked_date);
			statement.setString(6, booked_time);
			statement.executeUpdate();

			//get ticket ID
			String getTicketID="select ID from movieticket where booked_date=\""+booked_date+"\" and booked_time=\""+booked_time+"\";";
			Statement smt = con.createStatement();
			ResultSet rs1 = smt.executeQuery(getTicketID);
			if (rs1.next()==false) {
				System.out.println("Ticket ID unavailable. Booking failed.");
				System.out.println("If amount has been deducted, refund will be processed within 3-5 business days.");
				return 0;
			}				
			else {
			do
	        {
	            ticket_ID=rs1.getInt("ID");
	        }while(rs1.next());		
			}
			
								// update movieticket table
			
			for (int i=0;i<seat_count;i++)
			{
				String cmd2 = "insert into bookedtickets (ticket_ID,seat_ID,seat_name) values (?,?,?)";
				seat_ID=var.getSeat(show_ID,seats[i].name);
				PreparedStatement statement1 = con.prepareStatement(cmd2);
				statement1.setInt(1, ticket_ID);
				statement1.setInt(2, seat_ID);
				statement1.setString(3, seats[i].name);
				statement1.executeUpdate();
			}	
			
			System.out.println("Tickets have been booked successfully!");
			System.out.println("Ticket ID - "+ticket_ID);
			}catch (Exception e)
			{
				System.out.println("Failed to book tickets. Please try again! ");
				System.out.println("If amount has been deducted, refund will be processed within 3-5 business days.");
			}
			return 0;
		}
		
	void cancelTicket(int user_ID) {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			
			movieTicketUser(user_ID);
			System.out.println("Select ticket to cancel.");
			int ticket_ID = sc.nextInt();
			String cmd = "delete from movieticket where ID=? ";
			PreparedStatement statement = con.prepareStatement(cmd);
			statement.setInt(1, ticket_ID);
			statement.executeUpdate();
			System.out.println("Tickets cancelled succesfully. Refund will be processed within 3-5 business days.");
			}catch (Exception e) {
				System.out.println("Unable to cancel ticket, please try again!");
//				e.printStackTrace();
		}
		}	
	
	
	int movieTicketUser(int user_ID) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");		
			Statement smt = con.createStatement();				
			String count1 = "select mt.ID,m.name,ms.start,ms.date,t.name,mt.seat_count,t.locality from movie m "
					+ "inner join movieshow ms on m.ID = ms.movie_ID inner join screen s on s.ID = ms.screen_ID "
					+ "inner join theatre t on t.ID = s.theatre_ID inner join movieticket mt on mt.show_ID=ms.ID"
					+ " where user_ID="+user_ID+";";
			ResultSet rs1 = smt.executeQuery(count1);
			if (rs1.next()==false) {
				System.out.println("No tickets booked.");
				return 0;
			}				
			else {
				System.out.println("\nUpcoming Movie Shows -");
			do
	        {
	            System.out.print(" "+rs1.getString("ID")+" - ");
	            System.out.print(rs1.getString("name")+"");
	            System.out.print(" in "+rs1.getString(5)+", ");
	            System.out.print(rs1.getString("locality")+" on ");
	            System.out.print(rs1.getString("date")+" at ");	
	            System.out.print(rs1.getString("start")+". Number of Tickets - ");	
	            System.out.print(rs1.getString("seat_count")+"\n");
	        }while(rs1.next());
			System.out.println();
			}
			}catch (Exception e) {
				System.out.println("Unable to display tickets, please try again!");
//				e.printStackTrace();
			}			
			return 0;
		}
}

//insert into movieticket (price,user_ID,show_ID,seat_count) values (360,1,3,2);
//insert into bookedtickets (ticket_ID,seat_ID,seat_name) values (?,?,?)
//select seat.ID from seat inner join movieshow on movieshow.screen_ID=seat.screen_ID where seat.name="B12" and movieshow.ID=1;
//select mt.ID,m.name,ms.start,ms.date,t.name,mt.seat_count,t.locality from movie m inner join movieshow ms on m.ID = ms.movie_ID inner join screen s on s.ID = ms.screen_ID inner join theatre t on t.ID = s.theatre_ID inner join movieticket mt on mt.show_ID=ms.ID where user_ID=1;