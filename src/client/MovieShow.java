package client;

import java.sql.*;
import java.util.*;

public class MovieShow {
	String ID;
	String startTime;
	String endTime;
	String date;
	// screen obj 
	
	String languages[] = {"Tamil","Telugu","Malayalam","Kannada","Hindi","English"};
	
	Scanner sc = new Scanner(System.in);
	Movie m = new Movie();
	Theatre t = new Theatre();
	Screen s = new Screen();
	int movie_ID;
	int theatre_ID;
	int screen_ID;
		
	int scheduleShow() 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			
			//choose movie			
			m.showMovies();
			System.out.println("Choose Movie");
			movie_ID=sc.nextInt();
			
			// select theatre
			int a = t.showTheatre();
			if (a==0)
				return 0;
			System.out.println("Choose Theatre");
			theatre_ID = sc.nextInt();
			
			//select screen	
			a = s.showScreen(theatre_ID);
			if (a==0)
				return 0;
			System.out.println("\nSelect Screen");
			screen_ID = sc.nextInt();
			sc.nextLine();
			
			//get other details
			System.out.println("\nEnter start time");
			startTime=sc.nextLine();
			System.out.println("Enter end time");
			endTime=sc.nextLine();
			System.out.println("Enter date");
			date=sc.nextLine();

			// insert into table 			
			 String sql = "insert into movieShow (screen_ID,movie_ID,start,end,date) values(?,?,?,?,?);";
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setInt(1, screen_ID);
			 statement.setInt(2, movie_ID);
			 statement.setString(3, startTime);
			 statement.setString(4, endTime);
			 statement.setString(5, date);
			 statement.executeUpdate();
			 System.out.println("\nShow created.\n");
			con.close();
			} 
		catch (Exception e){
			System.out.println("Failed to Connect. Unable to schedule show. Please try again." );
			return 0;
			}
		
	return 1;
	
	}
	
	int viewShow(String city) 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			Statement smt = con.createStatement();
			System.out.println("Enter date");
			date=sc.nextLine();
			String count1 = "select movieshow.ID,movie.name,movieshow.start,movieshow.date,theatre.name,theatre.locality from movie inner join movieshow on movie.ID = movieshow.movie_ID inner join screen on screen.ID = movieshow.screen_ID inner join theatre on theatre.ID = screen.theatre_ID where movieshow.date=\""+date+"\" and city=\""+city+"\";";
			ResultSet rs1 = smt.executeQuery(count1);
			if (rs1.next()==false) {
				System.out.println("No shows available");
				return 0;
			}				
			else {
				System.out.println("\nShows scheduled on : "+date);
				System.out.println("\n  S No.  Movie Name  Start Time      Theatre         Area");
			do
	        {
	            System.out.print("   "+rs1.getString("ID")+"      ");
	            System.out.print(rs1.getString("name")+"      ");
	            System.out.print(rs1.getString("start")+"    ");	
	            System.out.print(rs1.getString(5)+"   ");
	            System.out.print(rs1.getString("locality")+"\n");
	        }while(rs1.next());
			System.out.println();
			}			
			con.close();
			} 
		catch (Exception e){
			System.out.println("Failed to Connect. Unable to display shows. Please try again.");
			return 0;
			}
		
	return 1;

}
	
	int showSeat(int show_ID) {
		int rowMax=65;
		int colMax=0;
		char y;
		ArrayList<String> booked = new ArrayList<String> ();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			Statement smt = con.createStatement();
			
			// get screen max row and col 
			String count = "select s.maxRow, s.maxCol from screen s, movieshow ms where ms.screen_ID=s.ID and ms.ID="+show_ID+";";
			ResultSet rs = smt.executeQuery(count);
			if (rs.next()==false) {
				System.out.println("No seats available !");
				return 0;
			}				
			else {
			do
	        {
	            String x =rs.getString("maxRow"); 
	            y=x.charAt(0);
				rowMax=(int)y;
				colMax=rs.getInt("maxCol");	
	        }while(rs.next());
			}
					
			
			// get unavailable seats  
			String count1="select bt.seat_name from bookedtickets bt, movieticket mt where mt.ID=bt.ticket_ID and mt.show_ID="+show_ID+";";
			ResultSet rs1 = smt.executeQuery(count1);
			if (rs1.next()==false) {
				System.out.println("");
//				return 0;
			}				
			else {
			do
	        {
	            booked.add(rs1.getString("seat_name"));
	        }while(rs1.next());
			}
			
			
			// print values
			System.out.println("\n\t\t\t\t SCREEN AVAILABLE SEATS\n");
			System.out.print("\t    ");	
			
			for(int x=1;x<=colMax;x++)
			{
				if (x<=9)
					System.out.print(x+"  ");
				else
				System.out.print(x+" ");
			}			
			System.out.println("\n");
			
//			for(int i=65;i<=rowMax;i++)
//			{
//				System.out.print("\t"+(char)(i)+"  ");
//				for(int j=1;j<=colMax;j++)
//				{
//					String temp = (char)i+""+j;
//					if(booked.contains(temp)) {
//							System.out.print(" X "+" ");
//					}
//					else
//					System.out.print("[ ]"+" ");
//				}
			
			for(int i=65;i<=rowMax;i++)
			{
				System.out.print("\t"+(char)(i)+"   ");
				for(int j=1;j<=colMax;j++)
				{
					String temp = (char)i+""+j;
					if(booked.contains(temp)) {
							System.out.print("x "+" ");
					}
					else
					System.out.print("[]"+" ");
				}
				System.out.println();
			}
			System.out.println("\n\t\t\t             SCREEN THIS WAY \n\t\t\t\t--------------------------\n");
			con.close();			
		}catch (Exception e){
			System.out.println("Failed to Connect" );
//			e.printStackTrace();
			}
		
		return 0;
	}
	
}

