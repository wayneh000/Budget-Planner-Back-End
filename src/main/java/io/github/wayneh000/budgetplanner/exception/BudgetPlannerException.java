package io.github.wayneh000.budgetplanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BudgetPlannerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7671335385040237994L;

	public BudgetPlannerException() {
		super();
	}

	public BudgetPlannerException(String message, Throwable cause) {
		super(message, cause);
	}

	public BudgetPlannerException(String message) {
		super(message);
	}

}
