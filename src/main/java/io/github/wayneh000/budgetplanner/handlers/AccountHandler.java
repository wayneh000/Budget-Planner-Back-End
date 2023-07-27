package io.github.wayneh000.budgetplanner.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import io.github.wayneh000.budgetplanner.request.AccountRequest;

@Aspect
@Component
public class AccountHandler {
	
	private static final Logger LOGGER = LogManager.getLogger();

	@Before("execution(* io.github.wayneh000.budgetplanner.service.AccountService.createAccount(..))")
	public void createAccountBeforeAdvice(JoinPoint joinPoint) {
		LOGGER.info("Creating account with details: ");
		AccountRequest request = (AccountRequest) joinPoint.getArgs()[0];
		LOGGER.info(request);
	}
}
