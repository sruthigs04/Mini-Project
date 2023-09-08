package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Theatre { 
	String ID;
	String locality;
	String name;
	String city;
	int screen_count;
	Scanner sc = new Scanner(System.in);
	
//	insert into theatre(locality,name,city,screen_count) values ("Thiruvanmyur","S2 Thyagaraja","Chennai",4);
	
	void registerTheatre() {
		
		// get theatre details 
		System.out.println("Enter City ");
		city = sc.nextLine();
		System.out.println("Enter Theatre Name ");
		name = sc.nextLine();
		System.out.println("Enter Locality/Area ");
		locality = sc.nextLine();
		System.out.println("Enter Number of Screens ");
		screen_count = sc.nextInt();
		
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			String cmd = "insert into theatre(locality,name,city,screen_count) values (?,?,?,?) ";
			PreparedStatement statement = con.prepareStatement(cmd);
			statement.setString(1, locality);
			statement.setString(2, name);
			statement.setString(3, city);
			statement.setInt(4, screen_count);
			statement.executeUpdate();			
		}catch (Exception e) {
			System.out.println("Unable to register theatre");
//			e.printStackTrace();
	}
	}
	
	void deregisterTheatre() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			
			System.out.println("Select theatre to degister");
			showTheatre();
			int theatre_ID = sc.nextInt();
			String cmd = "delete from theatre where ID=? ";
			PreparedStatement statement = con.prepareStatement(cmd);
			statement.setInt(1, theatre_ID);
			statement.executeUpdate();			
		}catch (Exception e) {
			System.out.println("Unable to deregister theatre, please try again!");
//			e.printStackTrace();
	}
	} 
			
	int showTheatre() {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
		Statement smt = con.createStatement();
		String count = "select * from theatre";
		ResultSet rs = smt.executeQuery(count);
		if (rs.next()==false)
		{
			System.out.println("No theatres available");
			return 0;
		}				
		else {
//			System.out.println();
			System.out.println("\n Available Theatres - ");
			do				
            {				
                System.out.print(" ID:"+rs.getString("ID")+" - ");
                System.out.print(rs.getString("name")+", ");
                System.out.println(rs.getString("locality"));                
            }while(rs.next());
			System.out.println();
		}
		}catch (Exception e){
			System.out.println("Failed to Connect" );
			return 0;
			}
		return 1;
	}

}
