package io.github.wayneh000.budgetplanner.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = { "username" }))
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Integer accountId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "date_created")
	private LocalDateTime dateCreated;
	
	@Column(name = "date_last_login")
	private LocalDateTime dateLastLogin;
	
	public Account() {
	}

	public Account(Integer accountId, String username, String password, LocalDateTime dateCreated,
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
		return "Account [accountId=" + accountId + ", username=" + username + ", dateCreated=" + dateCreated
				+ ", dateLastLogin=" + dateLastLogin + "]";
	}
}
