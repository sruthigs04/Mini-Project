package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Screen {
	String ID;
	int capacity;
	String sound;
	String type;
	boolean isWorking;
	Seat seats [] = new Seat[capacity];
	//theatre 
	
	Scanner sc = new Scanner(System.in);
	
//	insert into screen(capacity,sound,type,isWorking,currMovie,theatre_ID) values (178,"Dolby Atmos","IMAX","true",NULL,1);


	public void addScreen() {
		
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
			do
	        {
	            System.out.println("ID:"+rs1.getString("ID"));
	            System.out.println("Name:"+rs1.getString("name"));
	            System.out.println("Capacity:"+rs1.getInt("capacity"));	
	        }while(rs1.next());
			}
		}
		catch (Exception e){
			System.out.println("Failed to Connect" + e);
			}
		return 0;
	}

	
}
