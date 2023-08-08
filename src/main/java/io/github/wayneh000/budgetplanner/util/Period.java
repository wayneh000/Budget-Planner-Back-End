package io.github.wayneh000.budgetplanner.util;

import io.github.wayneh000.budgetplanner.exception.BudgetPlannerException;
import io.github.wayneh000.budgetplanner.exception.ErrorMessages;

public class Period {

	public static final Period HOURLY = new Period("Hourly", 0); //special case
	public static final Period DAILY = new Period("Daily", 1);
	public static final Period WEEKLY = new Period("Weekly", 7);
	public static final Period BIWEEKLY = new Period("Biweekly", 14);
	public static final Period SEMIMONTHLY = new Period("Semimonthly", 15);
	public static final Period MONTHLY = new Period("Monthly", 30);
	public static final Period QUARTERLY = new Period("Quarterly", 90);
	public static final Period SEMIANNUAL = new Period("Semiannual", 180);
	public static final Period ANNUAL = new Period("Annual", 360);
	
	public final String name;
	public final int value;
	
	private Period(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public static int getValueFromName(String name) throws BudgetPlannerException {
		name = name.toUpperCase();
		switch (name) {
			case "HOURLY":
				return HOURLY.value;
			case "DAILY":
				return DAILY.value;
			case "WEEKLY":
				return WEEKLY.value;
			case "BIWEEKLY":
				return BIWEEKLY.value;
			case "SEMIMONTHLY":
				return SEMIMONTHLY.value;
			case "MONTHLY":
				return MONTHLY.value;
			case "QUARTERLY":
				return QUARTERLY.value;
			case "SEMIANNUAL":
				return SEMIANNUAL.value;
			case "ANNUAL":
				return ANNUAL.value;
			default:
				throw new BudgetPlannerException(ErrorMessages.PERIOD_INVALID_NAME);
		}
	}
}
