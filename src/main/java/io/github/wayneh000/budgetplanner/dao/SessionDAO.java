package io.github.wayneh000.budgetplanner.dao;

import java.time.LocalDateTime;

import io.github.wayneh000.budgetplanner.entity.Session;
import io.github.wayneh000.budgetplanner.response.SessionResponse;

public class SessionDAO {

	private String sessionId;
	private AccountDAO accountDAO;
	private LocalDateTime expirationDate;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public AccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "SessionDAO [sessionId=" + sessionId + ", accountDAO=" + accountDAO + ", expirationDate=" + expirationDate + "]";
	}
	
	public static Session toEntity(SessionDAO sessionDAO) {
		Session session = new Session();
		session.setSessionId(sessionDAO.getSessionId());
		session.setAccount(AccountDAO.toEntity(sessionDAO.getAccountDAO()));
		session.setExpirationDate(sessionDAO.getExpirationDate());
		return session;
	}
	
	public static SessionDAO fromEntity(Session session) {
		SessionDAO sessionDAO = new SessionDAO();
		sessionDAO.setSessionId(session.getSessionId());
		sessionDAO.setAccountDAO(AccountDAO.fromEntity(session.getAccount()));
		sessionDAO.setExpirationDate(session.getExpirationDate());
		return sessionDAO;
	}
}
