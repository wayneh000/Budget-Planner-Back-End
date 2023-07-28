package io.github.wayneh000.budgetplanner.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.wayneh000.budgetplanner.dao.AccountDAO;
import io.github.wayneh000.budgetplanner.entity.Account;
import io.github.wayneh000.budgetplanner.exception.BudgetPlannerException;
import io.github.wayneh000.budgetplanner.exception.ErrorMessages;
import io.github.wayneh000.budgetplanner.repository.AccountRepository;
import io.github.wayneh000.budgetplanner.request.AccountRequest;
import io.github.wayneh000.budgetplanner.request.UpdatePasswordRequest;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	private PasswordEncoder passwordEncoder;

	public AccountService() {
		passwordEncoder = new BCryptPasswordEncoder(16);
	}

	public AccountDAO createAccount(AccountRequest request) throws BudgetPlannerException {
		if (accountRepository.findByUsername(request.getUsername()).isPresent())
			throw new BudgetPlannerException(ErrorMessages.ACCOUNT_ALREADY_EXISTS);

		AccountDAO accountDAO = new AccountDAO();
		accountDAO.setUsername(request.getUsername());
		accountDAO.setPassword(passwordEncoder.encode(request.getPassword()));
		accountDAO.setDateCreated(LocalDateTime.now());
		accountDAO.setDateLastLogin(LocalDateTime.now());
		Account account = accountRepository.save(AccountDAO.toEntity(accountDAO));
		accountDAO.setAccountId(account.getAccountId());

		return accountDAO;
	}

	public AccountDAO getAccount(Integer accountId) throws BudgetPlannerException {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new BudgetPlannerException(ErrorMessages.ACCOUNT_NOT_FOUND));
		return AccountDAO.fromEntity(account);
	}

	public List<AccountDAO> getAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map(AccountDAO::fromEntity).collect(Collectors.toList());
	}

	public AccountDAO updatePassword(UpdatePasswordRequest request) throws BudgetPlannerException {
		AccountDAO accountDAO = verifyLogin(request.getUsername(), request.getPassword());
		accountDAO.setPassword(passwordEncoder.encode(request.getNewPassword()));
		accountRepository.save(AccountDAO.toEntity(accountDAO));

		return accountDAO;
	}

	AccountDAO verifyLogin(String username, String password) throws BudgetPlannerException {
		Account account = accountRepository.findByUsername(username)
				.orElseThrow(() -> new BudgetPlannerException(ErrorMessages.ACCOUNT_INVALID_CREDENTIALS));
		if (!passwordEncoder.matches(password, account.getPassword()))
			throw new BudgetPlannerException(ErrorMessages.ACCOUNT_INVALID_CREDENTIALS);

		return AccountDAO.fromEntity(account);
	}
}
