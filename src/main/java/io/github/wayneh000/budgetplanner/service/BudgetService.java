package io.github.wayneh000.budgetplanner.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.wayneh000.budgetplanner.dao.AccountDAO;
import io.github.wayneh000.budgetplanner.dao.BudgetDAO;
import io.github.wayneh000.budgetplanner.dao.SessionDAO;
import io.github.wayneh000.budgetplanner.entity.Budget;
import io.github.wayneh000.budgetplanner.exception.BudgetPlannerException;
import io.github.wayneh000.budgetplanner.exception.ErrorMessages;
import io.github.wayneh000.budgetplanner.repository.BudgetRepository;
import io.github.wayneh000.budgetplanner.request.budget.NewBudgetRequest;
import io.github.wayneh000.budgetplanner.util.Period;

@Service
public class BudgetService {

	@Autowired
	private BudgetRepository budgetRepository;

	@Autowired
	private SessionService sessionService;

	public BudgetDAO createNewBudget(NewBudgetRequest request) throws BudgetPlannerException {
		SessionDAO sessionDAO = sessionService.verifySession(request.getSessionId());

		BudgetDAO budgetDAO = new BudgetDAO();
		budgetDAO.setAccountDAO(sessionDAO.getAccountDAO());
		budgetDAO.setName(request.getName());
		budgetDAO.setDescription(request.getDescription());
		budgetDAO.setPeriod(Period.getValueFromName(request.getPeriod()));
		budgetDAO.setDateCreated(LocalDateTime.now());
		budgetDAO.setDateLastEdited(LocalDateTime.now());
		Budget budget = budgetRepository.save(BudgetDAO.toEntity(budgetDAO));
		budgetDAO.setBudgetId(budget.getBudgetId());

		return budgetDAO;
	}

	public BudgetDAO getBudget(String sessionId, Integer budgetId) throws BudgetPlannerException {
		SessionDAO sessionDAO = sessionService.verifySession(sessionId);
		BudgetDAO budgetDAO = BudgetDAO.fromEntity(budgetRepository.findById(budgetId)
				.orElseThrow(() -> new BudgetPlannerException(ErrorMessages.BUDGET_NOT_FOUND)));
		
		if (!Objects.equals(sessionDAO.getAccountDAO().getAccountId(), budgetDAO.getAccountDAO().getAccountId()))
			throw new BudgetPlannerException(ErrorMessages.BUDGET_NOT_FOUND);
		
		return budgetDAO;
	}
	
	public List<BudgetDAO> getBudgets(String sessionId) throws BudgetPlannerException {
		SessionDAO sessionDAO = sessionService.verifySession(sessionId);
		List<Budget> budgets = budgetRepository.findAllByAccount(AccountDAO.toEntity(sessionDAO.getAccountDAO()));
		return budgets.stream().map(BudgetDAO::fromEntity).collect(Collectors.toList());
	}
}
