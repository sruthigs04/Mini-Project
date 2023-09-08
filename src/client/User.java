package client;

import java.io.IOException;
import java.util.Scanner;

public class User {
	
	static void exitfn(int flag) {
		if (flag==0)
		System.out.println("Logged out.\n------  END OF PROGRAM  ------");
	}	
	
	void main(int user_ID) throws IOException {
		Scanner sc1 = new Scanner(System.in);
		int choice=0;
		int input=0;
		int flag=1;
		String city="";
		Ticket tk = new Ticket();
		Event e = new Event();
		Movie m = new Movie();
		
		System.out.println("\n---------------  USER  ----------------\n");
				
		while(flag==1)
		{
			System.out.println("\nMENU - \n(Choose an option between 1-7) ");
			System.out.println("1. Book Event Tickets");
			System.out.println("2. Book Movie Tickets");
			System.out.println("3. Cancel Event Tickets");
			System.out.println("4. Cancel Movie Tickets");
			System.out.println("5. View Now Running Movies");
			System.out.println("6. View Booked Tickets");
			System.out.println("7. Exit");
			input=sc1.nextInt();
			sc1.nextLine();
			
			switch(input) 
			{
			case 1:
				System.out.println("Enter city");
				city=sc1.nextLine();
				System.out.println("Select Event Type");
				System.out.println("1. Music show");
				System.out.println("2. Comedy show");
				System.out.println("3. Theatre show");
				choice=sc1.nextInt();
				switch (choice)
					{
					case 1:
						e.bookEventTickets("Music",user_ID,city);
						break;
					case 2:
						e.bookEventTickets("Comedy",user_ID,city);
						break;
					case 3:
						e.bookEventTickets("Theatre",user_ID,city);
						break;
					}
				System.out.println("\nPress 1 to go to Home Page. To exit, press 0 ");
				flag=sc1.nextInt();
				exitfn(flag);
				break;
			case 2:
				System.out.println("Enter city");
				city=sc1.nextLine();
				tk.bookTicket(user_ID,city);
				System.out.println("\nPress 1 to go to Home Page. To exit, press 0 ");
				flag=sc1.nextInt();
				exitfn(flag);
				break;
			case 3:
				e.cancelEventTicket(user_ID);
				System.out.println("Press 1 to go to Home Page. To exit, press 0 ");
				flag=sc1.nextInt();
				exitfn(flag);
				break;
			case 4:
				tk.cancelTicket(user_ID);
				System.out.println("\nPress 1 to go to Home Page. To exit, press 0 ");
				flag=sc1.nextInt();
				exitfn(flag);
				break;
			case 5:
				m.showMovies();
				System.out.println("\nPress 1 to go to Home Page. To exit, press 0 ");
				flag=sc1.nextInt();
				exitfn(flag);
				break;
				
			case 6:
				System.out.println("Select Show type");
				System.out.println("1. Movie ");
				System.out.println("2. Event ");
				choice=sc1.nextInt();
				switch (choice)
					{
					case 1:
						tk.movieTicketUser(user_ID);
						break;
					case 2:
						e.eventTicketUser(user_ID);
						break;
					}
				System.out.println("\nPress 1 to go to Home Page. To exit, press 0 ");
				flag=sc1.nextInt();
				exitfn(flag);
				break;
			case 7:
				flag=0;
				exitfn(flag);
				break;
			default:
				System.out.println("Invalid option. Please enter value between 1-7.");		
			}
		}
	}

}
