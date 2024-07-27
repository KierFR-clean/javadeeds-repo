package Resort_Management_System;

public class Calendar {
	
	DateInfo current_DateInfo = new DateInfo();
	private int theYear;
	private int theMonth;
	 private Date_format start, end;

	public Calendar(int theYear) {
		this.theYear = theYear;
	}

	public Date_format getStart() {
		return start;
	}

	public void setStart(Date_format start) {
		this.start = start;
	}

	public Date_format getEnd() {
		return end;
	}

	public void setEnd(Date_format end) {
		this.end = end;
	}
	public void the_calendar(int month, int year, boolean with_marks, char name_ByCharFirst) {
		calendar_format(month, year, with_marks, name_ByCharFirst);
	}

	private void calendar_format(int month, int year, boolean marked_calendar, char name_ByCharFirst) {
		System.out.print("""
				------------------------------------
				|  %s                       ▲
				|    %d                        ▼
				------------------------------------
				| Su | Mo | Tu | We | Th | Fr | Sa |
				------------------------------------
				""".formatted(current_DateInfo.retrieve_StringMonth(month), year));
		int dayOfWeek = day_ofWeekGiven(month, year);
		int totalday_forMonth = ttldays_InMonth(month, year);

		System.out.print("|");
		for (int index = 1; index <= totalday_forMonth + dayOfWeek - 1; index++) {
			if (blank_blockOfDate(index, dayOfWeek)) {
				System.out.print("    |");
			} else {
				if (isDayReserved(month, (index - dayOfWeek + 1)) && marked_calendar) {
					System.out.print(" " + name_ByCharFirst + "  |");
				} else if ((index - dayOfWeek + 1) <= 9) {
					System.out.print("  " + (index - dayOfWeek + 1) + " |");
				} else {
					System.out.print(" " + (index - dayOfWeek + 1) + " |");
				}

			}
			System.out.print((index % 7 == 0) ? "\n------------------------------------\n|" : "");
		}

		System.out.println("\n------------------------------------\n");

	}

	private boolean isDayReserved(int month, int curr_Day) {
		if (start == null && end == null)
			return false;

		if (month < start.month)
			return false;

		if (curr_Day < start.day && month == start.month)
			return false;

		if (curr_Day > end.day && month == end.month)
			return false;

		return true;
	}

	public void printACalendar(int start_month, int year, char name_ByCharFirst) {
		for (int month = start_month; month <= 12; month++) {
			the_calendar(month, year, false, name_ByCharFirst);
		}
	}

	public void print_markedCalendar(int start_month, int end_month, char name_ByCharFirst ) {
		for (int month = start_month; month <= end_month; month++) {
			the_calendar(month, getTheYear(), true, name_ByCharFirst);
		}
	}

	public int days_between(Date_format start_date, Date_format end_date) {
		start = start_date;
		end = end_date;
		int total_duration_days = 0;

		if (start_date.month == end_date.month)
			return end_date.day - start_date.day;

		if (end_date.month == (start_date.month + 1)) {
			for (int index = start_date.day; index < ttldays_InMonth(start_date.month, start_date.year); index++) {
				total_duration_days++;
			}
			return total_duration_days + end_date.day;
		}

		for (int index = start_date.day; index < ttldays_InMonth(start_date.month, start_date.year); index++) {
			total_duration_days++;
		}

		int after_firstmonth = (start_date.month + 1);

		while (after_firstmonth < end_date.month) {
			total_duration_days += ttldays_InMonth(after_firstmonth, start_date.year);
			after_firstmonth++;
		}

		total_duration_days += end_date.day;

		return total_duration_days;

	}

	private boolean blank_blockOfDate(int index, int dayOfWeek) {
		return index < dayOfWeek;
	}

	private boolean isYearALeap(int theYear) {
		return (theYear % 4 == 0 && theYear % 100 != 0) || (theYear % 400 == 0);
	}

	public int getKey(int month) {

		switch (current_DateInfo.retrieve_StringMonth(month)) {
		case "January" -> {
			return isYearALeap(getTheYear()) ? 0 : 1;
		}

		case "February" -> {
			return isYearALeap(getTheYear()) ? 3 : 4;
		}

		case "October" -> {
			return 1;
		}
		case "September", "December" -> {
			return 6;
		}
		case "March", "November" -> {
			return 4;
		}

		case "April", "July" -> {
			return 0;
		}

		case "May" -> {
			return 2;
		}
		case "August" -> {
			return 3;
		}

		case "June" -> {
			return 5;
		}

		}

		return -1;
	}

	public int day_ofWeekGiven(int month, int year) {
		int last_twoDigits_OfWeek = year % 100;
		int one_quarter = last_twoDigits_OfWeek / 4;

		int summate = last_twoDigits_OfWeek + one_quarter + 1 + getKey(month);

		return (summate - 1) % 7;

	}

	public int ttldays_InMonth(int month, int year) {
		switch (month) {
		case 4, 6, 9, 11 -> {
			return 30;
		}
		case 2 -> {
			return isYearALeap(year) ? 29 : 28;
		}
		default -> {
			return 31;
		}

		}
	}

	public int getTheYear() {
		return theYear;
	}

	public int getTheMonth() {
		return theMonth;
	}

}


class DateInfo {
	String[] MONTH_NAMES;
	String[] DAY_NAMES;

	public DateInfo() {
		setMONTH_NAMES();
		setDAY_NAMES();
	}

	public void setMONTH_NAMES() {
		final String[] months = { "", "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" };
		this.MONTH_NAMES = months;
	}

	public void setDAY_NAMES() {
		final String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		this.DAY_NAMES = days;
	}

	public String retrieve_StringMonth(int month_number) {
		for (int index = 0; index < getMONTH_NAMES().length; index++) {
			if (index == month_number)
				return getMONTH_NAMES()[index];
		}
		return "";
	}

	public String[] getMONTH_NAMES() {
		return MONTH_NAMES;
	}

	public String[] getDAY_NAMES() {
		return DAY_NAMES;
	}

}

class Date_format {
	int day, month, year;

	public Date_format(int year, int month, int day) {
		this.month = month;
		this.day = day;
		this.year = year;
	}

	public String toString() {
		return ("""
				-------------------------------
				| Current Day:                |
				-------------------------------
				|           %d/%d/%d
				|
				-------------------------------
				%n""".formatted(day, month, year));
	}

	public String date_byformat() {
		return "" + year + "-" + month + "-" + day;

	}

}
