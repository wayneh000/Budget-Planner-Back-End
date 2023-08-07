package io.github.wayneh000.budgetplanner.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SessionHandler {

	private static final Logger LOGGER = LogManager.getLogger();
	
	@Before("execution(public * io.github.wayneh000.budgetplanner.service.SessionService.createSession(..))")
	public void createSessionBeforeAdvice(JoinPoint joinPoint) {
		Object account = joinPoint.getArgs()[0];
		LOGGER.info("Creating new session for account: {}", account);
	}
	
	@Before("execution(public * io.github.wayneh000.budgetplanner.service.SessionService.verifySession(..))")
	public void verifySessionBeforeAdvice(JoinPoint joinPoint) {
		Object sessionId = joinPoint.getArgs()[0];
		LOGGER.info("Attempting to verify session ID {}", sessionId);
	}
}
