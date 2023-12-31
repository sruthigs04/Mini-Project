package client;

import java.sql.*;
import java.util.Scanner;

public class Event {
	String ID;
	String title;
	String artist;
	String day;
	String date;
	String type;
	String city;
	String duration;
	String start;
	String language;
	int minimumAge;
	String description;
	double minimumPrice;
	String place;
	int silver;
	int gold;
	int diamond;
	int platinum;
	int VIP;
	int capacity;
	int no_of_categories;
	String capacity_each;
	
	Scanner sc = new Scanner(System.in);
	
	String capacityEach(int silver,int gold,int diamond,int platinum,int VIP) {
		String val="";
		if(silver>0)
			val+=silver+"_";
		else
			val+="NULL_";
		if(gold>0)
			val+=gold+"_";
		else
			val+="NULL_";
		if(diamond>0)
			val+=diamond+"_";
		else
			val+="NULL_";
		if(platinum>0)
			val+=platinum+"_";
		else
			val+="NULL_";
		if(VIP>0)
			val+=VIP+"";
		else
			val+="NULL";
		return val;
	}
	
	double calculatePrice(String eventType,int no,String seatType) {
		double price = 134.99;
		if (eventType.equals("Music"))
		{
			if(seatType.equals("Silver"))
				price=(1000.00)*no;
			if(seatType.equals("Gold"))
				price=(2000.00)*no;
			if(seatType.equals("Diamond"))
				price=(4000.00)*no;
			if(seatType.equals("Platinum"))
				price=(10000.00)*no;
			if(seatType.equals("VIP"))
				price=(20000.00)*no;
		}
		else if (eventType.equals("Comedy")|| eventType.equals("Theatre"))
		{
			if(seatType.equals("Silver"))
				price=(399.00)*no;
			if(seatType.equals("Gold"))
				price=(599.00)*no;
			if(seatType.equals("Diamond"))
				price=(999.00)*no;
			if(seatType.equals("Platinum"))
				price=(1299.00)*no;
			if(seatType.equals("VIP"))
				price=(1499.00)*no;
		}
		return price;
	}
	
	void ticketPriceDetails(String eventType) {
		System.out.println("Available Categories - ");
		if (eventType.equals("Music"))
		{
			System.out.println(" Silver   - 1000.00 ");
			System.out.println(" Gold     - 2000.00 ");
			System.out.println(" Diamond  - 4000.00 ");
			System.out.println(" Platinum - 10000.00 ");
			System.out.println(" VIP      - 20000.00 ");
			System.out.println("Enter seat Category.");
		}
		else if (eventType.equals("Comedy") || eventType.equals("Theatre") )
		{
			System.out.println(" Silver   - 399.00 ");
			System.out.println(" Gold     - 599.00 ");
			System.out.println(" Diamond  - 999.00 ");
			System.out.println(" Platinum - 1299.00 ");
			System.out.println(" VIP      - 1499.00 ");
			System.out.println("Enter seat Category.");
		}
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
	
	void getEventDetails() {
		System.out.println("Enter Event Title ");
		title = sc.nextLine();
		System.out.println("Enter Artist Name ");
		artist = sc.nextLine();
		System.out.println("Enter day of event ");
		day = sc.nextLine();
		System.out.println("Enter Date ");
		date = sc.nextLine();
		System.out.println("Enter City ");
		city = sc.nextLine();
		System.out.println("Enter Duration ");
		duration = sc.nextLine();
		System.out.println("Enter Start time ");
		start = sc.nextLine();
		System.out.println("Enter language ");
		language = sc.nextLine();
		System.out.println("Enter Minimum Age ");
		minimumAge = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Description ");
		description = sc.nextLine();
		System.out.println("Enter Minimum Price ");
		minimumPrice = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Location ");
		place = sc.nextLine();
		System.out.println("Enter Number of Ticket Categories ");
		no_of_categories = sc.nextInt();
		System.out.println("Enter Number of Silver Category Tickets ");
		silver = sc.nextInt();
		System.out.println("Enter Number of Gold Category Tickets ");
		gold = sc.nextInt();
		System.out.println("Enter Number of Diamond Category Tickets ");
		diamond = sc.nextInt();
		System.out.println("Enter Number of Platinum Category Tickets ");
		platinum = sc.nextInt();
		System.out.println("Enter Number of VIP Tickets ");
		VIP = sc.nextInt();
		System.out.println("Enter Capacity ");
		capacity = sc.nextInt();
		sc.nextLine();
		capacity_each = capacityEach(silver,gold,diamond,platinum,VIP);
	}
	
	void scheduleEvent(String type) {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			
			getEventDetails();
			this.type=type;
			String cmd = " insert into event(title,artist,day,date,type,city,duration,start,language,min_age,"
					+ "description,min_price,location,silver,gold,diamond,platinum,VIP,capacity,category_count,capacity_each) values"
					+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(cmd);
			statement.setString(1, title);
			statement.setString(2, artist);
			statement.setString(3, day);
			statement.setString(4, date);
			statement.setString(5, type);
			statement.setString(6, city);
			statement.setString(7, duration);
			statement.setString(8, start);
			statement.setString(9, language);
			statement.setInt(10, minimumAge);
			statement.setString(11, description);
			statement.setDouble(12, minimumPrice);
			statement.setString(13, place);
			statement.setInt(14, silver);
			statement.setInt(15, gold);
			statement.setInt(16, diamond);
			statement.setInt(17, platinum);
			statement.setInt(18, VIP);
			statement.setInt(19, capacity);
			statement.setInt(20, no_of_categories);
			statement.setString(21, capacity_each);
			statement.executeUpdate();
			System.out.println("Event scheduled successfully!");

			
		}catch (Exception e) {
			System.out.println("Unable to schedule Event");
//			e.printStackTrace();
		}
	}
	
