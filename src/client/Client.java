package client;

import java.util.*;

public class Client {

	static void dummy() {
		System.out.println("Dummy print");
	}
	
	static void exitfn(int flag) {
		if (flag==0)
		System.out.println("Logged out.\n------  END OF PROGRAM  ------");
	}
	
	 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice=0;
		int input=0 ;
		int flag=1;
		Seat s = new Seat();
		Screen s1=new Screen();
//		Theatre t = new Theatre();
		MovieShow ms = new MovieShow();
		
				
		System.out.println("\t\t\t\t---------------  Client  ----------------");
		
		while(flag==1 )
		{
			System.out.println("\nMENU - \n(Choose an option between 1-6) ");
			System.out.println("1. Schedule an Event");
			System.out.println("2. Schedule a Movie");
			System.out.println("3. Modify Theatre Details");
			System.out.println("4. Register Theatre");
			System.out.println("5. Deregister Theatre");
			System.out.println("6. Exit");
			
			input=sc.nextInt();
			sc.nextLine();
			
			switch(input) 
			{
			case 1:
				System.out.println("Enter Event Type");
				System.out.println("1. Add music show");
				System.out.println("2. Add comedy show");
				System.out.println("3. Add theatre show");
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
				System.out.println("Schedule a Movie Show ()");
//				ms.scheduleShow();
				ms.viewShow();
				System.out.println("Do you want to continue? If yes, press 1. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 3:
				System.out.println("Choose an option");
				System.out.println("1. Add screen");
				System.out.println("2. Remove screen");
				choice=sc.nextInt();
					switch (choice)
					{
					case 1:
//						System.out.println("Add Screen()");
						s1.addScreen();
						s.addSeats();
//						dummy();
						break;
					case 2:
						System.out.println("Remove Screen()");
						break;
						
					}	
				System.out.println("Do you want to continue? If yes, press 1. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 4:
				System.out.println("Register a Theatre ()");
				System.out.println("Do you want to continue? If yes, press 1. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 5:
				System.out.println("Deregister a Theatre ()");
				System.out.println("Do you want to continue? If yes, press 1. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 6:
				System.out.println("End of program");
				flag=0;
				break;
			default:
				System.out.println("Invalid option. Please enter value between 1-6.");
			}
		}
		sc.close();
	}

}

