package io.github.wayneh000.budgetplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.wayneh000.budgetplanner.dao.SessionDAO;
import io.github.wayneh000.budgetplanner.exception.BudgetPlannerException;
import io.github.wayneh000.budgetplanner.exception.ErrorMessages;
import io.github.wayneh000.budgetplanner.request.AccountRequest;
import io.github.wayneh000.budgetplanner.response.SessionResponse;
import io.github.wayneh000.budgetplanner.service.SessionService;

@RestController
@RequestMapping("api/v1/auth/session")
public class SessionController {

	@Autowired
	private SessionService sessionService;
	
	@PostMapping("login")
	public ResponseEntity<SessionResponse> login(@RequestBody AccountRequest request) {
		try {
			return new ResponseEntity<>(createResponse(sessionService.createSession(request)), HttpStatus.CREATED);
		} catch (BudgetPlannerException e) {
			throw new ResponseStatusException(
					e.getMessage().equals(ErrorMessages.ACCOUNT_INVALID_CREDENTIALS) ? HttpStatus.UNAUTHORIZED
							: HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}
	
	@GetMapping("verify/{id}")
	public ResponseEntity<SessionResponse> verify(@PathVariable String id) {
		try {
			return new ResponseEntity<>(createResponse(sessionService.verifySession(id)), HttpStatus.OK);
		} catch (BudgetPlannerException e) {
			HttpStatus status;
			switch(e.getMessage()) {
				case ErrorMessages.SESSION_EXPIRED:
				case ErrorMessages.SESSION_NOT_FOUND:
					status = HttpStatus.UNAUTHORIZED;
					break;
				default:
					status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			throw new ResponseStatusException(status, e.getMessage());
		}
	}
	
	static SessionResponse createResponse(SessionDAO sessionDAO) {
		SessionResponse response = new SessionResponse();
		response.setSessionId(sessionDAO.getSessionId());
		response.setAccountResponse(AccountController.createResponse(sessionDAO.getAccountDAO()));
		response.setExpirationDate(sessionDAO.getExpirationDate());
		return response;
	}
}
