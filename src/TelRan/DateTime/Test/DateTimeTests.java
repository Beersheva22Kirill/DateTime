package TelRan.DateTime.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

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
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM,YYYY,d", Locale.CANADA);
		System.out.println(barMizvaAs.format(dtf));
		assertEquals(barMizvaAs.toString(), "1812-06-06");
		ChronoUnit unit = ChronoUnit.YEARS;
		System.out.printf("Number of %s between %s and %s is %d",unit, birthDateAs, barMizvaAs, unit.between(birthDateAs, barMizvaAs));
		
	}

}
