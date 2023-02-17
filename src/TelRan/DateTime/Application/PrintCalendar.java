package TelRan.DateTime.Application;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.IntStream;

public class PrintCalendar {

	private static final String LANGUAGE_TAG = "en";
	private static final int YEAR_OFFSET = 10;
	private static final int WIDTH_FIELD = 4;
	private static Locale locale = Locale.forLanguageTag(LANGUAGE_TAG);

	public static void main(String[] args) {
		
		try {
			int[] monthYearFirstDay = getMonthYearFirstDay(args);
			printCalendar(monthYearFirstDay[0], monthYearFirstDay[1],monthYearFirstDay[2]);
		} catch (Exception e) {	
			System.out.println(e.getMessage());
		}

	}

	private static void printCalendar(int month, int year, int firstDay) {
		printTitle(month,year);
		printWeekDays(firstDay);
		printDays(month,year,firstDay);	
	}

	private static void printDays(int month, int year, int firstDay) {
		System.out.println();
		int weekDayNumber = getFirstDay(month, year, firstDay);
		int offset = getOffset(weekDayNumber);
		
		int nDays = YearMonth.of(year, month).lengthOfMonth();
		System.out.printf("%s", " ".repeat(offset));
		for (int date = 1; date <= nDays ; date++) {
			System.out.printf("%4d", date);
			if (++weekDayNumber > 7) {
				System.out.println();
			    weekDayNumber = 1;
			}		
		}
		
	}

	private static int getOffset(int weekDayNumber) {
		
		return (weekDayNumber - 1) * WIDTH_FIELD;
	}

	private static int getFirstDay(int month, int year, int firstDay) {
		int res = LocalDate.of(year, month, 1).getDayOfWeek().getValue() + (DayOfWeek.values().length - firstDay);
		if (res > DayOfWeek.values().length) {
			res -= DayOfWeek.values().length;
		}
		return res;
	}

	private static void printWeekDays(int firstDay) {
		System.out.print("  ");
		DayOfWeek[] days = DayOfWeek.values();
		int j = 0;
		for (int i = firstDay; j < days.length; j++) {
			System.out.printf("%s ", days[i].getDisplayName(TextStyle.SHORT, locale));
			i++;
			if(i > days.length - 1) {
				i = 0;
			}
		}
	}

	private static void printTitle(int month, int year) {
		
		System.out.printf("%s%d, %s\n"," ".repeat(YEAR_OFFSET), year, Month.of(month).getDisplayName(TextStyle.FULL, locale));	
	}

	private static int[] getMonthYearFirstDay(String[] args) throws Exception {
		
		return args.length == 0 ? getCurrentMonthYearFirstDay() : getMonthYearFirstDayArgs(args);
	}

	private static int[] getCurrentMonthYearFirstDay() {
		
		LocalDate current = LocalDate.now();
		return new int[] {current.getMonth().getValue(), current.getYear(), DayOfWeek.MONDAY.getValue() - 1};
	}

	private static int[] getMonthYearFirstDayArgs(String[] args) throws Exception{
		
		return new int[] {getMonthArgs(args), getYearArgs(args), getFirsDayArgs(args)};
	}

	private static int getFirsDayArgs(String[] args) throws Exception {
		int res = DayOfWeek.MONDAY.getValue() - 1;
		if (args.length > 2) {
			try {
				res = DayOfWeek.valueOf(args[2].toUpperCase()).getValue() - 1;
			} catch (Exception e) {
				throw new Exception("FirstDay mast be a MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY (case insensitive) ");
			}
		}
		return res;
	}

	private static int getYearArgs(String[] args) throws Exception{
		int res = LocalDate.now().getYear();
		if (args.length > 1) {
			try {
				res = Integer.parseInt(args[1]);
				if (res < 0) {
					throw new Exception("year mast be a positive number");
				}
			} catch (NumberFormatException e) {
				throw new Exception("year mast be a number");
			}
		}
		return res;
	}

	private static int getMonthArgs(String[] args) throws Exception{
			try {
				int res = Integer.parseInt(args[0]);
				if (res < 1 || res > 12) {
					throw new Exception("month should be a number in the range 1 - 12");
				}
				return res;
			} catch (NumberFormatException e) {
				throw new Exception("month should be a number");
			}		
	}

}
