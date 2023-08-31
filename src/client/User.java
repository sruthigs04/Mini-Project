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
		Seat s = new Seat();
		Screen s1=new Screen();
		Theatre t = new Theatre();
		MovieShow ms = new MovieShow();
		Ticket tk = new Ticket();
		
		System.out.println("---------------  Consumer  ----------------");
		
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
						System.out.println("Add music show ()");
						break;
					case 2:
						System.out.println("Add comedy show ()");
						break;
					case 3:
						System.out.println("Add Theatre show ()");
						break;
					}
				System.out.println("Do you want to continue? If yes, press 1. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 2:
				System.out.println("Book Movie Tickets");
				tk.bookTicket(user_ID);
				System.out.println("Do you want to continue? If yes, press 1. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 3:
				System.out.println("Cancel Event Tickets");
				System.out.println("Do you want to continue? If yes, press 1. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 4:
				System.out.println("Cancel Movie Tickets");
				System.out.println("Do you want to continue? If yes, press 1. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 5:
				System.out.println("Update Profile");
				System.out.println("Do you want to continue? If yes, press 1. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 6:
				System.out.println("View Upcoming Shows");System.out.println("Do you want to continue? If yes, press 1. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 7:
				System.out.println("End of program");
				flag=0;
				break;
			default:
				System.out.println("Invalid option. Please enter value between 1-7.");		
			}
		}
	}

}
