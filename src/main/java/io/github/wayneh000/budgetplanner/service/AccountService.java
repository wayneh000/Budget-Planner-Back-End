package io.github.wayneh000.budgetplanner.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.wayneh000.budgetplanner.dao.AccountDAO;
import io.github.wayneh000.budgetplanner.entity.Account;
import io.github.wayneh000.budgetplanner.exception.BudgetPlannerException;
import io.github.wayneh000.budgetplanner.repository.AccountRepository;
import io.github.wayneh000.budgetplanner.request.AccountRequest;
import io.github.wayneh000.budgetplanner.request.UpdatePasswordRequest;
import io.github.wayneh000.budgetplanner.response.AccountResponse;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	private PasswordEncoder passwordEncoder;
	private SessionService sessionService;
	
	public AccountService() {
		passwordEncoder = new BCryptPasswordEncoder(16);
		sessionService = new SessionService();
	}
	
	public AccountResponse createAccount(AccountRequest request) throws BudgetPlannerException {
		if (accountRepository.findByUsername(request.getUsername()).isPresent())
			throw new BudgetPlannerException("AccountService.USERNAME_ALREADY_EXISTS");
		
		AccountDAO accountDAO = new AccountDAO();
		accountDAO.setUsername(request.getUsername());
		accountDAO.setPassword(passwordEncoder.encode(request.getPassword()));
		accountDAO.setDateCreated(LocalDateTime.now());
		accountDAO.setDateLastLogin(LocalDateTime.now());
		Account account = accountRepository.save(AccountDAO.toEntity(accountDAO));
		accountDAO.setAccountId(account.getAccountId());
		
		return AccountDAO.toResponse(accountDAO);
	}
	
	public AccountResponse getAccount(Integer accountId) throws BudgetPlannerException {
		Account account = accountRepository.findById(accountId).orElseThrow(() -> new BudgetPlannerException("AccountService.ACCOUNT_NOT_FOUND"));
		return AccountDAO.toResponse(AccountDAO.fromEntity(account));
	}
	
	public List<AccountResponse> getAccounts() {
		List<Account> accounts = accountRepository.findAll();
		List<AccountResponse> response = new ArrayList<>(accounts.size());
		for (Account account : accounts)
			response.add(AccountDAO.toResponse(AccountDAO.fromEntity(account)));
		return response;
	}
	
	public AccountDAO login(AccountRequest request) throws BudgetPlannerException {
		AccountDAO accountDAO = verifyLogin(request.getUsername(), request.getPassword());
		accountDAO.setDateLastLogin(LocalDateTime.now());
		accountRepository.save(AccountDAO.toEntity(accountDAO));
		sessionService.createSession(accountDAO);
		
		return accountDAO;
	}
	
	public AccountResponse updatePassword(UpdatePasswordRequest request) throws BudgetPlannerException {
		AccountDAO accountDAO = verifyLogin(request.getUsername(), request.getPassword());
		accountDAO.setPassword(passwordEncoder.encode(request.getNewPassword()));
		accountRepository.save(AccountDAO.toEntity(accountDAO));
		
		return AccountDAO.toResponse(accountDAO);
	}
	
	private AccountDAO verifyLogin(String username, String password) throws BudgetPlannerException {
		Account account = accountRepository.findByUsername(username).orElseThrow(() -> new BudgetPlannerException("AccountService.INVALID_CREDENTIALS"));
		if (!passwordEncoder.matches(password, account.getPassword()))
			throw new BudgetPlannerException("AccountService.INVALID_CREDENTIALS");
		
		return AccountDAO.fromEntity(account);
	}
}
