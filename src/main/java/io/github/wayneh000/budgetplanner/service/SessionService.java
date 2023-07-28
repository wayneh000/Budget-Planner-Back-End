package io.github.wayneh000.budgetplanner.service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.wayneh000.budgetplanner.dao.AccountDAO;
import io.github.wayneh000.budgetplanner.dao.SessionDAO;
import io.github.wayneh000.budgetplanner.entity.Session;
import io.github.wayneh000.budgetplanner.exception.BudgetPlannerException;
import io.github.wayneh000.budgetplanner.repository.SessionRepository;

@Service
public class SessionService {
	
	@Autowired
	private SessionRepository sessionRepository;
	
	private AccountService accountService;
	private Random rng;
	
	public SessionService() {
		accountService = new AccountService();
		rng = new Random();
	}
	
	public SessionDAO createSession(AccountDAO accountDAO) throws BudgetPlannerException {
		accountService.verifyLogin(accountDAO.getUsername(), accountDAO.getPassword());
		
		SessionDAO sessionDAO = new SessionDAO();
		sessionDAO.setAccountDAO(accountDAO);
		renewExpirationDate(sessionDAO);
		Session session;
		do {
			sessionDAO.setSessionId(generateId());
			session = sessionRepository.findById(sessionDAO.getSessionId()).orElse(null);
		} while (session != null);
		sessionRepository.save(SessionDAO.toEntity(sessionDAO));
		
		return sessionDAO;
	}
	
	public SessionDAO verifySession(String sessionId) throws BudgetPlannerException {
		Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new BudgetPlannerException("SessionService.SESSION_NOT_FOUND"));
		if (session.getExpirationDate().isAfter(LocalDateTime.now())) {
			deleteSession(sessionId);
			throw new BudgetPlannerException("SessionService.SESSION_EXPIRED");
		}
		
		return SessionDAO.fromEntity(session);
	}
	
	public SessionDAO updateSession(String sessionId) throws BudgetPlannerException {
		SessionDAO sessionDAO = verifySession(sessionId);
		renewExpirationDate(sessionDAO);
		sessionRepository.save(SessionDAO.toEntity(sessionDAO));
		
		return sessionDAO;
	}
	
	public SessionDAO deleteSession(String sessionId) throws BudgetPlannerException {
		Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new BudgetPlannerException("SessionService.SESSION_NOT_FOUND"));
		sessionRepository.deleteById(sessionId);
		
		return SessionDAO.fromEntity(session);
	}
	
	private void renewExpirationDate(SessionDAO sessionDAO) {
		sessionDAO.setExpirationDate(LocalDateTime.now().plusMinutes(30));
	}
	
	private String generateId() {
		byte[] bytes = new byte[48];
		rng.nextBytes(bytes);
		return Base64.getUrlEncoder().encodeToString(bytes);
	}
}
