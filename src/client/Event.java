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
		double price = 0;
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
		return price;
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
			
		}catch (Exception e) {
			System.out.println("Unable to schedule Event");
			e.printStackTrace();
		}
	}
	
	int showEventDetails(int event_ID) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			Statement smt = con.createStatement();
			
			
			String count1 = "select * from event where ID="+event_ID+";";
			ResultSet rs1 = smt.executeQuery(count1);
			if (rs1.next()==false) {
				System.out.println("No event details available");
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
			System.out.println("Failed to Connect" + e);
			}
		return 0;
	}
	
	int showAllEvents(String type) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			Statement smt = con.createStatement();
			
			
			String count1 = "select * from event where type=\""+type+"\";";
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
			System.out.println("Failed to Connect" + e);
			e.printStackTrace();
			}
		return 0;
	}
	
	int bookEventTickets(String eventType, int user_ID) {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			
			//display event details 
			showAllEvents(eventType);
			int event_ID;
			System.out.println("Enter event ID to learn more about the event -");
			event_ID = sc.nextInt();
			showEventDetails(event_ID);
			
			//exit
			System.out.println("Would you like to book tickets for this event? If yes, please enter Y, else enter N to exit to Home Page");
			String option = sc.next();
			sc.nextLine();
			if (option=="n" || option=="N")
				return 0;
			
			//get other details
			System.out.println("Available Categories - ");
			System.out.println(" Silver   - 1000.00 ");
			System.out.println(" Gold     - 2000.00 ");
			System.out.println(" Diamond  - 4000.00 ");
			System.out.println(" Platinum - 10000.00 ");
			System.out.println(" VIP      - 20000.00 ");
			System.out.println("Enter seat Category.");
			String category=sc.nextLine();
			System.out.println("Enter number of seats");
			int seat_count=sc.nextInt();
			
			double price = calculatePrice(eventType,seat_count,category);
			//update table
			String cmd = "insert into eventticket(event_ID,user_ID,category,seat_count,price) values (?,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(cmd);
			statement.setInt(1, event_ID);
			statement.setInt(2, user_ID);
			statement.setString(3, category);
			statement.setInt(4, seat_count);
			statement.setDouble(5, price);
			statement.executeUpdate();
			con.close();
			
			System.out.println("Tickets booked!");
			}catch (Exception e) {
				System.out.println("Unable to schedule Event");
				e.printStackTrace();
			}
		return 0;
	}

}
