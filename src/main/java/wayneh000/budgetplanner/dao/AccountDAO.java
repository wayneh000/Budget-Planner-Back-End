package wayneh000.budgetplanner.dao;

import java.time.LocalDateTime;

import wayneh000.budgetplanner.entity.Account;
import wayneh000.budgetplanner.response.AccountResponse;

public class AccountDAO {

	private Integer accountId;
	private String username;
	private String password;
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
		Account account = new Account();
		account.setAccountId(accountDAO.getAccountId());
		account.setUsername(accountDAO.getUsername());
		account.setPassword(accountDAO.getPassword());
		account.setDateCreated(accountDAO.getDateCreated());
		account.setDateLastLogin(accountDAO.getDateLastLogin());
		return account;
	}
	
	public static AccountDAO fromEntity(Account account) {
		AccountDAO accountDAO = new AccountDAO();
		accountDAO.setAccountId(account.getAccountId());
		accountDAO.setUsername(account.getUsername());
		accountDAO.setPassword(account.getPassword());
		accountDAO.setDateCreated(account.getDateCreated());
		accountDAO.setDateLastLogin(account.getDateLastLogin());
		return accountDAO;
	}
	
	public static AccountResponse toResponse(AccountDAO accountDAO) {
		AccountResponse response = new AccountResponse();
		response.setAccountId(accountDAO.getAccountId());
		response.setUsername(accountDAO.getUsername());
		response.setDateCreated(accountDAO.getDateCreated());
		response.setDateLastLogin(accountDAO.getDateLastLogin());
		return response;
	}
}
