package io.github.wayneh000.budgetplanner.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.wayneh000.budgetplanner.dao.AccountDAO;
import io.github.wayneh000.budgetplanner.dao.SessionDAO;
import io.github.wayneh000.budgetplanner.repository.AuthenticationRepository;

@Service
public class AuthenticationService {
	
	private static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	private AuthenticationRepository authenticationRepository;
	
	public AccountDAO createAccount(String username, String password) {
		return null;
	}
	
	public AccountDAO verifyLogin(String username, String password) {
		return null;
	}
	
	public AccountDAO updatePassword(AccountDAO accountDAO, String newPassword) {
		return null;
	}
	
	public SessionDAO createSession(AccountDAO accountDAO) {
		return null;
	}
	
	public SessionDAO verifySession(SessionDAO sessionDAO) {
		return null;
	}
	
	public SessionDAO updateSession(SessionDAO sessionDAO) {
		return null;
	}
	
	public SessionDAO deleteSession(SessionDAO sessionDAO) {
		return null;
	}
}
