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
			System.out.println("Choose Movie\n");
			m.showMovies();
			movie_ID=sc.nextInt();
			
			// select theatre
			System.out.println("Choose Theatre\n");
			t.showTheatre();
			theatre_ID = sc.nextInt();
			
			//select screen	
			System.out.println("Select Screen");
			s.showScreen(theatre_ID);
			screen_ID = sc.nextInt();
			sc.nextLine();
			
			//get other details
			System.out.println("Enter start time");
			startTime=sc.nextLine();
			System.out.println("Enter end time");
			endTime=sc.nextLine();
			System.out.println("Enter date");
			date=sc.nextLine();

			// insert into table 			
			 String sql = "insert into movieShow (screen_ID,movie_ID,start,end,date) values(?,?,?,?,?);";
			 System.out.println(sql);
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setInt(1, screen_ID);
			 statement.setInt(2, movie_ID);
			 statement.setString(3, startTime);
			 statement.setString(4, endTime);
			 statement.setString(5, date);
			 statement.executeUpdate();
			 System.out.println("Record created.");
			con.close();
			} 
		catch (Exception e){
			System.out.println("Failed to Connect" + e);
			}
		
	return 0;
	
	}
	
	int viewShow() 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			Statement smt = con.createStatement();
			System.out.println("Enter date");
			date=sc.nextLine();
			String count1 = "select movieshow.ID,movie.name,movieshow.start,movieshow.date,theatre.name,theatre.locality from movie inner join movieshow on movie.ID = movieshow.movie_ID inner join screen on screen.ID = movieshow.screen_ID inner join theatre on theatre.ID = screen.theatre_ID where movieshow.date=\""+date+"\";";
			ResultSet rs1 = smt.executeQuery(count1);
			if (rs1.next()==false) {
				System.out.println("No screens available");
				return 0;
			}				
			else {
			do
	        {
	            System.out.println("ID: "+rs1.getString("ID"));
	            System.out.println("Movie Name: "+rs1.getString("name"));
	            System.out.println("Show timing: "+rs1.getString("start"));	
	            System.out.println("Date: "+rs1.getString("date"));
	            System.out.println("Theatre: "+rs1.getString(5));
	            System.out.println("Area: "+rs1.getString("locality")+"\n");

	        }while(rs1.next());
			}			
			con.close();
			} 
		catch (Exception e){
			System.out.println("Failed to Connect" + e);
			}
		
	return 0;

}
	
}
