package client;

import java.util.ArrayList;
import java.util.Date;

public class Ticket {
	String ID;
	Date date;
	String name;
	String artists[];
	int numTickets;   
	ArrayList<Seat> seats= new ArrayList<Seat>();  
	
	void generateTicket() {}
	
	void cancelTicket() {}
	
	
}
