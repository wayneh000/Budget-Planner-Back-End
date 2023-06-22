package io.github.wayneh000.budgetplanner.dao;

import java.time.LocalDateTime;

import io.github.wayneh000.budgetplanner.entity.Session;

public class SessionDAO {

	private String sessionId;
	private AccountDAO accountDAO;
	private String ipAddress;
	private LocalDateTime expirationDate;
	
	public SessionDAO() {
	}

	public SessionDAO(String sessionId, AccountDAO accountDAO, String ipAddress, LocalDateTime expirationDate) {
		this.sessionId = sessionId;
		this.accountDAO = accountDAO;
		this.ipAddress = ipAddress;
		this.expirationDate = expirationDate;
	}

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

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "SessionDAO [sessionId=" + sessionId + ", accountDAO=" + accountDAO + ", ipAddress=" + ipAddress
				+ ", expirationDate=" + expirationDate + "]";
	}
	
	public static Session toEntity(SessionDAO sessionDAO) {
		return new Session(sessionDAO.getSessionId(),
				AccountDAO.toEntity(sessionDAO.getAccountDAO()),
				sessionDAO.getIpAddress(),
				sessionDAO.getExpirationDate());
	}
	
	public static SessionDAO fromEntity(Session session) {
		return new SessionDAO(session.getSessionId(),
				AccountDAO.fromEntity(session.getAccount()),
				session.getIpAddress(),
				session.getExpirationDate());
	}
}
