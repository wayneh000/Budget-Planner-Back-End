package io.github.wayneh000.budgetplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.wayneh000.budgetplanner.dao.SessionDAO;
import io.github.wayneh000.budgetplanner.request.AccountRequest;
import io.github.wayneh000.budgetplanner.response.SessionResponse;
import io.github.wayneh000.budgetplanner.service.SessionService;

@RestController
@RequestMapping("api/v1/session")
public class SessionController {

	@Autowired
	private SessionService sessionService;
	
	@PostMapping("login")
	public ResponseEntity<SessionResponse> login(AccountRequest request) {
		return null;
	}
	

	
	static SessionResponse toResponse(SessionDAO sessionDAO) {
		SessionResponse response = new SessionResponse();
		response.setSessionId(sessionDAO.getSessionId());
		response.setAccountResponse(AccountController.createResponse(sessionDAO.getAccountDAO()));
		response.setExpirationDate(sessionDAO.getExpirationDate());
		return response;
	}
}
