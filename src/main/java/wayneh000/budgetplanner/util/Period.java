package wayneh000.budgetplanner.util;

public class Period {

	public static final int HOURLY = 0; //special case
	public static final int DAILY = 1;
	public static final int WEEKLY = 7;
	public static final int BIWEEKLY = 14;
	public static final int SEMIMONTHLY = 15;
	public static final int MONTHLY = 30;
	public static final int QUARTERLY = 90;
	public static final int SEMIANNUAL = 180;
	public static final int ANNUAL = 360;
	
	private Period() {
	}
}
