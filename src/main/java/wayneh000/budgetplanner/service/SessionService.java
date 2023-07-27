package wayneh000.budgetplanner.service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wayneh000.budgetplanner.dao.AccountDAO;
import wayneh000.budgetplanner.dao.SessionDAO;
import wayneh000.budgetplanner.entity.Session;
import wayneh000.budgetplanner.exception.BudgetPlannerException;
import wayneh000.budgetplanner.repository.SessionRepository;
import wayneh000.budgetplanner.response.SessionResponse;

@Service
public class SessionService {
	
	@Autowired
	private SessionRepository sessionRepository;
	
	private Random rng;
	
	public SessionService() {
		rng = new Random();
	}
	
	public SessionResponse createSession(AccountDAO accountDAO) {
		SessionDAO sessionDAO = new SessionDAO();
		sessionDAO.setAccountDAO(accountDAO);
		renewExpirationDate(sessionDAO);
		Session session;
		do {
			sessionDAO.setSessionId(generateId());
			session = sessionRepository.findById(sessionDAO.getSessionId()).orElse(null);
		} while (session != null);
		sessionRepository.save(SessionDAO.toEntity(sessionDAO));
		
		return SessionDAO.toResponse(sessionDAO);
	}
	
	public SessionDAO verifySession(String sessionId) throws BudgetPlannerException {
		Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new BudgetPlannerException("SessionService.SESSION_NOT_FOUND"));
		if (session.getExpirationDate().isAfter(LocalDateTime.now())) {
			deleteSession(sessionId);
			throw new BudgetPlannerException("SessionService.SESSION_EXPIRED");
		}
		
		return SessionDAO.fromEntity(session);
	}
	
	public SessionResponse updateSession(String sessionId) throws BudgetPlannerException {
		SessionDAO sessionDAO = verifySession(sessionId);
		renewExpirationDate(sessionDAO);
		sessionRepository.save(SessionDAO.toEntity(sessionDAO));
		
		return SessionDAO.toResponse(sessionDAO);
	}
	
	public SessionResponse deleteSession(String sessionId) throws BudgetPlannerException {
		Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new BudgetPlannerException("SessionService.SESSION_NOT_FOUND"));
		sessionRepository.deleteById(sessionId);
		
		return SessionDAO.toResponse(SessionDAO.fromEntity(session));
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
