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
	
	public BudgetDAO() {
	}

	public BudgetDAO(Integer budgetId, AccountDAO accountDAO, String name, String description, Integer period,
			LocalDateTime dateCreated, LocalDateTime dateLastEdited) {
		this.budgetId = budgetId;
		this.accountDAO = accountDAO;
		this.name = name;
		this.description = description;
		this.period = period;
		this.dateCreated = dateCreated;
		this.dateLastEdited = dateLastEdited;
	}

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
		return new Budget(budgetDAO.getBudgetId(),
				AccountDAO.toEntity(budgetDAO.getAccountDAO()),
				budgetDAO.getName(),
				budgetDAO.getDescription(),
				budgetDAO.getPeriod(),
				budgetDAO.getDateCreated(),
				budgetDAO.getDateLastEdited());
	}
	
	public static BudgetDAO fromEntity(Budget budget) {
		return new BudgetDAO(budget.getBudgetId(),
				AccountDAO.fromEntity(budget.getAccount()),
				budget.getName(),
				budget.getDescription(),
				budget.getPeriod(),
				budget.getDateCreated(),
				budget.getDateLastEdited());
	}
}