	int showEventDetails(int event_ID,String city, String eventType) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			Statement smt = con.createStatement();
			
			
			String count1 = "select * from event where ID="+event_ID+" and city=\""+city+"\" and type=\""+eventType+"\";";
			ResultSet rs1 = smt.executeQuery(count1);
			if (rs1.next()==false) {
				System.out.println("No event details available.");
				return 0;
			}				
			else {
			do
	        {
	            System.out.print("\n "+rs1.getString("title")+" - ");
	            System.out.print(rs1.getString("description")+"\n ");
	            System.out.print("by - "+rs1.getString("artist")+"\n ");	
	            System.out.print("Happening in - "+rs1.getString("city")+" At ");
	            System.out.print(rs1.getString("location")+"\n ");
	            System.out.print("On - "+rs1.getString("date")+" (");
	            System.out.print(rs1.getString("day")+")\n ");
	            System.out.print("Timing - "+rs1.getString("start")+"\n ");
	            System.out.print("Duration - "+rs1.getString("duration")+" Hours\n ");
	            System.out.print("Minimum Age - "+rs1.getString("min_age")+" Years\n ");
	            System.out.print("Language - "+rs1.getString("language")+"\n ");
	            System.out.print("Tickets Rs."+rs1.getString("min_price")+" Onwards");

	        }while(rs1.next());
			System.out.println();
			}			
			con.close();
			} 
		catch (Exception e){
			System.out.println("Connection error. Please try again.");
			return 0;
			}
		return 1;
	}
	
	int showAllEvents(String type, String city) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			Statement smt = con.createStatement();			
			
			String count1 = "select * from event where type=\""+type+"\" and city=\""+city+"\";";
			ResultSet rs1 = smt.executeQuery(count1);
			if (rs1.next()==false) {
				System.out.println("No event details available");
				return 0;
			}				
			else {
				System.out.println("\nUpcoming "+type+" Shows -");
				System.out.println("\n  ID        Title          Artist        Date        Time       City     ");
			do
	        {
	            System.out.print("  "+rs1.getString("ID")+"   ");
	            System.out.print(rs1.getString("title")+"   ");
	            System.out.print(rs1.getString("Artist")+"   ");	
	            System.out.print(rs1.getString("date")+"   ");
	            System.out.print(rs1.getString("start")+"    ");
	            System.out.print(rs1.getString("city")+"\n");

	        }while(rs1.next());
			System.out.println();
			}			
			con.close();
			} 
		catch (Exception e){
			System.out.println("Failed to Connect");
//			e.printStackTrace();
			return 0;
			}
		return 1;
	}
	
	int bookEventTickets(String eventType, int user_ID, String city) {
		int check=1;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			
			//display event details 
			check = showAllEvents(eventType,city);
			if (check==0)
				return 0;
			int event_ID;
			System.out.println("Enter event ID to learn more about the event -");
			event_ID = sc.nextInt();
			check = showEventDetails(event_ID,city,eventType);
			if (check==0)
				return 0;
			
			//exit
			System.out.println("Would you like to book tickets for this event? If yes, please enter Y, else enter N to exit to Home Page");
			String option = sc.next();
			sc.nextLine();
			if (option.equals("n") || option.equals("N"))
				return 0;
			
			//get other details
			ticketPriceDetails(eventType);
			String category=sc.nextLine();
			System.out.println("Enter number of seats");
			int seat_count=sc.nextInt();
			
			//calculate price
			double price = calculatePrice(eventType,seat_count,category);
			
			//check if seats are available
			int available_seats=0;
			if(!category.equals("VIP"))
				category=category.toLowerCase();
			Statement statement2 = con.createStatement();
			String cmd2 = "select "+category+" from event where ID="+event_ID+";";
			ResultSet rs1 = statement2.executeQuery(cmd2);
			if (rs1.next()==false) {
				System.out.println("Seats unavailable. Booking failed.");
				return 0;
			}				
			else {
			do
	        {
	            available_seats= rs1.getInt(category);
	        }while(rs1.next());		
			}			
			if(available_seats<seat_count) {
				System.out.println("Only "+available_seats+" seats are available. Please try booking with a lower number of seats.");
				return 0;
			}				
			
			//payment 
			int paid = payment(price);
			if (paid!=1) {
				System.out.println("Payment declined. Please try again.");
				return 0;
			}
			else {
				System.out.println("Payment processed successfully.");
			}
						
			//update table
			String cmd = "insert into eventticket(event_ID,user_ID,category,seat_count,price) values (?,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(cmd);
			statement.setInt(1, event_ID);
			statement.setInt(2, user_ID);
			statement.setString(3, category);
			statement.setInt(4, seat_count);
			statement.setDouble(5, price);
			statement.executeUpdate();
			
			if(!category.equals("VIP"))
				category=category.toLowerCase();
			Statement statement1 = con.createStatement();
			String cmd1 = "update event set "+category+"="+category+"-"+seat_count+" where ID="+event_ID+";";
//			System.out.println(cmd1);
			statement1.executeUpdate(cmd1);
			
			con.close();			
			System.out.println("Tickets booked!");
			}catch (Exception e) {
				System.out.println("Unable to book event tickets. Please try again.");
//				e.printStackTrace();
			}
		return 0;
	}
	
	int cancelEventTicket(int user_ID) {
		int ticket_ID=0;
		int seat_count=0;
		String category;
		int event_ID=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			
			// display all tickets booked by user 
			eventTicketUser(user_ID);
		
			// select ticket to be cancelled
			System.out.println("Select the show ticket to be cancelled.");
			ticket_ID=sc.nextInt();
			
			//get ticket details
			Statement smt1 = con.createStatement();				
			String count2 = "select category,seat_count,event_ID from eventticket where ID="+ticket_ID+";";
			ResultSet rs2 = smt1.executeQuery(count2);
			if (rs2.next()==false) {
				System.out.println("No event details available");
				return 0;
			}				
			else {
			do
	        {
	            category=rs2.getString("category");
	            if (!category.equals("VIP"))
	            	category=category.toLowerCase();
	            seat_count=rs2.getInt("seat_count");
	            event_ID=rs2.getInt("event_ID");
	        }while(rs2.next());
			System.out.println();
			}					
			
			//delete ticket from eventticket table and update event table 		
			String cmd = "delete from eventticket where ID="+ticket_ID+";";
			String cmd1="update event set "+category+"="+category+"+"+seat_count+" where ID="+event_ID+";";
			PreparedStatement statement = con.prepareStatement(cmd);
			PreparedStatement statement1 = con.prepareStatement(cmd1);
			statement.executeUpdate();	
			statement1.executeUpdate();	
			System.out.println("Tickets have been cancelled successfully. Refund will be processed in 3-5 business tickets.");
			
		}catch (Exception e) {
			System.out.println("Unable to cancel event ticket, please try again!");
//			e.printStackTrace();
	}
		return 1;
	}

	
	int eventTicketUser(int user_ID)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");		
			Statement smt = con.createStatement();				
			String count1 = "select et.ID,e.title,e.date,e.city,et.category,et.seat_count from event e,eventticket et where et.user_ID="+user_ID+" and et.event_ID=e.ID;";
			ResultSet rs1 = smt.executeQuery(count1);
			if (rs1.next()==false) {
				System.out.println("No event details available");
				return 0;
			}				
			else {
				System.out.println("\nUpcoming Event Shows -");
			do
	        {
	            System.out.print(" "+rs1.getString("ID")+" - ");
	            System.out.print(rs1.getString("title")+"");
	            System.out.print(" in "+rs1.getString("city")+" on ");
	            System.out.print(rs1.getString("date")+". ");	
	            System.out.print(rs1.getString("category")+" Ticket - ");
	            System.out.print(rs1.getString("seat_count")+"\n");
	        }while(rs1.next());
			System.out.println();
			}
			}catch (Exception e) {
				System.out.println("Connection failed, please try again!");
//				e.printStackTrace();
		}			
		return 0;			
	}
}
