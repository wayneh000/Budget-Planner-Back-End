package io.github.wayneh000.budgetplanner.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "session")
public class Session {

	@Id
	@Column(name = "session_id")
	private String sessionId;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	@Column(name = "expiration_date")
	private LocalDateTime expirationDate;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "Session [sessionId=" + sessionId + ", account=" + account + ", expirationDate=" + expirationDate + "]";
	}
}
