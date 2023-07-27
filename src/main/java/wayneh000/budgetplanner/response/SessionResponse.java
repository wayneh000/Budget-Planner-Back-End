package wayneh000.budgetplanner.response;

import java.time.LocalDateTime;

public class SessionResponse {

	private String sessionId;
	private AccountResponse accountResponse;
	private LocalDateTime expirationDate;
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public AccountResponse getAccountResponse() {
		return accountResponse;
	}
	
	public void setAccountResponse(AccountResponse accountResponse) {
		this.accountResponse = accountResponse;
	}
	
	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "SessionResponse [sessionId=" + sessionId + ", accountResponse=" + accountResponse + ", expirationDate="
				+ expirationDate + "]";
	}
}
