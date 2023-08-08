package io.github.wayneh000.budgetplanner.util;

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
}
