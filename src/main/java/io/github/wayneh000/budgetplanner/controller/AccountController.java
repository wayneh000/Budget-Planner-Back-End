package io.github.wayneh000.budgetplanner.controller;

import java.util.List;

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

import io.github.wayneh000.budgetplanner.exception.BudgetPlannerException;
import io.github.wayneh000.budgetplanner.exception.ErrorMessages;
import io.github.wayneh000.budgetplanner.request.AccountRequest;
import io.github.wayneh000.budgetplanner.request.UpdatePasswordRequest;
import io.github.wayneh000.budgetplanner.response.AccountResponse;
import io.github.wayneh000.budgetplanner.service.AccountService;

@RestController
@RequestMapping("/api/v1/auth")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping("/createAccount")
	public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest request) {
		try {
			return new ResponseEntity<>(accountService.createAccount(request), HttpStatus.CREATED);
		} catch (BudgetPlannerException e) {
			throw new ResponseStatusException(
					e.getMessage().equals(ErrorMessages.ACCOUNT_ALREADY_EXISTS) ? HttpStatus.CONFLICT
							: HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}

	@GetMapping("/getAccount/{id}")
	public ResponseEntity<AccountResponse> getAccount(@PathVariable Integer id) {
		try {
			return new ResponseEntity<>(accountService.getAccount(id), HttpStatus.OK);
		} catch (BudgetPlannerException e) {
			throw new ResponseStatusException(
					e.getMessage().equals(ErrorMessages.ACCOUNT_NOT_FOUND) ? HttpStatus.NOT_FOUND
							: HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}

	@GetMapping("/getAccount")
	public ResponseEntity<List<AccountResponse>> getAccounts() {
		return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
	}

	@PutMapping("/updatePassword")
	public ResponseEntity<AccountResponse> updatePassword(@RequestBody UpdatePasswordRequest request) {
		try {
			return new ResponseEntity<>(accountService.updatePassword(request), HttpStatus.OK);
		} catch (BudgetPlannerException e) {
			throw new ResponseStatusException(
					e.getMessage().equals(ErrorMessages.ACCOUNT_INVALID_CREDENTIALS) ? HttpStatus.UNAUTHORIZED
							: HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}
}
