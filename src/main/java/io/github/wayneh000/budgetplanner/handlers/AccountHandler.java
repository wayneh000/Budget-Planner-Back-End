package io.github.wayneh000.budgetplanner.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import io.github.wayneh000.budgetplanner.request.AccountRequest;

@Aspect
@Component
public class AccountHandler {

	private static final Logger LOGGER = LogManager.getLogger();

	@Before("execution(public * io.github.wayneh000.budgetplanner.service.AccountService.createAccount(..))")
	public void createAccountBeforeAdvice(JoinPoint joinPoint) {
		Object request = joinPoint.getArgs()[0];
		LOGGER.info("Creating new account with details: {}", request);
	}
	
	@Before("execution(public * io.github.wayneh000.budgetplanner.service.AccountService.getAccount(..))")
	public void getAccountBeforeAdvice(JoinPoint joinPoint) {
		Object request = joinPoint.getArgs()[0];
		LOGGER.info("Getting account with id {}", request);
	}
	
	@Before("execution(public * io.github.wayneh000.budgetplanner.service.AccountService.getAccounts(..))")
	public void getAccountsBeforeAdvice() {
		LOGGER.info("Getting all accounts from database");
	}
	
	@Before("execution(public * io.github.wayneh000.budgetplanner.service.AccountService.updatePassword(..))")
	public void updatePasswordBeforeAdvice(JoinPoint joinPoint) {
		Object request = joinPoint.getArgs()[0];
		LOGGER.info("Updating password with details {}", request);
	}
}
