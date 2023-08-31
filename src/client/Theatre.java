package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Theatre { 
	String ID;
	String locality;
	String name;
	String city;
	int n;  // number of screens in each theatre 
	Screen[] screens = new Screen[n]; 
	
//	insert into theatre(locality,name,city,screen_count) values ("Thiruvanmyur","S2 Thyagaraja","Chennai",4);
	
	void registerTheatre() {}
	
	void deregisterTheatre() {} 
			
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
		else 
		{
			do
            {				
                System.out.println("ID:"+rs.getString("ID"));
                System.out.println("Name:"+rs.getString("name"));
                System.out.println("Number of Screens:"+rs.getString("screen_count"));			
            }while(rs.next());
		}
		}catch (Exception e){
			System.out.println("Failed to Connect" + e);
			}
		return 0;
	}

}
