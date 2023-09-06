package client;

import java.util.Scanner;

public class User {
	
	static void exitfn(int flag) {
		if (flag==0)
		System.out.println("Logged out.\n------  END OF PROGRAM  ------");
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice=0;
		int input=0 ;
		int flag=1;
		int user_ID=1;
		MovieShow ms = new MovieShow();
		Ticket tk = new Ticket();
		Event e = new Event();
		
		System.out.println("---------------  USER  ----------------");
		
		while(flag==1)
		{
			System.out.println("1. Book Event Tickets");
			System.out.println("2. Book Movie Tickets");
			System.out.println("3. Cancel Event Tickets");
			System.out.println("4. Cancel Movie Tickets");
			System.out.println("5. Update Profile");
			System.out.println("6. View Upcoming Shows");
			System.out.println("7. Exit");
			
			input=sc.nextInt();
			
			switch(input) 
			{
			case 1:
				System.out.println("Select Event Type");
				System.out.println("1. Music show");
				System.out.println("2. Comedy show");
				System.out.println("3. Theatre show");
				choice=sc.nextInt();
				switch (choice)
					{
					case 1:
						e.bookEventTickets("Music",user_ID);
						break;
					case 2:
						e.bookEventTickets("Comedy",user_ID);
						break;
					case 3:
						e.bookEventTickets("Theatre",user_ID);
						break;
					}
				System.out.println("\nDo you want to continue? If yes, press 1. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 2:
				tk.bookTicket(user_ID);
				System.out.println("\nDo you want to continue? If yes, press 1. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 3:
//				e.cancelEventTicket(user_ID);
				System.out.println("\nDo you want to continue? If yes, press 1. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 4:
				System.out.println("\nDo you want to continue? If yes, press 1. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 5:
				System.out.println("Update Profile");
				System.out.println("\nDo you want to continue? If yes, press 1. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
				
			case 6:
//				ms.viewShow();
//				ms.showSeat(1);
//				e.showEventDetails(2);
//				e.showAllEvents("Music");
				System.out.println("\nDo you want to continue? If yes, press 1. To exit, press 0 ");
				flag=sc.nextInt();
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
	sc.close();
	}

}
