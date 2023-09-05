package client;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Seat {
	String name;
	String type;
	float price;
	String description = "seated"; //standing, seated, etc.
	int screen_ID;
	int theatre_ID;
	int capacity;
	int rowMax=65;
	int colMax;
	int row=65;
	int col=1;	
	int r1,r2,r3;
	
	
	Movie m = new Movie();
	Theatre t = new Theatre();
	Screen s = new Screen();
	int movie_ID;
	
	Seat(String name,String type,float price){
		this.name=name;
		this.type=type;
		this.price=price;
	}
	
	
	Seat() {
		
	}
		
	int addSeats() {
		Scanner sc = new Scanner(System.in);		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			Statement smt = con.createStatement();
			
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
			screen_ID=sc.nextInt();	
			
			//get other details
			
			System.out.println("Enter max rows");
			rowMax+=sc.nextInt();
			System.out.println("Enter max columns");
			colMax=sc.nextInt();
			System.out.println("Enter capacity");
			capacity=sc.nextInt();
			System.out.println("Enter number of Elite rows");
			r1=sc.nextInt();
			System.out.println("Enter number of Premium rows");
			r2=sc.nextInt();
			System.out.println("Enter number of Economy rows");
			r3=sc.nextInt();
			
			
			//set seat values
			
			for(int i=0;i<capacity;i++)
			{
				if (col>colMax) 
				{
					col=1;
					row+=1;
				}
				name=((char)row)+""+col;
				col+=1;
				if (row<65+r1) {
					type="Elite";
					price=180;
				}
				if (row>=65+r1 && row<65+r1+r2) {
					type="Premium";
					price=145;
				}
				if(row>=65+r1+r2 && row<65+r1+r2+r3) {
					type="Economy";
					price=110;
				}
				String dmlcmd = "insert into seat(screen_ID,name,type,price,description) values (\""+screen_ID+"\",\""+name+"\",\""+type+"\",\""+price+"\",\""+description+"\");";
//				System.out.println(dmlcmd);
				smt.executeUpdate(dmlcmd);
			}
			System.out.println("Added to the database!");

			con.close();
			}
		catch (Exception e){
			System.out.println("Failed to Connect" + e);
		}
		sc.close();
		return 0;
	}
	
	int getSeat(int show_ID,String seatName)
	{
		int seat_ID;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			String getseat="select seat.ID from seat inner join movieshow on movieshow.screen_ID=seat.screen_ID where seat.name=\""+seatName+"\" and movieshow.ID="+show_ID+";";
			Statement smt = con.createStatement();
			ResultSet rs1 = smt.executeQuery(getseat);
			if (rs1.next()==false) {
				System.out.println("Ticket ID unavailable. Booking failed.");			
			}				
			else {
			do
	        {
//	            System.out.println("ID: "+rs1.getInt("ID"));
	            seat_ID=rs1.getInt("ID");
	        }while(rs1.next());		
			return seat_ID;		
			}
		con.close();
		} 
		catch (Exception e){
			System.out.println("Failed to Connect" + e);
		}
	return 0;
	}
}
