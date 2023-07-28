package io.github.wayneh000.budgetplanner.exception;

public class ErrorMessages {

	private ErrorMessages() {}
	
	//Generic Errors
	public static final String GENERIC = "Some other error has occurred.";
	
	//AccountService Errors
	public static final String ACCOUNT_ALREADY_EXISTS = "An account with that username already exists.";
	public static final String ACCOUNT_NOT_FOUND = "An account with that username does not exist.";
	public static final String ACCOUNT_INVALID_CREDENTIALS = "Invalid username or password.";
}
