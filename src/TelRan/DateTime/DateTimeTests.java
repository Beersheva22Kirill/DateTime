package TelRan.DateTime;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateTimeTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void localDateTest() {
		LocalDate birthDateAs = LocalDate.parse("1799-06-06");
		LocalDate barMizvaAs = birthDateAs.plusYears(13);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMMM YYYY", Locale.forLanguageTag("ru"));
		System.out.println(barMizvaAs.format(dtf));
		assertEquals(barMizvaAs.toString(), "1812-06-06");
		ChronoUnit unit = ChronoUnit.YEARS;
		System.out.printf("Number of %s between %s and %s is %d",unit, birthDateAs, barMizvaAs, unit.between(birthDateAs, barMizvaAs));
		
	}
	
	@Test
	void barMizvaTest() {
		LocalDate current = LocalDate.now();
		assertEquals(current.plusYears(13),current.with(new BarMizvaAdjuster()) );
	}
	
	@Test
	void nextFriday13Test() {
		LocalDate current =  LocalDate.parse("2023-01-13");
		LocalDate nextFriday =  LocalDate.parse("2023-10-13");
		assertEquals(nextFriday,current.with(new NextFriday13()));
	}
	
	
	
	@Test
	void WorkingDaysTest() {
		LocalDate current =  LocalDate.parse("2023-02-15");;	
		DayOfWeek[] dayNotWork = {DayOfWeek.FRIDAY,DayOfWeek.SATURDAY};	
		assertEquals(current.plusDays(6),current.with(new WorkingDays(4, dayNotWork)));
	}
	
	
	//display current localDateTime for all Canada time zones
	// displaying should contains time zone name
	@Test
	void displayCurrentDateTimeInCanadaTest() {
		ZoneId zoneCurrent = null;
		ZonedDateTime timeOfCanada = null;
		Set<ZoneId> zones = new HashSet<>();
		Set<ZoneId> zonesOfCanada = new HashSet<>();
		
		for (String zone: ZoneId.getAvailableZoneIds()) {
			zones.add(zoneCurrent.of(zone)); 
		}			
		zones.stream().filter(z -> z.toString().contains("Canada")).forEach(zonesOfCanada::add);
		System.out.println();
		Iterator<ZoneId> iterator = zonesOfCanada.iterator();
		while (iterator.hasNext()) {
			zoneCurrent = iterator.next();
			System.out.println(timeOfCanada.now(zoneCurrent).toLocalDateTime().format(DateTimeFormatter.ofPattern("YYYY-M-d H:m:s")) + " TimeZone " + zoneCurrent.toString());
		}				
	}

}
