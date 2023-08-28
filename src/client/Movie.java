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
	
	enum languages {Tamil,Telugu,Malayalam,Kannada,Hindi,English};
	enum genres {Action,Comedy,Thriller,Romance,Drama,Horror};
	
	void addMovies() 
	{
		Scanner sc = new Scanner(System.in);
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
		System.out.println("language = "+language);
		System.out.println("1.Action\n 2. Comedy\n 3. Thriller\n 4. Romance\n 5. Drama\n 6.Horror");
		genre= sc.nextLine();
		genre=genre.replaceAll("\\s", "_");
		System.out.println("genre = "+genre);
		System.out.println("Enter Artist Name");
		artist = sc.nextLine();	
		System.out.println(name+" "+genre+" "+language+" "+is3D+" "+duration+" "+description+" "+releaseDate+" "+artist);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			Statement smt = con.createStatement();
			String dmlcmd = "insert into movie(name,genre,language,is3D,duration,description,releaseDate,artist) values (\""+name+"\",\""+genre+"\",\""+language+"\",\""+is3D+"\",\""+duration+"\",\""+description+"\",\""+releaseDate+"\",\""+artist+"\");";
			System.out.println(dmlcmd);
			smt.executeUpdate(dmlcmd);
			con.close();
			}
		catch (Exception e){
			System.out.println("Failed to Connect" + e);
		}
	}
	
	void showMovies()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow","root","root");
			Statement smt = con.createStatement();
			String count = "select * from movie";
			ResultSet rs = smt.executeQuery(count);
			while(rs.next())
            {
                System.out.println("ID:"+rs.getString("ID"));
                System.out.println("Name:"+rs.getString("name"));
                System.out.println("Genre:"+rs.getString("genre"));
                System.out.println("Language:"+rs.getString("language"));
                System.out.println("is3D:"+rs.getString("is3D"));
                System.out.println("duration:"+rs.getString("duration"));
                System.out.println("description:"+rs.getString("description"));
                System.out.println("Release Date:"+rs.getString("releaseDate"));
                System.out.println("Artist:"+rs.getString("artist"));
                System.out.println("\n");
            }
			con.close();
		}
		catch (Exception e){
			System.out.println("Failed to Connect" + e);
		}
	}
}
