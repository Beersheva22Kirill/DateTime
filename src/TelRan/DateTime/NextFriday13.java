package TelRan.DateTime;

import java.time.temporal.*;



public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		temporal = temporal.plus(1, ChronoUnit.DAYS);	
		while (temporal.get(ChronoField.DAY_OF_WEEK) != 5 || temporal.get(ChronoField.DAY_OF_MONTH) != 13) {
				if (temporal.get(ChronoField.DAY_OF_MONTH) > 13) {
					temporal = temporal.plus(1, ChronoUnit.MONTHS);
					temporal = temporal.minus(temporal.get(ChronoField.DAY_OF_MONTH) - 1, ChronoUnit.DAYS);	
				} else {
					temporal = temporal.plus(1,ChronoUnit.DAYS);
				}	
		}

		return temporal;
	}

}
