package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Seat {
	String name;
	boolean isBooked = false;
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
		
	int addSeats() {
		Scanner sc = new Scanner(System.in);		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			Statement smt = con.createStatement();
			
			// select theatre
			
			System.out.println("Select Theatre");
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
			theatre_ID=sc.nextInt();
			
			//select screen	
			
			System.out.println("Select Screen");
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
				String dmlcmd = "insert into seat(screen_ID,name,isbooked,type,price,description) values (\""+screen_ID+"\",\""+name+"\",\""+isBooked+"\",\""+type+"\",\""+price+"\",\""+description+"\");";
//				System.out.println(dmlcmd);
				smt.executeUpdate(dmlcmd);
			}
			System.out.println("Added to the database!");

			con.close();
			}
		catch (Exception e){
			System.out.println("Failed to Connect" + e);
		}
		return 0;
	}
	
}
