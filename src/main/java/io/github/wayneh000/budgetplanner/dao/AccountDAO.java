package io.github.wayneh000.budgetplanner.dao;

import java.time.LocalDateTime;

import io.github.wayneh000.budgetplanner.entity.Account;

public class AccountDAO {

	private Integer accountId;
	private String username;
	private String password;
	private LocalDateTime dateCreated;
	private LocalDateTime dateLastLogin;
	
	public AccountDAO() {
	}

	public AccountDAO(Integer accountId, String username, String password, LocalDateTime dateCreated,
			LocalDateTime dateLastLogin) {
		this.accountId = accountId;
		this.username = username;
		this.password = password;
		this.dateCreated = dateCreated;
		this.dateLastLogin = dateLastLogin;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "AccountDAO [accountId=" + accountId + ", username=" + username + ", password=" + password
				+ ", dateCreated=" + dateCreated + ", dateLastLogin=" + dateLastLogin + "]";
	}

	public static Account toEntity(AccountDAO accountDAO) {
		return new Account(accountDAO.getAccountId(),
				accountDAO.getUsername(),
				accountDAO.getPassword(),
				accountDAO.getDateCreated(),
				accountDAO.getDateLastLogin());
	}
	
	public static AccountDAO fromEntity(Account account) {
		return new AccountDAO(account.getAccountId(),
				account.getUsername(),
				account.getPassword(),
				account.getDateCreated(),
				account.getDateLastLogin());
	}
}
