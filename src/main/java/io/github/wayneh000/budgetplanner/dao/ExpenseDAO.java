package io.github.wayneh000.budgetplanner.dao;

import io.github.wayneh000.budgetplanner.entity.Expense;

public class ExpenseDAO {

	private Long expenseId;
	private BudgetDAO budgetDAO;
	private String name;
	private String category;
	private Double amount;
	private Integer period;
	
	public ExpenseDAO() {
	}

	public ExpenseDAO(Long expenseId, BudgetDAO budgetDAO, String name, String category, Double amount,
			Integer period) {
		this.expenseId = expenseId;
		this.budgetDAO = budgetDAO;
		this.name = name;
		this.category = category;
		this.amount = amount;
		this.period = period;
	}

	public Long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
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
		return "ExpenseDAO [expenseId=" + expenseId + ", budgetDAO=" + budgetDAO + ", name=" + name + ", category="
				+ category + ", amount=" + amount + ", period=" + period + "]";
	}
	
	public static Expense toEntity(ExpenseDAO expenseDAO) {
		return new Expense(expenseDAO.getExpenseId(),
				BudgetDAO.toEntity(expenseDAO.getBudgetDAO()),
				expenseDAO.getName(),
				expenseDAO.getCategory(),
				expenseDAO.getAmount(),
				expenseDAO.getPeriod());
	}
	
	public static ExpenseDAO fromEntity(Expense expense) {
		return new ExpenseDAO(expense.getExpenseId(),
				BudgetDAO.fromEntity(expense.getBudget()),
				expense.getName(),
				expense.getCategory(),
				expense.getAmount(),
				expense.getPeriod());
	}
}
