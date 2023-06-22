package io.github.wayneh000.budgetplanner.dao;

import io.github.wayneh000.budgetplanner.entity.Income;

public class IncomeDAO {

	private Long incomeId;
	private BudgetDAO budgetDAO;
	private String name;
	private String category;
	private Double amount;
	private Integer period;
	
	public IncomeDAO() {
	}

	public IncomeDAO(Long incomeId, BudgetDAO budgetDAO, String name, String category, Double amount, Integer period) {
		this.incomeId = incomeId;
		this.budgetDAO = budgetDAO;
		this.name = name;
		this.category = category;
		this.amount = amount;
		this.period = period;
	}

	public Long getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(Long incomeId) {
		this.incomeId = incomeId;
	}

	public BudgetDAO getBudgetDAO() {
		return budgetDAO;
	}

	public void setBudgetDAO(BudgetDAO budgetDAO) {
		this.budgetDAO = budgetDAO;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return "IncomeDAO [incomeId=" + incomeId + ", budgetDAO=" + budgetDAO + ", name=" + name + ", category="
				+ category + ", amount=" + amount + ", period=" + period + "]";
	}
	
	public static Income toEntity(IncomeDAO incomeDAO) {
		return new Income(incomeDAO.getIncomeId(),
				BudgetDAO.toEntity(incomeDAO.getBudgetDAO()),
				incomeDAO.getName(),
				incomeDAO.getCategory(),
				incomeDAO.getAmount(),
				incomeDAO.getPeriod());
	}
	
	public static IncomeDAO fromEntity(Income income) {
		return new IncomeDAO(income.getIncomeId(),
				BudgetDAO.fromEntity(income.getBudget()),
				income.getName(),
				income.getCategory(),
				income.getAmount(),
				income.getPeriod());
	}
}
