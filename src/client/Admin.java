package client;

import java.util.*;

public class Admin {
	
	static void exitfn(int flag) {
		if (flag==0)
		System.out.println("Logged out.\n------  END OF PROGRAM  ------");
	}
	
	void main(int user_ID) {
		Scanner sc = new Scanner(System.in);
		int input=0 ;
		int flag=1; 
		Movie m = new Movie();
		
		System.out.println("\t\t\t\t---------------  Admin  ----------------");
		
		while(flag==1 )
		{
			System.out.println("\nMENU - \n(Choose an option between 1-3) ");
			System.out.println("1. Add Movie");
			System.out.println("2. View Movies");
			System.out.println("3. Exit");
			
			input=sc.nextInt();
			sc.nextLine();
			
			switch(input) 
			{
			case 1:
				m.addMovies();
				System.out.println("Press 1 to go to Home Page. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 2:
				m.showMovies();
				System.out.println("Press 1 to go to Home Page. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 3:
				System.out.println("End of program");
				flag=0;
				break;
			default:
				System.out.println("Invalid option. Please enter value between 1-3.");
			}
		}
	}

}
