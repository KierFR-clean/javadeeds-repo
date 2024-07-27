package Resort_Management_System;

import java.util.*;


public class Main {

	private final static Scanner sc = new Scanner(System.in);
	static Calendar calendar= new Calendar(2024);
	static Date_format date_keeper, time_now;
	static String confirmation_room;
	static char first_charOfReservee;
	static SummarySales_Report now_report = new SummarySales_Report();
	

	static double confirmation_price;
	static double report_sales;
	

	public static void main(String[] args) {
		execute(); 
	}
	
	private static void execute() {
		while(true) {
		interface_menu();
		selections();
		}
	}
	
	private static void selections() {
		switch(input("Highly recommend to often choose reservation tab first....")) {
		case 1 -> {
			reservation();
		}
		case 2 -> {
			if (now_report.firstSales == null) {
				System.out.println(" \' Empty database \' ");
			} else {
				int book_response;
				do {
				System.out.println("""
									-------------------------------------
									|                                   |
									|           Calendar Tab            |
									|                          yr: %d      
									-------------------------------------
									| [1] Print Year's Calendar
									| [2] Find A Guess Reservation Sched
									-------------------------------------
									|                       [3] Go Back | 
									-------------------------------------
									""".formatted(calendar.getTheYear()));
				
				 book_response = input("Must follow commands stated at the above details: ");
				
				switch(book_response) {
				case 1 -> {
					Calendar sample = new Calendar(2024);
					sample.printACalendar(1, calendar.getTheYear(), first_charOfReservee);
				}
				case 2 -> {
				
					now_report.guess_code();
					int found_code = input("Provide the guess_code\nThere's a reference list there above: ");
					Accomodation temp = now_report.find(found_code);
					if (temp == null) 	System.out.println("---Can't find guess_code---");
					else temp.reservedDetails.print_markedCalendar(temp.reservedDetails.getStart().month, temp.reservedDetails.getEnd().month, first_charOfReservee);
					
				}
					
				}
				} while (book_response != 3);
				
				                
			}
			
		}
		case 3 -> {
			if (now_report.firstSales == null) {
				System.out.println(" \' Empty database \' ");
			} else {
				System.out.println("""
						    		---------------------------------
						    		|  Report Sales Tab         | X |
						    		---------------------------------
						    	
									""");
				now_report.print_reservees();
				now_report.price_summary();
				
			}
			
		}
	  }
	}
	
	
	private static void reservation() {
	
		String name =  input("Reservation Policies...\nIs to leave the representative name first"
				+ "[Kindly provide the representative name]: ", false);
		
		print("To clarify...\n" + "Reservee name is " + name);
		
		int size = input("How many accomodation guests included with the reservation");
		
		Person newly_added = new Person(name, size);
		first_charOfReservee = newly_added.first_chacInReserveeName();
		
		print("Can you say each names of the said guests? ");
		newly_added.add();
		
		print(newly_added.toString());
		
		print("Done reserving guess.....\nOpening Accomdation selection....\n\tDone!");
		
		accomodation_panel();
		while (true) {
		convert_date_ltrInt("Please input the start date...\n"
				+ "Comply with the ff date format YYYY-MM-DD [Reminder: Add \'-\' ~ eg, 2024-01-10 ] :", false);
		
		Date_format start_date = date_keeper;
		
		print("Clarifying...\n." + start_date);
		
		convert_date_ltrInt("Please input the end date...\n"
				+ "Comply with the ff date format YYYY-MM-DD [Reminder: Add \'-\' ~ eg, 2024-01-10 ] :", false);
		
		Date_format end_date = date_keeper;
		
		print("Clarifying...\n" + end_date);
		
		if (validate_dates(start_date, end_date) && !now_report.reserved_already(start_date, end_date)) {
			
			alloting_reservation_date(newly_added, start_date, end_date);
			break;
		} else {
			System.out.println("""
							------------------------------------------------
							| Invalid Date Given:                      | X |
							------------------------------------------------                       
							| ~ Possible Errors:	                       |
							| ~ start date lesser than now date.          |
							| ~ each year should be only in 2024.          |
							| ~ end date is earlier than start date.       |
							| ~ both inputted dates are reserved           |
							------------------------------------------------
								""");
			
		}
		
		}
		
	}

