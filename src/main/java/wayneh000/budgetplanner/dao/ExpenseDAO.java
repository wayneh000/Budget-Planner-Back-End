package wayneh000.budgetplanner.dao;

import wayneh000.budgetplanner.entity.Expense;

public class ExpenseDAO {

	private Long expenseId;
	private BudgetDAO budgetDAO;
	private String name;
	private String category;
	private Double amount;
	private Integer period;
	
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
		Expense expense = new Expense();
		expense.setExpenseId(expenseDAO.getExpenseId());
		expense.setBudget(BudgetDAO.toEntity(expenseDAO.getBudgetDAO()));
		expense.setName(expenseDAO.getName());
		expense.setCategory(expenseDAO.getCategory());
		expense.setAmount(expenseDAO.getAmount());
		expense.setPeriod(expenseDAO.getPeriod());
		return expense;
	}
	
	public static ExpenseDAO fromEntity(Expense expense) {
		ExpenseDAO expenseDAO = new ExpenseDAO();
		expenseDAO.setExpenseId(expense.getExpenseId());
		expenseDAO.setBudgetDAO(BudgetDAO.fromEntity(expense.getBudget()));
		expenseDAO.setName(expense.getName());
		expenseDAO.setCategory(expense.getCategory());
		expenseDAO.setAmount(expense.getAmount());
		expenseDAO.setPeriod(expense.getPeriod());
		return expenseDAO;
	}
}
