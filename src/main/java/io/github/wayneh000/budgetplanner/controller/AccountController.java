package io.github.wayneh000.budgetplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.wayneh000.budgetplanner.dao.AccountDAO;
import io.github.wayneh000.budgetplanner.exception.BudgetPlannerException;
import io.github.wayneh000.budgetplanner.request.AccountRequest;
import io.github.wayneh000.budgetplanner.request.UpdatePasswordRequest;
import io.github.wayneh000.budgetplanner.service.AccountService;

@RestController("/api/v1/auth")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping("/createAccount")
	public ResponseEntity<AccountDAO> createAccount(@RequestBody AccountRequest request) throws BudgetPlannerException {
		return new ResponseEntity<>(accountService.createAccount(request), HttpStatus.CREATED);
	}
	
	@GetMapping("/getAccount/<id>")
	public ResponseEntity<AccountDAO> getAccount(@RequestParam Integer id) throws BudgetPlannerException {
		return new ResponseEntity<>(accountService.getAccount(id), HttpStatus.OK);
	}
	
	@GetMapping("/getAccount")
	public ResponseEntity<List<AccountDAO>> getAccounts() {
		return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
	}
	
	@PutMapping("/updatePassword")
	public ResponseEntity<AccountDAO> updatePassword(@RequestBody UpdatePasswordRequest request) throws BudgetPlannerException {
		return new ResponseEntity<>(accountService.updatePassword(request), HttpStatus.OK);
	}
}
