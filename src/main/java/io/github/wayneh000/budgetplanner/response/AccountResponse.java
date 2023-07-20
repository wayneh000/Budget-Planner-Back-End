package io.github.wayneh000.budgetplanner.response;

import java.time.LocalDateTime;

public class AccountResponse {

	private Integer accountId;
	private String username;
	private LocalDateTime dateCreated;
	private LocalDateTime dateLastLogin;
	
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	public LocalDateTime getDateLastLogin() {
		return dateLastLogin;
	}
	public void setDateLastLogin(LocalDateTime dateLastLogin) {
		this.dateLastLogin = dateLastLogin;
	}
}
