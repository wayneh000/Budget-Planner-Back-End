package io.github.wayneh000.budgetplanner.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.wayneh000.budgetplanner.dao.AccountDAO;
import io.github.wayneh000.budgetplanner.exception.BudgetPlannerException;
import io.github.wayneh000.budgetplanner.exception.ErrorMessages;
import io.github.wayneh000.budgetplanner.request.AccountRequest;
import io.github.wayneh000.budgetplanner.request.UpdatePasswordRequest;
import io.github.wayneh000.budgetplanner.response.AccountResponse;
import io.github.wayneh000.budgetplanner.service.AccountService;

@RestController
@RequestMapping("api/v1/auth/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping("createAccount")
	public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest request) {
		try {
			return new ResponseEntity<>(createResponse(accountService.createAccount(request)), HttpStatus.CREATED);
		} catch (BudgetPlannerException e) {
			throw new ResponseStatusException(
					e.getMessage().equals(ErrorMessages.ACCOUNT_ALREADY_EXISTS) ? HttpStatus.CONFLICT
							: HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}

	@GetMapping("getAccount/{id}")
	public ResponseEntity<AccountResponse> getAccount(@PathVariable Integer id) {
		try {
			return new ResponseEntity<>(createResponse(accountService.getAccount(id)), HttpStatus.OK);
		} catch (BudgetPlannerException e) {
			throw new ResponseStatusException(
					e.getMessage().equals(ErrorMessages.ACCOUNT_NOT_FOUND) ? HttpStatus.NOT_FOUND
							: HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}

	@GetMapping("getAccount")
	public ResponseEntity<List<AccountResponse>> getAccounts() {
		List<AccountDAO> accountDAOs = accountService.getAccounts();
		List<AccountResponse> accountResponses = accountDAOs.stream().map(AccountController::createResponse).collect(Collectors.toList());
		return new ResponseEntity<>(accountResponses, HttpStatus.OK);
	}

	@PutMapping("updatePassword")
	public ResponseEntity<AccountResponse> updatePassword(@RequestBody UpdatePasswordRequest request) {
		try {
			return new ResponseEntity<>(createResponse(accountService.updatePassword(request)), HttpStatus.OK);
		} catch (BudgetPlannerException e) {
			throw new ResponseStatusException(
					e.getMessage().equals(ErrorMessages.ACCOUNT_INVALID_CREDENTIALS) ? HttpStatus.UNAUTHORIZED
							: HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}
	
	static AccountResponse createResponse(AccountDAO accountDAO) {
		AccountResponse response = new AccountResponse();
		response.setAccountId(accountDAO.getAccountId());
		response.setUsername(accountDAO.getUsername());
		response.setDateCreated(accountDAO.getDateCreated());
		response.setDateLastLogin(accountDAO.getDateLastLogin());
		return response;
	}
}