	private static void accomodation_panel() {
		accomodation_interface("o Room \n|  o Suite  \n|  o Villa  ");
		TYPE_def user_prefferedRoom = TYPE_def.ROOM;
		int selection = -1, confirmation_certainty = -1;
		
		while (confirmation_certainty != 0) {
			selection =  input("Follow the commands upon selecting execution...");
		switch(selection) {
		case 1 -> {
			user_prefferedRoom = TYPE_def.ROOM;
			accomodation_interface("""
					   o Room      |
					----------------
					|  o Amenities |
					----------------------------------------------------
					|  Internet Connectivity, Basic Hygiene Souvenier  |
					----------------------------------------------------
					|  o Price     |
					----------------
					| 4,568.00 php  |
					""");
			
			
		}
		
		case 2 -> {
			user_prefferedRoom = TYPE_def.SUITE;

			accomodation_interface("""
					   o Suite     |
					----------------
					|  o Amenities |
					-----------------------------------------------------
					|  Internet Connectivity, Grande Hygiene Souvenier, |
					|  Deluxe Balcony , Latest Bathtub                  |
					-----------------------------------------------------
					|  o Price     |
					------------------
					| 23,569.00 php  |
					""");
		}
	
		case 3 -> {
			user_prefferedRoom = TYPE_def.VILLA;
			accomodation_interface("""
					   o Villa     |
					----------------
					|  o Amenities |
					----------------------------------------------------------------
					|  Internet Connectivity, Grande Hygiene Souvenier,            |
					|  Deluxe Balcony, Latest Bathtub, California King Sized Bed,  |
					|  13-0' x 14-0''.4m x 4.3m Kitchen                            |
					----------------------------------------------------------------
					|  o Price     |
					------------------
					| 29,789.00 php  |
					""");
			}
		
		case 0 -> {
			
			System.out.println("""
							------------------------------------------
							|  Are you sure?                     | X |
							------------------------------------------
							| 1 of 3 Booking Step Details...         |
							|-----------------------------------------
							|  %s
							|  
							|         Accomodation Fee:  %.2f php
							------------------------------------------
							|                         [1] Yes [2] No |
							------------------------------------------
						    """.formatted(user_prefferedRoom.toString(), user_prefferedRoom.market_price));
			int confirm = input("...");
							
			switch (confirm) {
			case 2 -> {
				print("Please wait....");
				confirmation_certainty = -1;
			}
			case 1 -> {
				print("1 of 3 Booking Step Done...\nOpening the system's calendar...\nDone ");
				 
				convert_date_ltrInt("Please input the date of inquiring...\n"
						+ "Comply with the ff date format YYYY-MM-DD [Reminder: Add \'-\' ~ eg, 2024-01-10 ] :", true);
				confirmation_room = user_prefferedRoom.toString();
				confirmation_price = user_prefferedRoom.market_price;
				confirmation_certainty = 0;
				break;
				
				
				
				
			}
			default -> {

			}

			}
		}

		}

	}
}
	protected static boolean validate_dates(Date_format reference, Date_format date_toValidate) {
		
		if (date_toValidate.year != calendar.getTheYear() || reference.year != calendar.getTheYear())  return false;
		
		if (reference.month < time_now.month || date_toValidate.month < time_now.month) return false;

		if (reference.month > 12 || date_toValidate.month > 12 ||
				reference.day > calendar.ttldays_InMonth(reference.month, calendar.getTheYear()) || date_toValidate.day > calendar.ttldays_InMonth(date_toValidate.month, calendar.getTheYear())) return false;
		
		if (reference.month > date_toValidate.month) return false;
	
		return true;
		
	}

	protected static void convert_date_ltrInt(String msg, boolean show_calendar) throws NumberFormatException {
		String theMonth_IntegerRep = input(msg , false);
		
		String[] convert = theMonth_IntegerRep.split("-");
		
		int year = Integer.parseInt(convert[0]),
		 month = Integer.parseInt(convert[1]),
		day = Integer.parseInt(convert[2]);
		
		
		if (show_calendar) {
			time_now = new Date_format(year, month, day);
			systemCalendar(day, month, year);
		} else {
			date_keeper = new Date_format(year, month, day);
		}
	}
	
	private static void systemCalendar(int day, int month, int year) {
		Date_format date_now =  new Date_format(year, month, day);
		System.out.println(date_now);
		calendar.printACalendar(month,calendar.getTheYear(), first_charOfReservee );
	}
	
