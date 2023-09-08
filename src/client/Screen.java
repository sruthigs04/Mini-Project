package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Screen {
	String ID;
	int capacity;
	String sound;
	String type;
	String isWorking;
	String currMovie;
	int theatre_ID;
	String name;
	String maxRow;
	int maxCol;
	Theatre t = new Theatre();
	Seat s = new Seat();	
	Scanner sc = new Scanner(System.in);
	
//	insert into screen(capacity,sound,type,isWorking,currMovie,theatre_ID) values (178,"Dolby Atmos","IMAX","true",NULL,1);


	int addScreen() {

		// display theatre details 
		t.showTheatre();
		System.out.println("Select Theatre");
		theatre_ID=sc.nextInt();
		
		// get theatre details 
				System.out.println("Enter Capacity ");
				capacity = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Sound System Type (Eg:Dolby Atmos)");
				sound = sc.nextLine();
				System.out.println("Enter Screen type (Eg:IMAX)");
				type = sc.nextLine();
				System.out.println("Enter working condition (true/false)");
				isWorking = sc.nextLine();
				currMovie = "NULL";
				System.out.println("Enter Screen Name (Eg:Screen 5)");
				name = sc.nextLine();
				System.out.println("Enter Maximum Number of Rows as Alphabet (Eg:G) ");
				maxRow = sc.nextLine();
				System.out.println("Enter Maximum Number of Columns as Number (Eg:22)");
				maxCol = sc.nextInt();
				sc.nextLine();
				
				
				//add screen to screen table 
				try {			
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
					String cmd = "insert into screen(capacity,sound,type,isWorking,currMovie,theatre_ID,name,maxRow,maxCol) values (?,?,?,?,?,?,?,?,?) ";
					PreparedStatement statement = con.prepareStatement(cmd);
					statement.setInt(1, capacity);
					statement.setString(2, sound);
					statement.setString(3, type);
					statement.setString(4, isWorking);
					statement.setString(5, currMovie);
					statement.setInt(6, theatre_ID);
					statement.setString(7, name);
					statement.setString(8, maxRow);
					statement.setInt(9, maxCol);
					statement.executeUpdate();			
				}catch (Exception e)
				{
					System.out.println("Unable to add screen");
//					e.printStackTrace();
				}
				
				int screen_ID=0;				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
					Statement smt = con.createStatement();
					String count1 = " select ID from screen where theatre_ID="+theatre_ID+" order by ID desc limit 1;";
					ResultSet rs1 = smt.executeQuery(count1);
					if (rs1.next()==false) {
						System.out.println("No screens available");
						return 0;
					}						
					else {
					do
			        {
			            screen_ID=rs1.getInt("ID");
			        }while(rs1.next());
					}
				}
				catch (Exception e){
					System.out.println("Failed to Connect " );
					}
			s.addSeats(screen_ID);
			return 0;
	} 
	
	int showScreen(int theatre_ID) 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			Statement smt = con.createStatement();
			String count1 = "select * from screen where theatre_ID=\""+theatre_ID+"\";";
			ResultSet rs1 = smt.executeQuery(count1);
			if (rs1.next()==false) {
				System.out.println("No screens available");
				return 0;
			}				
			else {
				System.out.println("\n Available Screens - ");
			do
	        {
				System.out.print(" ID:"+rs1.getString("ID")+" - ");
                System.out.print(rs1.getString("name")+", ");
                System.out.println(rs1.getString("capacity")); 
	        }while(rs1.next());
			}
		}
		catch (Exception e){
			System.out.println("Failed to Connect");
			return 0;
			}
		return 1;
	}

	int removeScreen() {
		t.showTheatre();
		System.out.println("Select Theatre");
		theatre_ID=sc.nextInt();
		showScreen(theatre_ID);
		int screen_ID=sc.nextInt();
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			String cmd = "delete from screen where ID="+screen_ID+";";
			PreparedStatement statement = con.prepareStatement(cmd);
			statement.executeUpdate();			
		}catch (Exception e) {
			System.out.println("Unable to remove screen, please try again!");
//			e.printStackTrace();
	}		
		return 0;
		
	}

	
}
