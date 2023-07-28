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
import io.github.wayneh000.budgetplanner.exception.ErrorMessages;
import io.github.wayneh000.budgetplanner.repository.SessionRepository;
import io.github.wayneh000.budgetplanner.request.AccountRequest;

@Service
public class SessionService {

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private AccountService accountService;

	private Random rng;

	public SessionService() {
		rng = new Random();
	}

	public SessionDAO createSession(AccountRequest request) throws BudgetPlannerException {
		AccountDAO accountDAO = accountService.verifyLogin(request.getUsername(), request.getPassword());

		SessionDAO sessionDAO = new SessionDAO();
		sessionDAO.setAccountDAO(accountDAO);
		renewExpirationDate(sessionDAO);
		Session session;
		do {
			sessionDAO.setSessionId(generateId());
			session = sessionRepository.findById(sessionDAO.getSessionId()).orElse(null);
		} while (session != null);
		sessionRepository.save(SessionDAO.toEntity(sessionDAO));
		accountService.updateLoginDate(accountDAO);

		return sessionDAO;
	}

	public SessionDAO verifySession(String sessionId) throws BudgetPlannerException {
		Session session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new BudgetPlannerException(ErrorMessages.SESSION_NOT_FOUND));
		if (session.getExpirationDate().isAfter(LocalDateTime.now())) {
			deleteSession(sessionId);
			throw new BudgetPlannerException(ErrorMessages.SESSION_EXPIRED);
		}

		return updateSession(SessionDAO.fromEntity(session));
	}

	SessionDAO updateSession(SessionDAO sessionDAO) throws BudgetPlannerException {
		renewExpirationDate(sessionDAO);
		sessionRepository.save(SessionDAO.toEntity(sessionDAO));

		return sessionDAO;
	}

	SessionDAO deleteSession(String sessionId) throws BudgetPlannerException {
		Session session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new BudgetPlannerException(ErrorMessages.SESSION_NOT_FOUND));
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
