package client;

import java.util.*;

public class Client {

	static void exitfn(int flag) {
		if (flag==0)
		System.out.println("Logged out.\n------  END OF PROGRAM  ------");
	}
		 
	void main(int user_ID) {
		Scanner sc = new Scanner(System.in);
		int choice=0;
		int input=0 ;
		int flag=1;
		Screen s1=new Screen();
		Theatre t = new Theatre();
		MovieShow ms = new MovieShow();
		Event e = new Event();
//		Movie m = new Movie();

						
		System.out.println("\t\t\t\t---------------  Client  ----------------");
		
		while(flag==1)
		{
			System.out.println("\nMENU - \n(Choose an option between 1-9) ");
			System.out.println("1. Schedule an Event");
			System.out.println("2. View Events");
			System.out.println("3. Schedule a Movie");
			System.out.println("4. View Movie Shows");
			System.out.println("5. Modify Theatre Screen Details");
			System.out.println("6. Register Theatre");
			System.out.println("7. Deregister Theatre");
			System.out.println("8. View Theatres");
			System.out.println("9. Exit");
			
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
						e.scheduleEvent("Music");
						break;
					case 2:
						e.scheduleEvent("Comedy");
						break;
					case 3:
						e.scheduleEvent("Theatre");
						break;
					}
				System.out.println("Press 1 to go to Home Page. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 2:
				System.out.println("Enter city");
				String city = sc.nextLine();
				System.out.println("Enter Event Type");
				System.out.println("1. Music shows");
				System.out.println("2. Comedy shows");
				System.out.println("3. Theatre shows");
				choice=sc.nextInt();
				switch (choice)
					{
					case 1:
						e.showAllEvents("Music", city);
						break;
					case 2:
						e.showAllEvents("Comedy",city);
						break;
					case 3:
						e.showAllEvents("Theatre",city);
						break;
					}
				System.out.println("Press 1 to go to Home Page. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 3:
				ms.scheduleShow();
				System.out.println("Press 1 to go to Home Page. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 4:
				System.out.println("Enter city");
				String city2 = sc.nextLine();
				ms.viewShow(city2);
				System.out.println("Press 1 to go to Home Page. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 5:
				System.out.println("Choose an option");
				System.out.println("1. Add screen");
				System.out.println("2. Remove screen");
				choice=sc.nextInt();
					switch (choice)
					{
					case 1:
						s1.addScreen();
						break;
					case 2:
						s1.removeScreen();
						break;						
					}	
				System.out.println("Press 1 to go to Home Page. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 6:
				t.registerTheatre();
				System.out.println("Enter screen details - ");
				s1.addScreen();
				System.out.println("Press 1 to go to Home Page. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 7:
				t.deregisterTheatre();
				System.out.println("Press 1 to go to Home Page. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 8:
				t.showTheatre();
				System.out.println("Press 1 to go to Home Page. To exit, press 0 ");
				flag=sc.nextInt();
				exitfn(flag);
				break;
			case 9:
				flag=0;
				exitfn(flag);
				break;
			default:
				System.out.println("Invalid option. Please enter value between 1-9.");
			}
		}
		sc.close();
	}

}