	private static void alloting_reservation_date(Person reservee, Date_format start_date, Date_format end_date) {
		
		System.out.println("""
				            -------------------------------------------
				            | 3 of 3 Booking Step Process....        
				            | Confirmation Details                 
				            |------------------------------------------
				            | 
				            |  %s
				            |      %.2f             
				            |             
				            |    %s-%s                  
				            |              
				            |                
				            | Days of Staying:
				            |       %d
				            |
				            |__________________________________________
				            |        Reservation Fee:      
				            |                      %.2f         
				            |                      [1] Pay & Confirm        
				            -------------------------------------------
							""".formatted(confirmation_room,confirmation_price,start_date.date_byformat(),end_date.date_byformat(),
									calendar.days_between(start_date, end_date), (confirmation_price * calendar.days_between(start_date, end_date))));
		     		int last_confirmation = input("");
		     		
		     		
		     		
		     		switch(last_confirmation) {
		     		case 1 -> {
		     			double pay = 0, change = 0;
		     			report_sales = (confirmation_price * calendar.days_between(start_date, end_date));
		     			change = change(change, pay);
						if (pay != Math.round(report_sales)) {
		     				print("Here's your change sir...\n\t" + Math.abs(change));
		     			}
		     			
		     			now_report(reservee, start_date, end_date);
						print("Please wait...\nSuccessfully Booked In, Have a Great Stay-In!!!...");
					}
					}

				}

	private static void now_report(Main.Person reservee, Date_format start_date, Date_format end_date) {
		Calendar reserved_now = new Calendar(2024);
		now_report.append(reservee, confirmation_room, confirmation_price, report_sales, start_date, end_date,
				reserved_now);
	}

	private static double pay(double pay) {
		do {
			pay = input("Please pay amount of " + Math.round(report_sales) + "\nSimply type in the amount ");
		} while (pay < Math.round(report_sales));
		return pay;
	}

	private static double change(double change, double pay) {
		if (pay != Math.round(report_sales)) {
			change = (Math.round(report_sales) - pay);
		}
		return change;
	}
	
	
	private static void accomodation_interface(String lblprompt)  {
		System.out.println("""
							-----------------------------------------------------------
							|  Accomodation Listing                               | X |
							-----------------------------------------------------------
							|  %s
							-----------------------------------------------------------
							|             |[1] Room |[2] Suite |[3] Villa |[0]Confirm |                                
							-----------------------------------------------------------
							%n""".formatted( lblprompt));
	}
	
	private static void interface_menu() {

		System.out.println("""
					  ------------------------------------
							| Hotel Reservation System     | X |
							------------------------------------
							| Please select from the following | 
							------------------------------------
							|[1] Reservation Tab               |
							|[2] Booking Calendar              |
							|[3] Report Tab                    |
							|[4] Exit System                   |
							------------------------------------
							""");
	}
	
	private static int input(String promptString) {
		System.out.println(promptString);
		while (!sc.hasNextInt()) {
			print("You're trying to input data type that is not integer...\nYou may try again: ");
			sc.next();
		}
		int data = sc.nextInt();
		return (data >= 0)? data : input("You're trying to input negative number...\nResolve it by inputting only whole number {eg. 1, 2, 3, 4}: ");
	}
	
	
	private static String input(String promptString, boolean tobeNewline) {
		System.out.println(promptString);
		return (tobeNewline)? sc.nextLine(): sc.next();
	}
	
	private static void print(String promptString) {
		System.out.println(promptString + "\n");
	}
	
	
	static class Person {
		int guest_code;
		String representative;
		String[] guests;
		
		Person(String representative, int guess_size) {
			this.representative = representative;
			guests = new String[guess_size];
			guest_code = (int) (Math.random() * 10000 - 1000 + 1) + 1000;
		}
		
		protected char first_chacInReserveeName() {
			return this.representative.charAt(0);
		}
		
		private void add(){
			for (int i = 0; i < guests.length; i++) {
				guests[i] = input("Okay..." + "inputted the number " + (i + 1) + " guest; Next...", false);
			}
		}
		
		private String guest_list() {
			String concat = "";
			int guide = 0;
			for(String guest : guests) {
				if ((guide % 3) == 0) {
					concat += "\n|   ";
				}
				concat += guest + ",";
				guide++;
			}
			return concat;
		}
		
