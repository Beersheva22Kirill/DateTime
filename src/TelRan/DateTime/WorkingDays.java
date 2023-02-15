package TelRan.DateTime;

import java.time.DayOfWeek;
import java.time.temporal.*;


public class WorkingDays implements TemporalAdjuster {
	boolean[] dayOfWeek;
	int dayWorking;

	public WorkingDays(int dayWorking, DayOfWeek[] dayNotWorking) {
		dayOfWeek = new boolean[7];	
		for (DayOfWeek day : dayNotWorking) {
			dayOfWeek[day.getValue() - 1] = true;	
		}
		this.dayWorking = dayWorking;
	}
	@Override
	public Temporal adjustInto(Temporal temporal) {
		int dayNotW = 0;
		while(dayWorking > 0) {
			temporal = temporal.plus(1, ChronoUnit.DAYS);	
			if (dayOfWeek[temporal.get(ChronoField.DAY_OF_WEEK) - 1]) {			
				dayNotW++;
			}
			dayWorking--;
		}
		temporal = temporal.plus(dayNotW, ChronoUnit.DAYS);
		return temporal;
	}

}
