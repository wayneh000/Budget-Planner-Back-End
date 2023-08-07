package io.github.wayneh000.budgetplanner.dao;

import java.time.LocalDateTime;

import io.github.wayneh000.budgetplanner.entity.Budget;

public class BudgetDAO {

	private Integer budgetId;
	private AccountDAO accountDAO;
	private String name;
	private String description;
	private Integer period;
	private LocalDateTime dateCreated;
	private LocalDateTime dateLastEdited;

	public Integer getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}

	public AccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateLastEdited() {
		return dateLastEdited;
	}

	public void setDateLastEdited(LocalDateTime dateLastEdited) {
		this.dateLastEdited = dateLastEdited;
	}

	@Override
	public String toString() {
		return "BudgetDAO [budgetId=" + budgetId + ", accountDAO=" + accountDAO + ", name=" + name + ", description="
				+ description + ", period=" + period + ", dateCreated=" + dateCreated + ", dateLastEdited="
				+ dateLastEdited + "]";
	}
	
	public static Budget toEntity(BudgetDAO budgetDAO) {
		Budget budget = new Budget();
		budget.setBudgetId(budgetDAO.getBudgetId());
		budget.setAccount(AccountDAO.toEntity(budgetDAO.getAccountDAO()));
		budget.setName(budgetDAO.getName());
		budget.setDescription(budgetDAO.getDescription());
		budget.setPeriod(budgetDAO.getPeriod());
		budget.setDateCreated(budgetDAO.getDateCreated());
		budget.setDateLastEdited(budgetDAO.getDateLastEdited());
		return budget;
	}
	
	public static BudgetDAO fromEntity(Budget budget) {
		BudgetDAO budgetDAO = new BudgetDAO();
		budgetDAO.setBudgetId(budget.getBudgetId());
		budgetDAO.setAccountDAO(AccountDAO.fromEntity(budget.getAccount()));
		budgetDAO.setName(budget.getName());
		budgetDAO.setDescription(budget.getDescription());
		budgetDAO.setPeriod(budget.getPeriod());
		budgetDAO.setDateCreated(budget.getDateCreated());
		budgetDAO.setDateLastEdited(budget.getDateLastEdited());
		return budgetDAO;
	}
}
