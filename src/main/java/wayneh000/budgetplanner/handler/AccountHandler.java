package wayneh000.budgetplanner.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import wayneh000.budgetplanner.request.AccountRequest;

@Aspect
@Component
public class AccountHandler {
	
	private static Logger LOGGER = LogManager.getLogger();

	@Before("execution(* io.github.wayneh000.budgetplanner.service.AccountService.createAccount(..))")
	public void createAccountBeforeAdvice(JoinPoint joinPoint) {
		LOGGER.info("Creating account with details: ");
		AccountRequest request = (AccountRequest) joinPoint.getArgs()[0];
		LOGGER.info(request);
	}
}