		public String toString() {
			return """
					----------------------------------------------------------------------------
					|     	Guest #%d       |                                                 
					----------------------------------------------------------------------------
					|     Representative     |    %s                                   		   
					----------------------------------------------------------------------------                                    
					|         Guests         |                                    
					--------------------------
					|  %s 
					""".formatted(guest_code,  representative, guest_list());
		}
	}
	
	static class SummarySales_Report {
		Accomodation firstSales, lastSales;
		
		public SummarySales_Report() {
			this.firstSales = this.lastSales = null;
		}
		
		private boolean empty() {
			return this.firstSales == null;
		}
		
		public void append(Person reservee, String chosen_room, double market_price,double max_price, Date_format start_date, Date_format end_date, Calendar now_booked ) {
			Accomodation newly_added = new Accomodation(reservee, chosen_room, market_price, max_price, start_date, end_date, now_booked);
			if (!empty()) {
				lastSales.next_customer = newly_added;
				lastSales = newly_added;
				return;
			}
			firstSales = newly_added;
			lastSales = newly_added;
			return;
		}
		
		public void print_reservees() {
			this.recursive_byprint(firstSales);
		}
		
		private void recursive_byprint(Accomodation start) {
			if (start != null) {
				System.out.println(start + "\n");
				recursive_byprint(start.next_customer);
			}
			System.out.println("_________________End of Summary Report__________________");
		}
		
		public void price_summary() {
			System.out.println("""
								---------------------------------------
								|  Current Summary Sales              |
								|              2024                   |
								---------------------------------------      
								|   %.2f                              |
								---------------------------------------
								""".formatted(summary_price(firstSales)));
		}
		
		public Accomodation find(int code) {
			Accomodation found_it = findReservedDetails(firstSales, code);
			return (found_it == null) ? null : found_it;
		}
		
		
		private Accomodation findReservedDetails(Accomodation start, int code) {
			if (start != null) {
				if (start.reservee.guest_code == code) return start;
				findReservedDetails(start.next_customer, code);
			}
			return null;
			
		}
		
		protected boolean reserved_already(Date_format start_date, Date_format end_date) {
			Accomodation temp = firstSales;
			while (temp != null) {
				if (temp.reservedDetails.getStart() == start_date || temp.reservedDetails.getEnd() == end_date) return true;
				
			}
			return false;
		}
		
		public void guess_code() {
			guess_codeByList(firstSales, 1);
		}
		
		private void guess_codeByList(Accomodation start, int count) {
			if (start != null) {
				System.out.println("["+ count + "]. " + start.reservee.guest_code);
				guess_codeByList(start.next_customer, count + 1);
			}
			System.out.println("---End of Records---");
		}
		
		private double summary_price(Accomodation start) {
			if (start != null) {
					return summary_price(start.next_customer) + start.max_price;
			}
			return 0.00;
		}
		
		
	}
	
	
	static class Accomodation {
		Person reservee;
		String chosen_room;
		double market_price, max_price;
		Date_format start_date, end_date;
		Calendar reservedDetails;
		Accomodation next_customer;
		
		
		public Accomodation(Person reservee, String chosen_room, double market_price,double max_price, Date_format start_date, Date_format end_date, Calendar reservedDetails) {
			this.reservee = reservee;
			this.chosen_room = chosen_room;
			this.max_price = max_price;
			this.market_price = market_price;
			this.start_date = start_date;
			this.end_date = end_date;
			this.reservedDetails = new Calendar(2024);
			this.reservedDetails.setStart(start_date);
			this.reservedDetails.setEnd(end_date);
			this.reservedDetails.print_markedCalendar(start_date.month, end_date.month, first_charOfReservee);

		}
		
		public String toString() {
			return """
					-----------------------------------------------------
					| Guess: %d
					-----------------------------------------------------
					| Reservation made by: %s           %s
					|                                       %.2f
					| Starting Date ~ Ending Date
					|             %s - %s
					|____________________________________________________
					|                  Reservation Fee:  %.2f Php
					|____________________________________________________
					
					%n""".formatted(reservee.guest_code, reservee.representative, chosen_room
							, market_price,start_date.date_byformat(), end_date.date_byformat(), max_price);
		}
	}
	
	
	
	enum TYPE_def {
		
		ROOM(4568.00),
		SUITE(23569.00),
		VILLA(29789.00);
		
		double market_price;
		
		TYPE_def(double market_price) {
			this.market_price = market_price;
		}
		
	}

}
