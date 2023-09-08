package client;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.*;

public class Movie {
	String ID;
	String name;
	String genre;
	String language;
	String certification;
	boolean is3D;
	String duration;
	String description;
	String releaseDate; 
	String artist;
	
	String languages[] = {"Tamil","Telugu","Malayalam","Kannada","Hindi","English"};
	enum genres {Action,Comedy,Thriller,Romance,Drama,Horror};
	Scanner sc = new Scanner(System.in);
	
	void addMovies() 
	{
		System.out.println("Enter Movie Name");
		name = sc.nextLine();
		System.out.println("Enter Movie Certification");
		certification = sc.nextLine();
		System.out.println("Is the movie in 3D? Type Y for yes and N for no");
		is3D = (sc.nextLine()=="Y") ? (true) : (false);
		System.out.println("Enter Movie Duration");
		duration = sc.nextLine(); 
		System.out.println("Enter Movie Description");
		description = sc.nextLine();
		System.out.println("Enter Movie Release Date");
		releaseDate = sc.nextLine();
		System.out.println("Choose the languages the movie is released in -");
		System.out.println("1.Tamil\n 2. Telugu\n 3. Malayalam\n 4. Kannada\n 5. Hindi\n 6.English");
		language= sc.nextLine();
		language=language.replaceAll("\\s", "_");
		System.out.println("Choose genre -");
		System.out.println("1.Action\n 2. Comedy\n 3. Thriller\n 4. Romance\n 5. Drama\n 6.Horror");
		genre= sc.nextLine();
		genre=genre.replaceAll("\\s", "_");
		System.out.println("Enter Artist Name");
		artist = sc.nextLine();	
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			Statement smt = con.createStatement();
			String dmlcmd = "insert into movie(name,genre,language,is3D,duration,description,releaseDate,artist) values (\""+name+"\",\""+genre+"\",\""+language+"\",\""+is3D+"\",\""+duration+"\",\""+description+"\",\""+releaseDate+"\",\""+artist+"\");";
			smt.executeUpdate(dmlcmd);
			con.close();
			}
		catch (Exception e){
			System.out.println("Failed to Connect" );
		}
	}
	
	int showMovies()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			Statement smt = con.createStatement();
			String count = "select * from movie";
			ResultSet rs = smt.executeQuery(count);
			if (rs.next()==false) {
				System.out.println("No shows available");
				return 0;
			}				
			else {
				System.out.println("\n Available Movies - ");
				System.out.println("\n  S No.  Movie Name    Language    Duration    Release Date");
			do
	        {
	            System.out.print("   "+rs.getString("ID")+"      ");
	            System.out.print(rs.getString("name")+"        ");
	            String x = rs.getString("language");
	            int y = Integer.parseInt(x);
	            System.out.print(languages[y-1]+"      ");	
	            System.out.print(rs.getString("duration")+"     ");
	            System.out.print(rs.getString("releaseDate")+"\n");
	        }while(rs.next());
			System.out.println();
			}			
			con.close();
		}
		catch (Exception e){
			System.out.println("Failed to Connect");
			return 0;
		}
		return 1;
	}

}
